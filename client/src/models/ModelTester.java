package models;

public class ModelTester {
	public static void main(String args[]) {
		// Test employee
		Employee employee = new Employee("augustak@stud.ntnu.no", "August", "Kvernmo", "47713553", "47713553");
		// Test meeting room
		Room room = new Room(101, 10);
		room.setName("Sesam sitt lokale");
		// Test meeting
		Appointment meeting = new Appointment(1);
		meeting.setDate("2013-03-13");
		meeting.setStartTime("16:05");
		meeting.setEndTime("19:05");
		meeting.setDescription("ekskursjon til Sesam");
		meeting.setLocation("Sesam gatekjøkken");
		meeting.setMeetingLeader(employee);
		meeting.setMeetingRoom(room);
		System.out.println(meeting.toString());
	}
}
