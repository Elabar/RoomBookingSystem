
public class Booking {
	
	public Booking() {
		
	}
	
	public void setBooking(User user,Room room) {
		if(user.getMember_type() == "VIP") {
			//reward??
			if(room.checkRoom("VIP")) {
				//assign to VIP room
				
			}else if(room.checkRoom("deluxe")) {
				//assign to deluxe room
				
			}else if(room.checkRoom("standard")) {
				//assign to standard room
				
			}else {
				//assign to waiting list
				
			}
		}
		
		if(user.getMember_type() == "normal") {
			if(room.checkRoom("deluxe")) {
				//assign to deluxe room
				//reward
			}else {
				if(user.getExcl_reward()) {
					if(room.checkRoom("VIP")) {
						//assign to vip room
						user.setExcl_reward(false);
					}
				}else {
					if(room.checkRoom("standard")){
						//assign to normal room
					}else {
						//assign to waiting list
					}
				}
			}
		}
		
		if(user.getMember_type() == "non") {
			if(room.checkRoom("standard")) {
				//assign to standard room
			}else {
				//assign to waiting list
			}
		}
	}
	
	public void cancelBooking(User user) {
		
	}
}
