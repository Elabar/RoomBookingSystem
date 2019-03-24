import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import java.util.Random;

@RunWith(JUnitParamsRunner.class)
public class TestUser {
	
	private Object[] users() {
		return new Object[] {
			new Object[] {"name1","VIP",3,3,true},
			new Object[] {"name2","normal",2,3,true},
			new Object[] {"name2","non",1,3,false},
			new Object[] {"name1","VIP",3,7,false},
			new Object[] {"name2","normal",2,7,false},
			new Object[] {"name2","non",1,7,false}
		};
	}
	
	@Test
	@Parameters(method = "users")
	public void testUserConstructor(String name,String memberType,int maxRoom,int randomNum,boolean expectedReward) {
		Random random = mock(Random.class);
		when(random.nextInt(anyInt())).thenReturn(randomNum);
		User SUT = new User(name,memberType,random);
		assertEquals(SUT.getMaxNumberOfBookedRoom(),maxRoom);
		assertEquals(expectedReward,SUT.getExclReward());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidUserConstructor() {
		Random random = new Random();
		User SUT = new User("name1","asd",random);
	}
	
	private Object[] dataForDecrease(){
		return new Object[] {
				new Object[] {3,-3},
				new Object[] {2,-2},
				new Object[] {1,-1},
		};
	}
	
	
	@Test
	@Parameters(method = "dataForDecrease")
	public void testDecreseNumberOfBookedRoom(int numberOfLoop,int numberOfBookedRoom) {
		Random random = new Random();
		User user = new User("name1","VIP",random);
		for(int i = 0;i < numberOfLoop;i++) {
			user.decreaseNumberOfBookedRoom();
		}
		assertEquals(user.getNumberOfBookedRoom(),numberOfBookedRoom);
	}
	
	private Object[] dataForIncrease(){
		return new Object[] {
				new Object[] {3,3},
				new Object[] {2,2},
				new Object[] {1,1},
		};
	}
	
	@Test
	@Parameters(method = "dataForIncrease")
	public void testAddNumberOfBookedRoom(int numberOfLoop,int numberOfBookedRoom) {
		Random random = new Random();
		User user = new User("name1","VIP",random);
		for(int i = 0;i < numberOfLoop;i++) {
			user.addNumberOfBookedRoom();
		}
		assertEquals(user.getNumberOfBookedRoom(),numberOfBookedRoom);
	}
	
	@Test
	@Parameters
	public void testCanBook(String memberType,int numberOfBookedRoom,boolean expected) {
		Random random = new Random();
		User SUT = new User("name1",memberType,random);
		for(int i = 0;i < numberOfBookedRoom;i++) {
			SUT.addNumberOfBookedRoom();
		}
		assertEquals(expected,SUT.canBook());
	}
	
	private Object[] parametersForTestCanBook() {
		return new Object[] {
				new Object[] {"VIP",0,true},
				new Object[] {"VIP",3,false},
				new Object[] {"normal",0,true},
				new Object[] {"normal",2,false},
				new Object[] {"non",0,true},
				new Object[] {"non",1,false}
		};
	}
}
