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
		  //----------------------------VIP---------------------------
		  //new Object[] {canBook,expectedRoom,memberType,reward,VIPRoom, deluxeRoom,standardRoom,isAssign,
			new Object[] {true,		"VIP",		"VIP",		true,	true,		true,		 true,true},
			new Object[] {true,		"VIP",		"VIP",		true,	true,		true,		 false,true},
			new Object[] {true,		"VIP",		"VIP",		true,	true,		false,		 true,true},
			new Object[] {true,		"VIP",		"VIP",		true,	true,		false,		 false,true},
			new Object[] {true,		"deluxe",	"VIP",		true,	false,		true,		 true,true},
			new Object[] {true,		"deluxe",	"VIP",		true,	false,		true,		 false,true},
			new Object[] {true,		"standard",	"VIP",		true,	false,		false,		 true,true},
			new Object[] {true,			null,	"VIP",		true,	false,		false,		 false,false},
			new Object[] {true,		"VIP",		"VIP",		false,	true,		true,		 true,true},
			new Object[] {true,		"VIP",		"VIP",		false,	true,		true,		 false,true},
			new Object[] {true,		"VIP",		"VIP",		false,	true,		false, 		 true,true},
			new Object[] {true,		"VIP",		"VIP",		false,	true,		false,		 false,true},
			new Object[] {true,		"deluxe",	"VIP",		false,	false,		true,		 true,true},
			new Object[] {true,		"deluxe",	"VIP",		false,	false,		true,		 false,true},
			new Object[] {true,		"standard",	"VIP",		false,	false,		false,		 true,true},
			new Object[] {true,			null,	"VIP",		false,	false,		false,		 false,false},
		  //----------------------------normal---------------------------
			new Object[] {true,		"VIP",		"normal",	true,	true,		true,		 true,true},
			new Object[] {true,		"VIP",		"normal",	true,	true,		true,		 false,true},
			new Object[] {true,		"VIP",		"normal",	true,	true,		false,		 true,true},
			new Object[] {true,		"VIP",		"normal",	true,	true,		false,		 false,true},
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
	public void testSetBooking(boolean canBook,String expectedRoomType,String memberType,boolean reward,boolean VIPAvailability,boolean deluxeAvailability,boolean standardAvailability,boolean isAssign) {
		User user = mock(User.class);
		when(user.canBook()).thenReturn(canBook);
		when(user.getMember_type()).thenReturn(memberType);
		when(user.getExcl_reward()).thenReturn(reward);
		Room room = mock(Room.class);
		
		when(room.checkRoom("VIP")).thenReturn(VIPAvailability);

		when(room.checkRoom("deluxe")).thenReturn(deluxeAvailability);

		when(room.checkRoom("standard")).thenReturn(standardAvailability);

		
		Booking SUT = new Booking(user);
		SUT.setBooking(user, room);
		if(isAssign) {
			verify(user,times(1)).addNumberOfBookedRoom();
			verify(room,times(1)).assignRoom(expectedRoomType);
		}else {
			verify(user,times(1)).addWaitingList();
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetBookingInvalidMember() {
		User user = mock(User.class);
		Room room = mock(Room.class);
		when(user.canBook()).thenReturn(true);
		when(user.getMember_type()).thenReturn("asd");
		Booking SUT = new Booking(user);
		SUT.setBooking(user, room);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetBookingCantBook() {
		User user = mock(User.class);
		Room room = mock(Room.class);
		when(user.canBook()).thenReturn(false);
		Booking SUT = new Booking(user);
		SUT.setBooking(user, room);
	}
}
