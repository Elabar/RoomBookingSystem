package utar.edu.my;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

@RunWith(JUnitParamsRunner.class)
public class IntegrationTestBooking {
	
	private Scanner inputDataScanner;

	//room_type=vip/deluxe/standard
	//member_type=vip/normal/non
	private Object[] dataForSetBooking(){
		File file = new File("TestValue.txt");
		try {
			this.inputDataScanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Object> params = new ArrayList<Object>();
		while(inputDataScanner.hasNextLine()) {
			String[] text = inputDataScanner.nextLine().split(",");
			ArrayList<Object> param =  new ArrayList<Object>();
			param.add(String.valueOf(text[0].trim()));
			param.add(String.valueOf(text[1].trim()));
			param.add(String.valueOf(text[2].trim()));
			param.add(String.valueOf(text[3].trim()));
			param.add(String.valueOf(text[4].trim()));
			param.add(String.valueOf(text[5].trim()));
			param.add(String.valueOf(text[6].trim()));
			param.add(String.valueOf(text[7].trim()));
			params.add(param.toArray());
		}
		return params.toArray();
	}
	
	/*
	 * This will ONLY test user try to book 1 room at a time
	 */
	@Test
	@Parameters(method = "dataForSetBooking")
	public void testSetBookingCanBook(boolean canBook,String expectedRoomType,String memberType,boolean reward,boolean vipAvailability,boolean deluxeAvailability,boolean standardAvailability,boolean isAssign) {
		//Room is still mock as we did not implement the class
		Room room = mock(Room.class);
		when(room.checkRoom("vip")).thenReturn(vipAvailability);
		when(room.checkRoom("deluxe")).thenReturn(deluxeAvailability);
		when(room.checkRoom("standard")).thenReturn(standardAvailability);

		WaitingList wl = new WaitingList();
		
		//Random object still need to be mock object as we need to control the random factor
		Random random = mock(Random.class);
		if(reward) {
			when(random.nextInt(anyInt())).thenReturn(3);
		}else {
			when(random.nextInt(anyInt())).thenReturn(4);
		}
		
		User user = new User("Lee Hoe Mun",memberType,random);
		
		Booking booking = new Booking();
		booking.setBooking(user,1,wl,room);
		assertEquals(1,user.getNumberOfBookedRoom());
		if(isAssign) {
			verify(room,times(1)).assignRoom(expectedRoomType);
			assertEquals(expectedRoomType,booking.getRooms().get(0));
		}else {
			assertEquals(user,wl.getWaiting().get(0));
			assertEquals("waitingList",booking.getWaitingLists().get(0));
		}
		
		if(isAssign && "normal".equals(user.getMemberType()) && "vip".equals(expectedRoomType)) {
			assertFalse(user.getReward());
		}
	}
	
	/*
	 * This will test the invalid member type
	 * However, the exception throw here is threw 
	 * from the user class as we already have a validation
	 * for member type when we create a user
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetBookingInvalidMember() {
		Random random = mock(Random.class);
		
		User user = new User("Lee Hoe Mun","asd",random);
		
		Room room = mock(Room.class);
		
		WaitingList wl = new WaitingList();
		
		Booking booking = new Booking();
		
		booking.setBooking(user,1,wl,room);
	}
	
	/*
	 * This will test the user who try to book room when
	 * he/she has hit the limit of his/her member limitation
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetBookingCantBook() {
		Random random = mock(Random.class);
		User user = new User("Lee Hoe Mun","non",random);
		
		user.addNumberOfBookedRoom();
		
		Room room = mock(Room.class);
		
		WaitingList wl = new WaitingList();
		
		Booking booking = new Booking();
		
		booking.setBooking(user,1,wl,room);
	}
	
	/*
	 * We have tested all of the combinations condition for booking 1 room
	 * and ensure that the logic is correct. Now we only need to verify when 
	 * we try to book multiple room, the system actually book multiple times.
	 * We will use boundary analysis to test this. So all of the combinations 
	 * are 0,1,3,4 where 0 and 4 are the invalid combinations and 1 and 3 are 
	 * the valid combinations. As we have tested the 1 combination, we are going
	 * to test book 3 times in this test ONLY. Invalid combinations will be tested
	 * in another method
	 */
	@Test
	public void testSetBookingValidMultipleTimes() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(4);
		User user = new User("Lee Hoe Mun","vip",random);
		
		Room room = mock(Room.class);
		when(room.checkRoom("vip")).thenReturn(true);
		when(room.checkRoom("deluxe")).thenReturn(true);
		when(room.checkRoom("standard")).thenReturn(true);

		WaitingList wl = new WaitingList();
		
		Booking booking = new Booking();
		booking.setBooking(user,3,wl,room);
		
		//make sure the record is recording the correct room type
		for(int i = 0;i < 3;i++) {
			assertEquals("vip",booking.getRooms().get(i));
		}
	}
	
