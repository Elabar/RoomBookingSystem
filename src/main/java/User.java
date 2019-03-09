
public class User {
	private String name;
	private String member_type;
	private boolean excl_reward;
	private int maxNumberOfBookedRoom;
	private int numberOfBookedRoom;
	
	public String getMember_type() {
		return member_type;
	}
	
	public boolean getExcl_reward() {
		return excl_reward;
	}

	public void setExcl_reward(boolean excl_reward) {
		this.excl_reward = excl_reward;
	}

	public User(String name,String member_type) {
		this.name = name;
		this.member_type = member_type;
		
		if("VIP".equals(member_type)) {
			maxNumberOfBookedRoom = 3;
		}else if("normal".equals(member_type)) {
			maxNumberOfBookedRoom = 2;
		}else if("non".equals(member_type)) {
			maxNumberOfBookedRoom = 1;
		}
		
		this.numberOfBookedRoom = 0;
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
}
