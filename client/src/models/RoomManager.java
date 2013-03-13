package models;

import java.util.ArrayList;

public class RoomManager {
	private int minRoomSize;
	private ArrayList<MeetingRoom> meetingRooms;
	
	public RoomManager(int minRoomSize) {
		this.minRoomSize = minRoomSize;
	}
}
