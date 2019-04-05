package utar.edu.my;
import org.junit.Test;
import org.junit.runner.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Random;

@RunWith(JUnitParamsRunner.class)
public class TestUser {
	
	private Object[] users() {
		return new Object[] {
			//reward = true but non will always have false
			new Object[] {"name1","vip",3,3,true},
			new Object[] {"name2","normal",2,3,true},
			new Object[] {"name2","non",1,3,false},
			//reward = false
			new Object[] {"name1","vip",3,7,false},
			new Object[] {"name2","normal",2,7,false},
			new Object[] {"name2","non",1,7,false}
		};
	}
	
	@Test
	@Parameters(method = "users")
	public void testValidUserConstructor(String name,String memberType,int maxRoom,int randomNum,boolean expectedReward) {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(randomNum);
		User SUT = new User(name,memberType,random);
		assertEquals(maxRoom,SUT.getMaxNumberOfBookRoom());
		assertEquals(expectedReward,SUT.getReward());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidUserConstructor() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(3);
		User SUT = new User("name1","asd",random);
	}
	
	@Test
	public void testSetBooking() {
		Random random = mock(Random.class);
		User SUT = new User("name1","vip",random);
		
		WaitingList wl = mock(WaitingList.class);
		
		Room room = mock(Room.class);
		
		Booking booking = mock(Booking.class);
		
		SUT.setBookingTest(1, wl, room, booking);
		
		verify(booking).setBooking(SUT, 1, wl, room);
		
		assertEquals(booking,SUT.getBookings().get(0));
	}
	
	@Test
	public void testRemoveBooking() {
		Random random = mock(Random.class);
		User SUT = new User("name1","vip",random);
		
		WaitingList wl = mock(WaitingList.class);
		
		Room room = mock(Room.class);
		
		Booking booking = mock(Booking.class);
		
		SUT.setBookingTest(1, wl, room, booking);
		
		SUT.removeBooking(booking, room, wl);
		
		verify(booking).cancelBooking(SUT, room, wl);
		
		assertEquals(0,SUT.getBookings().size());
	}
	
	@Test
	public void testGetMemberType() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(2);
		User SUT = new User("test","vip",random);
		
		assertEquals("vip",SUT.getMemberType());
	}
	
	@Test
	public void testCanBookTrue() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(2);
		User SUT = new User("test","vip",random);
		
		assertTrue(SUT.canBook());
	}
	
	@Test
	public void testCanBookFalse() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(2);
		User SUT = new User("test","non",random);
		SUT.addNumberOfBookedRoom();
		assertFalse(SUT.canBook());
	}
	
	@Test
	public void testAddNumberOfBookedRoom() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(2);
		User SUT = new User("test","non",random);
		
		SUT.addNumberOfBookedRoom();
		assertEquals(1,SUT.getNumberOfBookedRoom());
	}
	
	@Test
	public void testDecreaseNumberOfBookedRoom() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(2);
		User SUT = new User("test","non",random);
		
		SUT.addNumberOfBookedRoom();
		SUT.addNumberOfBookedRoom();
		SUT.decreaseNumberOfBookedRoom();
		assertEquals(1,SUT.getNumberOfBookedRoom());
	}
	
	/*
	 * We test true and false here to make sure the constructor
	 * did not disturb the setter for reward
	 */
	@Test
	@Parameters({"true","false"})
	public void testSetReward(boolean expectedReward) {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(2);
		User SUT = new User("test","non",random);
		
		SUT.setReward(expectedReward);
		assertEquals(expectedReward,SUT.getReward());
	}
	
	@Test
	public void testPrintBooking() {
		Random random = mock(Random.class);
		User SUT = new User("name1","vip",random);
		
		WaitingList wl = mock(WaitingList.class);
		
		Room room = mock(Room.class);
		
		Booking booking = mock(Booking.class);
		
		Printer printer = mock(Printer.class);
		
		SUT.setBookingTest(1, wl, room, booking);
		
		SUT.printBooking(SUT, printer);
		
		verify(booking).printBooking(SUT, printer);
	}
	
	@Test
	public void testGetName() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(3);
		User SUT = new User("test","vip",random);
		assertEquals("test",SUT.getName());
	}
}
