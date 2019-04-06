package utar.edu.my;
import org.junit.Test;
import org.junit.runner.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Random;

@RunWith(JUnitParamsRunner.class)
public class IntegrationTestUser {
	
	private Object[] users() {
		return new Object[] {
			//reward = true but non will always have false
			new Object[] {"name1","vip",3,3,true},
			new Object[] {"name2","normal",2,3,true},
			new Object[] {"name2","non",1,3,false},
			//reward = false
			new Object[] {"name1","vip",3,4,false},
			new Object[] {"name2","normal",2,4,false},
			new Object[] {"name2","non",1,4,false}
		};
	}
	
	@Test
	@Parameters(method = "users")
	public void testValidUserConstructor(String name,String memberType,int maxRoom,int randomNum,boolean expectedReward) {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(randomNum);
		User user = new User(name,memberType,random);
		assertEquals(maxRoom,user.getMaxNumberOfBookRoom());
		assertEquals(expectedReward,user.getReward());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidUserConstructor() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(3);
		User user = new User("name1","asd",random);
	}
	
	@Test
	public void testSetBooking() {
		Random random = mock(Random.class);
		User user = new User("name1","vip",random);
		
		WaitingList wl = new WaitingList();
		
		Room room = mock(Room.class);
		
		Booking booking = new Booking();
		
		user.setBookingTest(1, wl, room, booking);
		
		assertEquals(booking,user.getBookings().get(0));
	}
	
	@Test
	public void testRemoveBooking() {
		Random random = mock(Random.class);
		User user = new User("name1","vip",random);
		
		WaitingList wl = new WaitingList();
		
		Room room = mock(Room.class);
		
		Booking booking = new Booking();
		
		user.setBookingTest(1, wl, room, booking);
		
		user.removeBooking(booking, room, wl);
		
		assertEquals(0,user.getBookings().size());
	}
	
	@Test
	public void testGetMemberType() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(2);
		User user = new User("test","vip",random);
		
		assertEquals("vip",user.getMemberType());
	}
	
	@Test
	public void testCanBookTrue() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(2);
		User user = new User("test","vip",random);
		
		assertTrue(user.canBook());
	}
	
	@Test
	public void testCanBookFalse() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(2);
		User user = new User("test","non",random);
		user.addNumberOfBookedRoom();
		assertFalse(user.canBook());
	}
	
	@Test
	public void testAddNumberOfBookedRoom() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(2);
		User user = new User("test","non",random);
		
		user.addNumberOfBookedRoom();
		assertEquals(1,user.getNumberOfBookedRoom());
	}
	
	@Test
	public void testDecreaseNumberOfBookedRoom() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(2);
		User user = new User("test","non",random);
		
		user.addNumberOfBookedRoom();
		user.addNumberOfBookedRoom();
		user.decreaseNumberOfBookedRoom();
		assertEquals(1,user.getNumberOfBookedRoom());
	}
	
	/*
	 * We test true and false here to make sure the reward
	 * is not set by constructor but the setter
	 */
	@Test
	@Parameters({"true","false"})
	public void testSetReward(boolean expectedReward) {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(2);
		User user = new User("test","non",random);
		
		user.setReward(expectedReward);
		assertEquals(expectedReward,user.getReward());
	}
	
	@Test
	public void testPrintBooking() {
		Random random = mock(Random.class);
		User user = new User("name1","vip",random);
		
		WaitingList wl = new WaitingList();
		
		Room room = mock(Room.class);
		when(room.checkRoom("vip")).thenReturn(true);
		when(room.checkRoom("deluxe")).thenReturn(true);
		when(room.checkRoom("standard")).thenReturn(true);
		
		Booking booking = new Booking();
		
		Printer printer = mock(Printer.class);
		
		user.setBookingTest(1, wl, room, booking);
		
		user.printBooking(user, printer);
		
		verify(printer).printInfo("name1", "vip","vip");
	}
	
	@Test
	public void testGetName() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(3);
		User user = new User("test","vip",random);
		assertEquals("test",user.getName());
	}
}
