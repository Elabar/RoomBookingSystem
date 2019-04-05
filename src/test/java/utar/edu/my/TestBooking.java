package utar.edu.my;

import org.junit.Test;
import org.junit.runner.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class TestBooking {
	//room_type=vip/deluxe/standard
	//member_type=vip/normal/non
	private Object[] dataForSetBooking(){
		return new Object[] {
		  //----------------------------vip---------------------------
		  //new Object[] {canBook,expectedRoom,memberType,reward,vipRoom, deluxeRoom,standardRoom,isAssign,
			new Object[] {true,		"vip",		"vip",		true,	true,		true,		 true,true},
			new Object[] {true,		"vip",		"vip",		true,	true,		true,		 false,true},
			new Object[] {true,		"vip",		"vip",		true,	true,		false,		 true,true},
			new Object[] {true,		"vip",		"vip",		true,	true,		false,		 false,true},
			new Object[] {true,		"deluxe",	"vip",		true,	false,		true,		 true,true},
			new Object[] {true,		"deluxe",	"vip",		true,	false,		true,		 false,true},
			new Object[] {true,		"standard",	"vip",		true,	false,		false,		 true,true},
			new Object[] {true,			null,	"vip",		true,	false,		false,		 false,false},
			new Object[] {true,		"vip",		"vip",		false,	true,		true,		 true,true},
			new Object[] {true,		"vip",		"vip",		false,	true,		true,		 false,true},
			new Object[] {true,		"vip",		"vip",		false,	true,		false, 		 true,true},
			new Object[] {true,		"vip",		"vip",		false,	true,		false,		 false,true},
			new Object[] {true,		"deluxe",	"vip",		false,	false,		true,		 true,true},
			new Object[] {true,		"deluxe",	"vip",		false,	false,		true,		 false,true},
			new Object[] {true,		"standard",	"vip",		false,	false,		false,		 true,true},
			new Object[] {true,			null,	"vip",		false,	false,		false,		 false,false},
		  //----------------------------normal---------------------------
			new Object[] {true,		"vip",		"normal",	true,	true,		true,		 true,true},
			new Object[] {true,		"vip",		"normal",	true,	true,		true,		 false,true},
			new Object[] {true,		"vip",		"normal",	true,	true,		false,		 true,true},
			new Object[] {true,		"vip",		"normal",	true,	true,		false,		 false,true},
			new Object[] {true,		"deluxe",	"normal",	true,	false,		true,		 true,true},
			new Object[] {true,		"deluxe",	"normal",	true,	false,		true,		 false,true},
			new Object[] {true,		"standard",	"normal",	true,	false,		false,		 true,true},
			new Object[] {true,			null,	"normal",	true,	false,		false,		 false,false},
			new Object[] {true,		"deluxe",		"normal",	false,	true,	true,		 true,true},
			new Object[] {true,		"deluxe",		"normal",	false,	true,	true,		 false,true},
			new Object[] {true,		"standard",		"normal",	false,	true,	false, 		 true,true},
			new Object[] {true,			null,		"normal",	false,	true,	false, 		 false,false},
			new Object[] {true,		"deluxe",	"normal",	false,	false,		true,		 true,true},
			new Object[] {true,		"deluxe",	"normal",	false,	false,		true,		 false,true},
			new Object[] {true,		"standard",	"normal",	false,	false,		false,		 true,true},
			new Object[] {true,			null,	"normal",	false,	false,		false,		 false,false},
		  //----------------------------non---------------------------
			new Object[] {true,		"standard",		"non",	true,	true,		true,		 true,true},
			new Object[] {true,			null,		"non",	true,	true,		true,		 false,false},
			new Object[] {true,		"standard",		"non",	true,	true,		false,		 true,true},
			new Object[] {true,			null,		"non",	true,	true,		false,		 false,false},
			new Object[] {true,		"standard",	"non",		true,	false,		true,		 true,true},
			new Object[] {true,			null,	"non",		true,	false,		true,		 false,false},
			new Object[] {true,		"standard",	"non",		true,	false,		false,		 true,true},
			new Object[] {true,			null,	"non",		true,	false,		false,		 false,false},
			new Object[] {true,		"standard",		"non",	false,	true,	true,		 true,true},
			new Object[] {true,			null,		"non",	false,	true,	true,		 false,false},
			new Object[] {true,		"standard",		"non",	false,	true,	false, 		 true,true},
			new Object[] {true,			null,		"non",	false,	true,	false, 		 false,false},
			new Object[] {true,		"standard",	"non",		false,	false,		true,		 true,true},
			new Object[] {true,			null,	"non",		false,	false,		true,		 false,false},
			new Object[] {true,		"standard",	"non",		false,	false,		false,		 true,true},
			new Object[] {true,			null,	"non",		false,	false,		false,		 false,false},
		};
	}
	
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
	 * We only need to use one member type to test for 2 and 3 times.
	 */
	@Test
	@Parameters({"vip,2,vip","vip,3,vip",
		"normal,2,deluxe","normal,3,deluxe",
		"non,2,standard","non,3,standard"})
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
		
		//make sure the record is recording the correct room type and waiting list
		for(int i = 0;i < numberOfBooking;i++) {
			assertEquals(expectedRoomType,SUT.getRooms().get(i));
		}
	}
	
	/*
	 * Now we want to ensure the system only allow 1/2/3 of booking times
	 * in a single booking session. If we look at the coding, the member 
	 * type will affect this test, so we can hard code it.
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
	
	@Test
	@Parameters({"vip,vip","deluxe,normal","standard,non"})
	public void testRemoveBookingRooms(String roomType,String memberType) {
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
	
	@Test
	@Parameters({"vip,vip","deluxe,normal","standard,non"})
	public void testRemoveBookingWaitingList(String roomType,String memberType) {
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
	
	@Test
	@Parameters({"vip,vip","deluxe,normal","standard,non"})
	public void testPrintInfo(String roomType,String memberType) {
		User user = mock(User.class);
		when(user.canBook()).thenReturn(true);
		when(user.getMemberType()).thenReturn(memberType);
		when(user.getReward()).thenReturn(false);
		when(user.getName()).thenReturn("test");
		
		Room room = mock(Room.class);
		when(room.checkRoom(roomType)).thenReturn(true);
		
		WaitingList wl = mock(WaitingList.class);
		
		Printer printer = mock(Printer.class);
		
		Booking SUT = new Booking();
		SUT.setBooking(user,1,wl,room);
		
		SUT.printBooking(user, printer);
		
		verify(printer).printInfo("test", memberType, roomType);
	}
}
