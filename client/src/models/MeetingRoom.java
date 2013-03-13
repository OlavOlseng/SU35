package models;

public class MeetingRoom {
	private final int meetingRoomID;
	private final int roomSize;
	private String name;
	
	public MeetingRoom(int meetingRoomID, int roomSize) {
		this.meetingRoomID = meetingRoomID;
		this.roomSize = roomSize;
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
		return "ID:\t" + meetingRoomID + "\nName:\t" + name + "\nRoom Size:\t" + roomSize;
	}
}
