package utar.edu.my;
import java.util.ArrayList;

public class WaitingList {
	private ArrayList<User> VIP;
	private ArrayList<User> normal;
	private ArrayList<User> non;
	private User user;
	
	public int getNumOfUserInList() {
		if("VIP".equals(user.getMemberType())) {
			return VIP.size();
		}else if("normal".equals(user.getMemberType())) {
			return normal.size();
		}else{
			return non.size();
		}
		/*
		 * Section below is not needed since it has filtered by
		 * the construtor
		else {
			throw new IllegalArgumentException("Unexpected member type");
		}
		*/
	}
	
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
		}else{
			non.add(user);
		}
		/*
		 * Section below is not needed since it has filtered by
		 * the construtor
		else {
			throw new IllegalArgumentException("Unexpected member type");
		}
		*/
	}
	
	//Since user will have many waiting list
	//It means one waiting list will have one user only
	//So we just need to return the only one user
	public User getWaiting(){
		return user;
	}
	
	public void removeWaiting() {
		if("VIP".equals(user.getMemberType())) {
			VIP.remove(user);
		}else if("normal".equals(user.getMemberType())) {
			normal.remove(user);
		}else {
			non.remove(user);
		}
		/*
		 * Section below is not needed since it has filtered by
		 * the construtor
		else {
			throw new IllegalArgumentException("Unexpected member type");
		}
		*/
	}
}
