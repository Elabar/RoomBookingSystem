import java.util.ArrayList;

public class WaitingList {
	private ArrayList<User> VIP;
	private ArrayList<User> normal;
	private ArrayList<User> non;
	private User user;
	
	public WaitingList(User user) {
		this.user = user;
		if("VIP".equals(user.getMemberType())) {
			VIP = new ArrayList<User>();
			addWaiting();
		}else if("normal".equals(user.getMemberType())) {
			normal = new ArrayList<User>();
			addWaiting();
		}else if("non".equals(user.getMemberType())) {
			non = new ArrayList<User>();
			addWaiting();
		}else {
			throw new IllegalArgumentException("Unexpected member type");
		}
	}
	
	public void addWaiting() {
		if("VIP".equals(user.getMemberType())) {
			VIP.add(user);
		}else if("normal".equals(user.getMemberType())) {
			normal.add(user);
		}else if("non".equals(user.getMemberType())) {
			non.add(user);
		}else {
			throw new IllegalArgumentException("Unexpected member type");
		}
	}
	
	public ArrayList<User> getWaiting(){
		if("VIP".equals(user.getMemberType())) {
			return VIP;
		}else if("normal".equals(user.getMemberType())) {
			return normal;
		}else if("non".equals(user.getMemberType())) {
			return non;
		}else {
			return null;
		}
	}
	
	public void removeWaiting() {
		if("VIP".equals(user.getMemberType())) {
			VIP.remove(user);
		}else if("normal".equals(user.getMemberType())) {
			normal.remove(user);
		}else if("non".equals(user.getMemberType())) {
			non.remove(user);
		}else {
			throw new IllegalArgumentException("Unexpected member type");
		}
	}
}
