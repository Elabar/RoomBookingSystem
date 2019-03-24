import java.util.ArrayList;
import java.util.Random;

public class User {
	private String name;
	private String memberType;
	private boolean exclReward;
	private int maxNumberOfBookedRoom;
	private int numberOfBookedRoom;
	private ArrayList<Booking> bookings;
	private ArrayList<WaitingList> waitingLists;
	
	public String getMemberType() {
		return memberType;
	}
	
	public boolean getExclReward() {
		return exclReward;
	}

	public void setExcl_reward(boolean exclReward) {
		this.exclReward = exclReward;
	}
	
	public User(String name,String memberType,Random random) {
		this.name = name;
		this.memberType = memberType;
		this.numberOfBookedRoom = 0;
		int randomNum = random.nextInt((10 - 1)) + 1;

		exclReward = randomNum < 5;
		
		if("VIP".equals(memberType)) {
			maxNumberOfBookedRoom = 3;
			
		}else if("normal".equals(memberType)) {
			maxNumberOfBookedRoom = 2;
		}else if("non".equals(memberType)) {
			maxNumberOfBookedRoom = 1;
			exclReward = false;
		}else {
			throw new IllegalArgumentException("Unexpected member type");
		}
	}

	public void addNumberOfBookedRoom() {
		numberOfBookedRoom++;
	}
	
	public int getNumberOfBookedRoom() {
		return numberOfBookedRoom;
	}
	
	public int getMaxNumberOfBookedRoom() {
		return maxNumberOfBookedRoom;
	}
	
	public void decreaseNumberOfBookedRoom() {
		numberOfBookedRoom--;
	}
	
	public boolean canBook() {
		return numberOfBookedRoom < maxNumberOfBookedRoom;
	}
	
	public void addWaitingList() {
		//to be implement
	}
}
