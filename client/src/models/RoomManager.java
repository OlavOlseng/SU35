package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

public class RoomManager {
	public static class NoAvailableRooms extends Exception {
		public String getMessage() {
			return "No available rooms for booking";
		}
	}
	
	private synchronized static ArrayList<String> removeRooms(ArrayList<String> roomIDs, int minSize) {
		ArrayList<String> avaliableRooms = new ArrayList<String>();
		int minRoom = 100000000;
		for(String roomID : roomIDs) {
			Room r = ApplicationModel.getInstance().getRoom(roomID); 
			if(r.getRoomSize() >= minSize) {
				System.out.println(r.getRoomSize());
				System.out.println(minSize);
				if(r.getRoomSize() <= minRoom) {
					avaliableRooms.add(0, roomID);
					minRoom = r.getRoomSize();
				}else{
					avaliableRooms.add(roomID);
				}
			}
		}
		return avaliableRooms;
	}
	
	private synchronized static ArrayList<Appointment> removeAppointments(ArrayList<Integer> appointmentIDs, Calendar date) {
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
	
	public synchronized static ArrayList<String> findSuitableRooms(int minSize, Appointment appointment) throws NoAvailableRooms {
		Calendar date = appointment.getDate();
		Calendar startTime = appointment.getStartTime();
		Calendar endTime = appointment.getEndTime();
		ArrayList<String> suitableRooms = ApplicationModel.getInstance().getRooms();
		suitableRooms = removeRooms(suitableRooms, minSize);
		ArrayList<Integer> appointmentIDs = ApplicationModel.getInstance().getAppointments();
		ArrayList<Appointment> appointments = removeAppointments(appointmentIDs, date);
		for(Appointment app : appointments) {
			if((startTime.after(app.getStartTime()) && startTime.before(app.getEndTime())) ||
				(endTime.after(app.getStartTime()) && endTime.before(app.getEndTime()))) {
				suitableRooms.remove(app.getMeetingRoom());
			}
		}
		if(suitableRooms.isEmpty()) {
			throw new NoAvailableRooms();
		}
		return suitableRooms;
	}
	
	public synchronized static String pickSuitableRoom(int minSize, Appointment appointment) throws NoAvailableRooms {
		String roomID = findSuitableRooms(minSize, appointment).iterator().next();
		if(roomID==null) {
			throw new NoAvailableRooms();
		}
		return roomID;
	}
}
