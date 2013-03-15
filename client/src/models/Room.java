package models;

public class Room {
	private int roomSize;
	private String name;
	
	public Room(String name, int roomSize) {
		this.name = name;
		this.roomSize = roomSize;
	}
	public void setRoomSize(int roomSize){
		this.roomSize = roomSize;
	}
	
	public int getRoomSize() {
		return roomSize;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return "Room Name:\t\t\t" + name + "\nRoom Size:\t\t\t" + roomSize;
	}
}
