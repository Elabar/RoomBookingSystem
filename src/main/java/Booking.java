public class Booking {
	private User aUser;
	
	//testing purpose constructor
	public Booking(User user) {
		this.aUser = user;
	}
	
	public void setBooking(User user,Room room) {
		if(user.canBook()) {
			if("VIP".equals(user.getMemberType())) {
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
					user.addNumberOfBookedRoom();
					WaitingList wl = new WaitingList(user);
					user.addWaitingList(wl);
				}
			}else if("normal".equals(user.getMemberType())) {
				if(user.getExclReward()) {
					if(room.checkRoom("VIP")) {
						user.addNumberOfBookedRoom();
						user.setExclReward(false);
						room.assignRoom("VIP");
					}else if(room.checkRoom("deluxe")) {
						user.addNumberOfBookedRoom();
						room.assignRoom("deluxe");
					}else if(room.checkRoom("standard")){
						user.addNumberOfBookedRoom();
						room.assignRoom("standard");
					}else {
						user.addNumberOfBookedRoom();
						WaitingList wl = new WaitingList(user);
						user.addWaitingList(wl);
					}
				}else {
					if(room.checkRoom("deluxe")) {
						user.addNumberOfBookedRoom();
						room.assignRoom("deluxe");
					}else if(room.checkRoom("standard")){
						user.addNumberOfBookedRoom();
						room.assignRoom("standard");
					}else {
						user.addNumberOfBookedRoom();
						WaitingList wl = new WaitingList(user);
						user.addWaitingList(wl);
					}	
				}
			}else if("non".equals(user.getMemberType())) {
				if(room.checkRoom("standard")) {
					user.addNumberOfBookedRoom();
					room.assignRoom("standard");
				}else {
					user.addNumberOfBookedRoom();
					WaitingList wl = new WaitingList(user);
					user.addWaitingList(wl);
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
	
	//assuming that cancelBooking only cancel user's reservation but not removing user from waiting list
	public void cancelBooking(User user,Room room,String roomType) {
		if(user.getNumberOfBookedRoom() > 0) {
			user.decreaseNumberOfBookedRoom();
			room.removeReserve(roomType);
		}else {
			System.out.println("No booked room.");
			throw new IllegalArgumentException("User do not have booked room");
		}
	}
}
