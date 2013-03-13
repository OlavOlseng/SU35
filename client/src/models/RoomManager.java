package models;

import java.util.ArrayList;

public class RoomManager {
	private int minRoomSize;
	private ArrayList<MeetingRoom> meetingRooms;
	
	public RoomManager(int minRoomSize) {
		this.minRoomSize = minRoomSize;
	}
	
	public ArrayList<MeetingRoom> findSuitableRooms() {
		String query = "SELECT...";
		/*
		 * send query to server
		 */
		return meetingRooms;
	}
}
