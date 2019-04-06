package utar.edu.my;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestWaitingList {
	
	@Test
	public void testAddUser() {
		User user = mock(User.class);
		
		WaitingList SUT = new WaitingList();
		
		SUT.addUser(user);
		
		assertEquals(user,SUT.getWaiting().get(0));
	}
	
	@Test
	public void testRemoveUser() {
		User user = mock(User.class);
		
		WaitingList SUT = new WaitingList();
		
		SUT.addUser(user);
		
		SUT.removeUser(user);
		assertEquals(0,SUT.getWaiting().size());
	}
	
}
