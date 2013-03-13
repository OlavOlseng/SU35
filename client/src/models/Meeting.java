package models;

public class Meeting extends Appointment {
	private MeetingRoom meetingRoom;
	private Employee meetingLeader;
	
	public Meeting(int appointmentID) {
		super(appointmentID);
	}
	
	public void setMeetingRoom(MeetingRoom meetingRoom) {
		this.meetingRoom = meetingRoom;
	}
	
	public MeetingRoom getMeetingRoom() {
		return meetingRoom;
	}
	
	public void setMeetingLeader(Employee meetingLeader) {
		this.meetingLeader = meetingLeader;
	}
	
	public Employee getMeetingLeader() {
		return meetingLeader;
	}
	
	public String toString() {
		return "Start time:\t" + startTime.toString() + "\nEnd time:\t" + endTime.toString() + 
				"\nMeeting room:\t" + meetingRoom.toString() + "\nMeeting Leader:\t" + meetingLeader.toString();
	}
}
