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
		if("VIP".equals(user.getMemberType())) {
			VIP.add(user);
		}else if("member".equals(user.getMemberType())) {
			member.add(user);
		}else if("normal".equals(user.getMemberType())) {
			normal.add(user);
		}
	}
	
	public ArrayList<User> getWaiting(){
		if("VIP".equals(user.getMemberType())) {
			return VIP;
		}else if("member".equals(user.getMemberType())) {
			return member;
		}else if("normal".equals(user.getMemberType())) {
			return normal;
		}else {
			return null;
		}
	}
	
	public void removeWaiting(User user) {
		if("VIP".equals(user.getMemberType())) {
			VIP.remove(user);
		}else if("member".equals(user.getMemberType())) {
			member.remove(user);
		}else if("normal".equals(user.getMemberType())) {
			normal.remove(user);
		}
	}
}
