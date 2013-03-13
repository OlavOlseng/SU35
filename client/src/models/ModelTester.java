package models;

public class ModelTester {
	public static void main(String args[]) {
		// Test employee
		Employee employee = new Employee("augustak@stud.ntnu.no", "August", "Kvernmo", "47713553", "47713553");
		// Test meeting room
		MeetingRoom meetingRoom = new MeetingRoom(101, 10);
		meetingRoom.setName("Sesam sitt lokale");
		// Test meeting
		Meeting meeting = new Meeting(1);
		meeting.setDate("2013-03-13");
		meeting.setStartTime("16:05");
		meeting.setEndTime("19:05");
		meeting.setDescription("ekskursjon til Sesam");
		meeting.setLocation("Sesam gatekjøkken");
		meeting.setMeetingLeader(employee);
		meeting.setMeetingRoom(meetingRoom);
		System.out.println(meeting.toString());
	}
}
