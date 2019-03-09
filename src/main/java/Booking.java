
import java.util.ArrayList;

public class Booking {
	private ArrayList<Room> rooms;
	private User aUser;
	
	public void setBooking(User user,Room room) {
		if(user.getMaxNumberOfBookedRoom() < user.getNumberOfBookedRoom()) {
			if("VIP".equals(user.getMember_type())) {
				//reward??
				if(room.checkRoom("VIP")) {
					//assign to VIP room
					user.addNumberOfBookedRoom();
					
				}else if(room.checkRoom("deluxe")) {
					//assign to deluxe room
					user.addNumberOfBookedRoom();
					
				}else if(room.checkRoom("standard")) {
					//assign to standard room
					user.addNumberOfBookedRoom();
					
				}else {
					//assign to waiting list
				}
			}else if("normal".equals(user.getMember_type())) {
				if(room.checkRoom("deluxe")) {
					//assign to deluxe room
					//reward
					user.addNumberOfBookedRoom();
				}else {
					if(user.getExcl_reward()) {
						if(room.checkRoom("VIP")) {
							//assign to VIP room
							user.addNumberOfBookedRoom();
							user.setExcl_reward(false);
						}
					}else {
						if(room.checkRoom("standard")){
							//assign to normal room
							user.addNumberOfBookedRoom();
						}else {
							//assign to waiting list
						}
					}
				}
			}else if("non".equals(user.getMember_type())) {
				if(room.checkRoom("standard")) {
					//assign to standard room
					user.addNumberOfBookedRoom();
				}else {
					//assign to waiting list
				}
			}else {
				System.out.println("Reached maximum room.");
			}
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
