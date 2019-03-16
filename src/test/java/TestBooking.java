import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class TestBooking {

	//room_type=VIP/deluxe/standard
	//member_type=VIP/normal/non
	private Object[] dataForSetBooking(){
		return new Object[] {
			new Object[] {"VIP",true,3,0,"VIP",1},
			new Object[] {"deluxe",true,3,0,"VIP",1},
			new Object[] {"standard",true,3,0,"VIP",1},
			
			new Object[] {"deluxe",true,2,2,"normal",0},
			new Object[] {"deluxe",true,2,1,"normal",1},
			new Object[] {"standard",false,1,0,"non",0},
			new Object[] {"standard",true,1,0,"non",1}
		};
	}
	
	@Test
	@Parameters(method = "dataForSetBooking")
	public void testSetBooking(String room_type,boolean isRoomAvailable,int maxBookedRoom,int bookedRoom,String memberType,int expected) {
		User user = mock(User.class);
		when(user.getMaxNumberOfBookedRoom()).thenReturn(maxBookedRoom);
		when(user.getNumberOfBookedRoom()).thenReturn(bookedRoom);
		when(user.getMember_type()).thenReturn(memberType);
		
		Room room = mock(Room.class);
		when(room.checkRoom(room_type)).thenReturn(isRoomAvailable);
		
		Booking SUT = new Booking(room,user);
		SUT.setBooking(user, room);
		verify(user,times(expected)).addNumberOfBookedRoom();
	}
}
