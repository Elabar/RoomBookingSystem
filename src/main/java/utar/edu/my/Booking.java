package utar.edu.my;

import java.util.ArrayList;

public class Booking {
	private ArrayList<String> rooms;
	private ArrayList<String> waitingLists;
	
	public ArrayList<String> getRooms(){
		return rooms;
	}
	
	public ArrayList<String> getWaitingLists(){
		return waitingLists;
	}
	
	public Booking() {
		this.rooms = new ArrayList<String>();
		this.waitingLists = new ArrayList<String>();
	}
	
	public void setBooking(User user,int numberOfBooking,WaitingList wl,Room room) {
		if(numberOfBooking < 4 && numberOfBooking >0) {
			for(int i = 0;i < numberOfBooking;i++) {
				if(user.canBook()) {
					if("vip".equals(user.getMemberType())) {
						if(room.checkRoom("vip")) {
							user.addNumberOfBookedRoom();
							room.assignRoom("vip");
							rooms.add("vip");
						}else if(room.checkRoom("deluxe")) {
							user.addNumberOfBookedRoom();
							room.assignRoom("deluxe");
							rooms.add("deluxe");
						}else if(room.checkRoom("standard")) {
							user.addNumberOfBookedRoom();
							room.assignRoom("standard");
							rooms.add("standard");
						}else {
							user.addNumberOfBookedRoom();
							wl.addUser(user);
							waitingLists.add("waitingList");
						}
					}else if("normal".equals(user.getMemberType())) {
						if(user.getReward()) {
							if(room.checkRoom("vip")) {
								user.addNumberOfBookedRoom();
								user.setReward(false);
								room.assignRoom("vip");
								rooms.add("vip");
							}else if(room.checkRoom("deluxe")) {
								user.addNumberOfBookedRoom();
								room.assignRoom("deluxe");
								rooms.add("deluxe");
							}else if(room.checkRoom("standard")){
								user.addNumberOfBookedRoom();
								room.assignRoom("standard");
								rooms.add("standard");
							}else {
								user.addNumberOfBookedRoom();
								wl.addUser(user);
								waitingLists.add("waitingList");
							}
						}else {
							if(room.checkRoom("deluxe")) {
								user.addNumberOfBookedRoom();
								room.assignRoom("deluxe");
								rooms.add("deluxe");
							}else if(room.checkRoom("standard")){
								user.addNumberOfBookedRoom();
								room.assignRoom("standard");
								rooms.add("standard");
							}else {
								user.addNumberOfBookedRoom();
								wl.addUser(user);
								waitingLists.add("waitingList");
							}	
						}
					}else if("non".equals(user.getMemberType())) {
						if(room.checkRoom("standard")) {
							user.addNumberOfBookedRoom();
							room.assignRoom("standard");
							rooms.add("standard");
						}else {
							user.addNumberOfBookedRoom();
							wl.addUser(user);
							waitingLists.add("waitingList");
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
		}else {
			System.out.println("Unaceptable time");
			throw new IllegalArgumentException("Unaceptable time");
		}
	}
	
	public void cancelBooking(User user,Room room,WaitingList wl) {
		for(int i = 0;i < rooms.size();i++) {
			if(rooms.get(i).equals("vip")){
				room.resignRoom("vip");
				user.decreaseNumberOfBookedRoom();
			}else if(rooms.get(i).equals("deluxe")){
				room.resignRoom("deluxe");
				user.decreaseNumberOfBookedRoom();
			}else {
				room.resignRoom("standard");
				user.decreaseNumberOfBookedRoom();
			}
		}
		
		for(int i = 0;i < waitingLists.size();i++) {
			wl.removeUser(user);
			user.decreaseNumberOfBookedRoom();
		}
	}
	
	public void printBooking(User user,Printer printer) {
		for(int i = 0;i < rooms.size();i++) {
			printer.printInfo(user.getName(), user.getMemberType(), rooms.get(i));
		}
	}
}
