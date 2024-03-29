package utar.edu.my;

import org.junit.Test;
import org.junit.runner.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

@RunWith(JUnitParamsRunner.class)
public class TestBooking {
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
		User user = mock(User.class);
		when(user.canBook()).thenReturn(canBook);
		when(user.getMemberType()).thenReturn(memberType);
		when(user.getReward()).thenReturn(reward);
		
		Room room = mock(Room.class);
		when(room.checkRoom("vip")).thenReturn(vipAvailability);
		when(room.checkRoom("deluxe")).thenReturn(deluxeAvailability);
		when(room.checkRoom("standard")).thenReturn(standardAvailability);

		WaitingList wl = mock(WaitingList.class);
		
		Booking SUT = new Booking();
		SUT.setBooking(user,1,wl,room);
		verify(user,times(1)).addNumberOfBookedRoom();
		if(isAssign) {
			verify(room,times(1)).assignRoom(expectedRoomType);
			assertEquals(expectedRoomType,SUT.getRooms().get(0));
		}else {
			verify(wl,times(1)).addUser(user);
			assertEquals("waitingList",SUT.getWaitingLists().get(0));
		}
		
		if(isAssign && "normal".equals(user.getMemberType()) && "vip".equals(expectedRoomType)) {
			verify(user,times(1)).setReward(false);
		}
	}
	
	//This will test the invalid member type
	@Test(expected = IllegalArgumentException.class)
	public void testSetBookingInvalidMember() {
		User user = mock(User.class);
		when(user.canBook()).thenReturn(true);
		when(user.getMemberType()).thenReturn("asd");
		
		Room room = mock(Room.class);
		
		WaitingList wl = mock(WaitingList.class);
		
		Booking SUT = new Booking();
		SUT.setBooking(user,1,wl,room);
	}
	
	/*
	 * This will test the user who try to book room when
	 * he/she has hit the limit of his/her member limitation
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetBookingCantBook() {
		User user = mock(User.class);
		when(user.canBook()).thenReturn(false);
		
		Room room = mock(Room.class);
		
		WaitingList wl = mock(WaitingList.class);
		
		Booking SUT = new Booking();
		SUT.setBooking(user,1,wl,room);
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
	@Parameters({"vip,3,vip"})
	public void testSetBookingValidMultipleTimes(String memberType,int numberOfBooking,String expectedRoomType) {
		User user = mock(User.class);
		when(user.canBook()).thenReturn(true);
		when(user.getMemberType()).thenReturn(memberType);
		when(user.getReward()).thenReturn(false);
		
		Room room = mock(Room.class);
		when(room.checkRoom("vip")).thenReturn(true);
		when(room.checkRoom("deluxe")).thenReturn(true);
		when(room.checkRoom("standard")).thenReturn(true);

		WaitingList wl = mock(WaitingList.class);
		
		Booking SUT = new Booking();
		SUT.setBooking(user,numberOfBooking,wl,room);
		
		//make sure the record is recording the correct room type
		for(int i = 0;i < numberOfBooking;i++) {
			assertEquals(expectedRoomType,SUT.getRooms().get(i));
		}
	}
	
	/*
	 * We will test the invalid book times (0 and 4) in this method
	 * Using boundary analysis
	 */
	@Test(expected = IllegalArgumentException.class)
	@Parameters({"4","0"})
	public void testSetBookingInValidMultipleTimes(int numberOfBooking) {
		User user = mock(User.class);
		when(user.canBook()).thenReturn(true);
		when(user.getMemberType()).thenReturn("vip");
		when(user.getReward()).thenReturn(false);
		
		Room room = mock(Room.class);
		when(room.checkRoom("vip")).thenReturn(true);
		when(room.checkRoom("deluxe")).thenReturn(true);
		when(room.checkRoom("standard")).thenReturn(true);

		WaitingList wl = mock(WaitingList.class);
		
		Booking SUT = new Booking();
		SUT.setBooking(user,numberOfBooking,wl,room);
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
		User user = mock(User.class);
		when(user.canBook()).thenReturn(true);
		when(user.getMemberType()).thenReturn(memberType);
		when(user.getReward()).thenReturn(false);
		
		Room room = mock(Room.class);
		when(room.checkRoom(roomType)).thenReturn(true);
		
		WaitingList wl = mock(WaitingList.class);
		
		Booking SUT = new Booking();
		SUT.setBooking(user,1,wl,room);
		
		SUT.cancelBooking(user, room, wl);
		
		verify(room).resignRoom(roomType);
		verify(user).decreaseNumberOfBookedRoom();
		verify(wl,never()).removeUser(user);
	}
	
	/*
	 * Another test for cancelBooking to test all of the branches
	 * This will focus on removing waiting list
	 */
	@Test
	@Parameters({"vip,vip"})
	public void testCancelBookingWaitingList(String roomType,String memberType) {
		User user = mock(User.class);
		when(user.canBook()).thenReturn(true);
		when(user.getMemberType()).thenReturn(memberType);
		when(user.getReward()).thenReturn(false);
		
		Room room = mock(Room.class);
		when(room.checkRoom(roomType)).thenReturn(false);
		
		WaitingList wl = mock(WaitingList.class);
		
		Booking SUT = new Booking();
		SUT.setBooking(user,1,wl,room);
		
		SUT.cancelBooking(user, room, wl);
		verify(room,never()).resignRoom(roomType);
		verify(user).decreaseNumberOfBookedRoom();
		verify(wl).removeUser(user);
	}
	
	/*
	 * This will test if the print info will pass the correct
	 * value to the printer class
	 */
	@Test
	public void testPrintInfo() {
		User user = mock(User.class);
		when(user.canBook()).thenReturn(true);
		when(user.getMemberType()).thenReturn("vip");
		when(user.getReward()).thenReturn(false);
		when(user.getName()).thenReturn("test");
		
		Room room = mock(Room.class);
		when(room.checkRoom("vip")).thenReturn(true);
		
		WaitingList wl = mock(WaitingList.class);
		
		Printer printer = mock(Printer.class);
		
		Booking SUT = new Booking();
		SUT.setBooking(user,1,wl,room);
		
		SUT.printBooking(user, printer);
		
		verify(printer).printInfo("test", "vip", "vip");
	}
}
