
import java.util.ArrayList;

public class Booking {
	private User aUser;
	
	//testing purpose constructor
	public Booking(User user) {
		this.aUser = user;
	}
	
	public void setBooking(User user,Room room) {
		if(user.canBook()) {
			if("VIP".equals(user.getMember_type())) {
				if(room.checkRoom("VIP")) {
					user.addNumberOfBookedRoom();
					room.assignRoom("VIP");
				}else if(room.checkRoom("deluxe")) {
					user.addNumberOfBookedRoom();
					room.assignRoom("deluxe");
				}else if(room.checkRoom("standard")) {
					user.addNumberOfBookedRoom();
					room.assignRoom("standard");
				}else {
					user.addWaitingList();
				}
			}else if("normal".equals(user.getMember_type())) {
				if(user.getExcl_reward()) {
					if(room.checkRoom("VIP")) {
						user.addNumberOfBookedRoom();
						user.setExcl_reward(false);		//->not tested
						room.assignRoom("VIP");
					}else if(room.checkRoom("deluxe")) {
						user.addNumberOfBookedRoom();
						room.assignRoom("deluxe");
					}else if(room.checkRoom("standard")){
						user.addNumberOfBookedRoom();
						room.assignRoom("standard");
					}else {
						user.addWaitingList();
					}
				}else {
					if(room.checkRoom("deluxe")) {
						user.addNumberOfBookedRoom();
						room.assignRoom("deluxe");
					}else if(room.checkRoom("standard")){
						user.addNumberOfBookedRoom();
						room.assignRoom("standard");
					}else {
						user.addWaitingList();
					}	
				}
			}else if("non".equals(user.getMember_type())) {
				if(room.checkRoom("standard")) {
					user.addNumberOfBookedRoom();
					room.assignRoom("standard");
				}else {
					user.addWaitingList();
				}
			}else {
				System.out.println("Unexpected user type.");
				throw new IllegalArgumentException("Such user type is not expected");
			}
		}else {
			System.out.println("User hits booking limit");
			throw new IllegalArgumentException("User hits booking limit");
		}
	}
	
	public void cancelBooking(User user,Room room) {
		if(user.getNumberOfBookedRoom() > 0) {
			user.decreaseNumberOfBookedRoom();
		}else {
			System.out.println("No booked room.");
		}
	}
}