	/*
	 * We will test the invalid book times (0 and 4) in this method
	 * Using boundary analysis
	 */
	@Test(expected = IllegalArgumentException.class)
	@Parameters({"4","0"})
	public void testSetBookingInValidMultipleTimes(int numberOfBooking) {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(4);
		User user = new User("Lee Hoe Mun","vip",random);
		
		Room room = mock(Room.class);
		when(room.checkRoom("vip")).thenReturn(true);
		when(room.checkRoom("deluxe")).thenReturn(true);
		when(room.checkRoom("standard")).thenReturn(true);

		WaitingList wl = new WaitingList();
		
		Booking booking = new Booking();
		booking.setBooking(user,numberOfBooking,wl,room);
	}
	
	/*
	 * To check all of the if else branch in cancel booking
	 * We will need to separate into two tests. One is 
	 * removing rooms and another one is removing waiting
	 * list. We test the remove room here. 
	 */
	@Test
	@Parameters({"vip,vip","deluxe,normal","standard,non"})
	public void testCancelBookingRooms(String roomType,String memberType) {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(4);
		User user = new User("Lee Hoe Mun",memberType,random);
		
		Room room = mock(Room.class);
		when(room.checkRoom(roomType)).thenReturn(true);
		
		WaitingList wl = new WaitingList();
		
		Booking booking = new Booking();
		booking.setBooking(user,1,wl,room);
		
		booking.cancelBooking(user, room, wl);
		
		verify(room).resignRoom(roomType);
		assertEquals(0,user.getNumberOfBookedRoom());
		assertEquals(0,wl.getWaiting().size());
	}
	
	/*
	 * Another test for cancelBooking to test all of the branches
	 * This will focus on removing waiting list
	 */
	@Test
	public void testCancelBookingWaitingList() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(4);
		User user = new User("Lee Hoe Mun","vip",random);
		
		Room room = mock(Room.class);
		when(room.checkRoom("vip")).thenReturn(false);
		
		WaitingList wl = new WaitingList();
		
		Booking booking = new Booking();
		booking.setBooking(user,1,wl,room);
		
		booking.cancelBooking(user, room, wl);
		verify(room,never()).resignRoom("vip");
		assertEquals(0,user.getNumberOfBookedRoom());
		assertEquals(0,wl.getWaiting().size());
	}
	
	/*
	 * This will test if the print info will pass the correct
	 * value to the printer class
	 */
	@Test
	public void testPrintInfo() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(4);
		User user = new User("Lee Hoe Mun","vip",random);
		
		Room room = mock(Room.class);
		when(room.checkRoom("vip")).thenReturn(true);
		
		WaitingList wl = new WaitingList();
		
		Printer printer = mock(Printer.class);
		
		Booking booking = new Booking();
		booking.setBooking(user,1,wl,room);
		
		booking.printBooking(user, printer);
		
		verify(printer).printInfo("Lee Hoe Mun", "vip", "vip");
	}
}
