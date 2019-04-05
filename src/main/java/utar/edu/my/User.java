package utar.edu.my;
import java.util.ArrayList;
import java.util.Random;

public class User {
	private String name;
	private String memberType;
	private boolean reward;
	private int maxNumberOfBookRoom;
	private int numberOfBookedRoom;
	private ArrayList<Booking> bookings;
	
	public User(String name, String memberType,Random random) {
		this.bookings = new ArrayList<Booking>();
		this.name = name;
		this.memberType = memberType;
		this.numberOfBookedRoom = 0;
		int randomNum = random.nextInt((10 - 1)) + 1;

		reward = randomNum < 5;
		if("vip".equals(memberType)) {
			maxNumberOfBookRoom = 3;
		}else if("normal".equals(memberType)) {
			maxNumberOfBookRoom = 2;
		}else if("non".equals(memberType)) {
			maxNumberOfBookRoom = 1;
			reward = false;
		}else {
			throw new IllegalArgumentException("Unexpected member type");
		}
	}
	
	public ArrayList<Booking> getBookings() {
		return bookings;
	}
	
	//original setBooking method
	public void setBooking(int numberOfBooking,WaitingList wl,Room room) {
		Booking booking = new Booking();
		booking.setBooking(this,numberOfBooking,wl,room);
		bookings.add(booking);
	}
	
	//used to test setBooking
	public void setBookingTest(int numberOfBooking,WaitingList wl,Room room,Booking booking) {
		booking.setBooking(this,numberOfBooking,wl,room);
		bookings.add(booking);
	}
	
	public void removeBooking(Booking booking,Room room,WaitingList wl) {
		booking.cancelBooking(this,room,wl);
		bookings.remove(booking);
	}
	
	public String getMemberType() {
		return memberType;
	}
	
	public boolean canBook() {
		return numberOfBookedRoom < maxNumberOfBookRoom;
	}
	
	public int getNumberOfBookedRoom() {
		return numberOfBookedRoom;
	}
	
	public void addNumberOfBookedRoom() {
		numberOfBookedRoom++;
	}
	
	public void decreaseNumberOfBookedRoom() {
		numberOfBookedRoom--;
	}
	
	public boolean getReward() {
		return reward;
	}
	
	public void setReward(boolean reward) {
		this.reward = reward;
	}
	
	public int getMaxNumberOfBookRoom() {
		return maxNumberOfBookRoom;
	}
	
	public void printBooking(User user,Printer printer) {
		for(int i = 0;i < bookings.size();i++) {
			bookings.get(i).printBooking(this, printer);
		}
	}
	
	public String getName() {
		return name;
	}
}
