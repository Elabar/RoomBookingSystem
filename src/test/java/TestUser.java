import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.junit.Test;

@RunWith(JUnitParamsRunner.class)
public class TestUser {
	
	private Object[] users() {
		User user1 = new User("Lee","VIP");
		User user2 = new User("Angeline","normal");
		User user3 = new User("Hew Yan","non");
		User user4 = new User("Wai Kit","VIP");
		User user5 = new User("Shu Jie","normal");
		User user6 = new User("Thoong","non");
		return new Object[] {
			new Object[] {user1,3},
			new Object[] {user2,2},
			new Object[] {user3,1},
			new Object[] {user4,3},
			new Object[] {user5,2},
			new Object[] {user6,1}
		};
	}
	
	@Test
	@Parameters(method = "users")
	public void testUserConstructor(User user,int maxRoom) {
		assertEquals(user.getMaxNumberOfBookedRoom(),maxRoom);
	}
	
	private Object[] dataForDecrease(){
		User user1 = new User("Lee","VIP");
		User user2 = new User("Angeline","normal");
		User user3 = new User("Hew Yan","non");
		return new Object[] {
				new Object[] {user1,3,-3},
				new Object[] {user2,2,-2},
				new Object[] {user3,1,-1},
		};
	}
	@Test
	@Parameters(method = "dataForDecrease")
	public void testDecreseNumberOfBookedRoom(User user,int numberOfLoop,int numberOfBookedRoom) {
		for(int i = 0;i < numberOfLoop;i++) {
			user.decreaseNumberOfBookedRoom();
		}
		assertEquals(user.getNumberOfBookedRoom(),numberOfBookedRoom);
	}
	
	private Object[] dataForIncrease(){
		User user1 = new User("Lee","VIP");
		User user2 = new User("Angeline","normal");
		User user3 = new User("Hew Yan","non");
		return new Object[] {
				new Object[] {user1,3,3},
				new Object[] {user2,2,2},
				new Object[] {user3,1,1},
		};
	}
	
	@Test
	@Parameters(method = "dataForIncrease")
	public void testAddNumberOfBookedRoom(User user,int numberOfLoop,int numberOfBookedRoom) {
		for(int i = 0;i < numberOfLoop;i++) {
			user.addNumberOfBookedRoom();
		}
		assertEquals(user.getNumberOfBookedRoom(),numberOfBookedRoom);
	}
	
	private Object[] dataForExclReward(){
		User user1 = new User("Lee","VIP");
		User user2 = new User("Angeline","normal");
		User user3 = new User("Hew Yan","non");
		return new Object[] {
				new Object[] {user1,true},
				new Object[] {user2,false},
				new Object[] {user3,true},
		};
	}
	
	@Test
	@Parameters(method = "dataForExclReward")
	public void testExclReward(User user,boolean excl_reward) {
		
		user.setExcl_reward(excl_reward);
		if(excl_reward) {
			assertTrue(user.getExclReward());
		}else {
			assertFalse(user.getExclReward());
		}
	}
}
