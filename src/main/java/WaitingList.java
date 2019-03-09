import java.util.ArrayList;

public class WaitingList {
	private ArrayList<User> VIP;
	private ArrayList<User> member;
	private ArrayList<User> normal;
	private User user;
	
	public WaitingList() {
		VIP = new ArrayList<User>();
		member = new ArrayList<User>();
		normal = new ArrayList<User>();
	}
	
	public void addWaiting(User user) {
		if("VIP".equals(user.getMember_type())) {
			VIP.add(user);
		}else if("member".equals(user.getMember_type())) {
			member.add(user);
		}else if("normal".equals(user.getMember_type())) {
			normal.add(user);
		}
	}
	
	public ArrayList<User> getWaiting(){
		if("VIP".equals(user.getMember_type())) {
			return VIP;
		}else if("member".equals(user.getMember_type())) {
			return member;
		}else if("normal".equals(user.getMember_type())) {
			return normal;
		}else {
			return null;
		}
	}
	
	public void removeWaiting(User user) {
		if("VIP".equals(user.getMember_type())) {
			VIP.remove(user);
		}else if("member".equals(user.getMember_type())) {
			member.remove(user);
		}else if("normal".equals(user.getMember_type())) {
			normal.remove(user);
		}
	}
}
