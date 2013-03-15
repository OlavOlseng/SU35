package models;

public class Room {
	private final int meetingRoomID;
	private final int roomSize;
	private String name;
	
	public Room(int meetingRoomID, int roomSize) {
		this.meetingRoomID = meetingRoomID;
		this.roomSize = roomSize;
		name = "";
	}
	
	public int getMeetingRoomID() {
		return meetingRoomID;
	}
	
	public int roomSize() {
		return roomSize;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return "Room ID:\t\t\t" + meetingRoomID + "\nRoom Name:\t\t\t" + name + "\nRoom Size:\t\t\t" + roomSize;
	}
}
