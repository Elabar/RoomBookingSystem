package utar.edu.my;
import java.util.ArrayList;

public class WaitingList {
	private ArrayList<User> users;
	
	public WaitingList() {
		this.users = new ArrayList<User>();
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public void removeUser(User user) {
		users.remove(user);
	}
	
	public ArrayList<User> getWaiting() {
		return users;
	}
}

