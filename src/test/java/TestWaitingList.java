import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.*;

@RunWith(JUnitParamsRunner.class)
public class TestWaitingList {

	
	@Test
	@Parameters({"VIP","normal","non"})
	public void testValidWaitingListConstrutor(String memberType){
		User user = mock(User.class);
		when(user.getMemberType()).thenReturn(memberType);
		WaitingList SUT = new WaitingList(user);
		assertEquals(user,SUT.getWaiting());
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters({"asd","qwe","123"})
	public void testInvalidWaitingListConstrutor(String memberType){
		User user = mock(User.class);
		when(user.getMemberType()).thenReturn(memberType);
		WaitingList SUT = new WaitingList(user);
	}
	
	@Test
	@Parameters({"VIP","normal","non"})
	public void testRemoveWaiting(String memberType) {
		User user = mock(User.class);
		when(user.getMemberType()).thenReturn(memberType);
		WaitingList SUT = new WaitingList(user);
		SUT.removeWaiting();
		assertEquals(0,SUT.getNumOfUserInList());
	}
	
}
