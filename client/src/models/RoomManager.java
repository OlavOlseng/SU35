package models;

import java.util.ArrayList;
import java.util.Calendar;

public class RoomManager {
	private final int minRoomSize;
	private final Calendar date;
	private final Calendar startTime;
	private final Calendar endTime;
	private ArrayList<String> rooms;
	private ArrayList<Appointment> appointments;
	
	public RoomManager(int minRoomSize, Calendar date, Calendar startTime, Calendar endTime) {
		this.minRoomSize = minRoomSize;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		rooms = new ArrayList<String>();
		appointments = new ArrayList<Appointment>();
	}
	
	private void addRoom(String room, int roomSize) {
		if(roomSize >= minRoomSize) {
			rooms.add(room);
		}
	}
	
	private void addAppointment(Appointment appointment) {
		if(appointment.getStartTime().getTime().getYear() == date.getTime().getYear() &&
				appointment.getStartTime().getTime().getMonth() == date.getTime().getMonth() &&
				appointment.getStartTime().getTime().getDate() == date.getTime().getDate()) {
				appointments.add(appointment);
		}
	}
	
	public ArrayList<String> findSuitableRooms() {
		ArrayList<String> suitableRooms = rooms;
		for(Appointment appointment : appointments) {
			if((startTime.after(appointment.getStartTime()) && startTime.before(appointment.getEndTime())) ||
				(endTime.after(appointment.getStartTime()) && endTime.before(appointment.getEndTime()))) {
				suitableRooms.remove(appointment.getMeetingRoom());
			}
		}
		return suitableRooms;
	}
	
	public String pickSuitableRoom() {
		return findSuitableRooms().get(0);
	}
}
