package utar.edu.my;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.Random;

public class IntegrationTestWaitingList {
	
	@Test
	public void testAddUser() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(3);
		User user = new User("test","vip",random);
		
		WaitingList waitingList = new WaitingList();
		
		waitingList.addUser(user);
		
		assertEquals(user,waitingList.getWaiting().get(0));
	}
	
	@Test
	public void testRemoveUser() {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(3);
		User user = new User("test","vip",random);
		
		WaitingList waitingList = new WaitingList();
		
		waitingList.addUser(user);
		
		waitingList.removeUser(user);
		assertEquals(0,waitingList.getWaiting().size());
	}
	
}
