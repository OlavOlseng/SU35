package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class RoomManager {
	public static class NoAvailableRooms extends Exception {
		public String getMessage() {
			return "No available rooms for booking";
		}
	}
	
	private static HashSet<String> removeRooms(HashSet<String> roomIDs, int minSize) {
		for(String roomID : roomIDs) {
			if(ApplicationModel.getInstance().getRoom(roomID).getRoomSize() < minSize) {
				roomIDs.remove(roomID);
			}
		}
		return roomIDs;
	}
	
	private static ArrayList<Appointment> removeAppointments(HashSet<Integer> appointmentIDs, Calendar date) {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		for(Integer ID : appointmentIDs) {
			Appointment appointment = ApplicationModel.getInstance().getAppointment(ID);
			if(appointment.getStartTime().getTime().getYear() == date.getTime().getYear() &&
					appointment.getStartTime().getTime().getMonth() == date.getTime().getMonth() &&
					appointment.getStartTime().getTime().getDate() == date.getTime().getDate()) {
					appointments.add(appointment);
			}
		}
		return appointments;
	}
	
	public static HashSet<String> findSuitableRooms(int minSize, Calendar date, Calendar startTime, Calendar endTime) throws NoAvailableRooms {
		HashSet<String> suitableRooms = ApplicationModel.getInstance().getRooms();
		suitableRooms = removeRooms(suitableRooms, minSize);
		HashSet<Integer> appointmentIDs = ApplicationModel.getInstance().getAppointments();
		ArrayList<Appointment> appointments = removeAppointments(appointmentIDs, date);
		for(Appointment appointment : appointments) {
			if((startTime.after(appointment.getStartTime()) && startTime.before(appointment.getEndTime())) ||
				(endTime.after(appointment.getStartTime()) && endTime.before(appointment.getEndTime()))) {
				suitableRooms.remove(appointment.getMeetingRoom());
			}
		}
		if(suitableRooms.isEmpty()) {
			throw new NoAvailableRooms();
		}
		return suitableRooms;
	}
	
	public static String pickSuitableRoom(int minSize, Calendar date, Calendar startTime, Calendar endTime) throws NoAvailableRooms {
		String roomID = findSuitableRooms(minSize, date, startTime, endTime).iterator().next();
		if(roomID==null) {
			throw new NoAvailableRooms();
		}
		return roomID;
	}
}
