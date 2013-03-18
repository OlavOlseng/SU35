package models;

import java.util.ArrayList;

public class ModelTest {
	public static void main(String args[]) {
		Appointment app1 = new Appointment(1);
		app1.setDate("2013-03-18");
		app1.setDescription("M�te 1");
		app1.setLocation("NTNU");
		app1.setStartTime("12:15");
		app1.setEndTime("13:15");
		app1.setMeetingLeader("olav@epost.no");
		app1.setMeetingRoom("Drivhuset");
		
		Appointment app2 = new Appointment(2);
		app2.setDate("2013-03-18");
		app2.setDescription("M�te 2");
		app2.setLocation("NTNU");
		app2.setStartTime("12:55");
		app2.setEndTime("13:55");
		app2.setMeetingLeader("august@epost.no");
		app2.setMeetingRoom("R-bygget R-7");
		
		Appointment app3 = new Appointment(3);
		app3.setDate("2013-03-18");
		app3.setDescription("M�te 3");
		app3.setLocation("NTNU");
		app3.setStartTime("12:00");
		app3.setEndTime("13:00");
		app3.setMeetingLeader("sindre@epost.no");
		app3.setMeetingRoom("Stripa S-1");
		
		Room room1 = new Room("Drivhuset", 30);
		Room room2 = new Room("R-bygget R-7", 500);
		Room room3 = new Room("Stripa S-1", 200);
		
		Appointment app4 = new Appointment(4);
		app4.setDate("2013-03-18");
		app4.setDescription("M�te 4");
		app4.setLocation("NTNU");
		app4.setStartTime("13:05");
		app4.setEndTime("14:05");
		app4.setMeetingLeader("fredrik@epost.no");
		
		RoomManager roomManager = new RoomManager(6, app4.getDate(), app4.getStartTime(), app4.getEndTime());
		roomManager.addAppointment(app1);
		roomManager.addAppointment(app2);
		roomManager.addAppointment(app3);
		roomManager.addRoom("Drivhuset", 30);
		roomManager.addRoom("R-bygget R-7", 500);
		roomManager.addRoom("Stripa S-1", 200);
		
		String room = roomManager.pickSuitableRoom();
		app4.setMeetingRoom(room);
		
		System.out.println(app4.toString());
	}
}