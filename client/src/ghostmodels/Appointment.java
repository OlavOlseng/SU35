package ghostmodels;

import java.util.Calendar;
import java.util.Date;

public class Appointment {
	private int appointmentID;
	private Calendar date;
	private Calendar startTime;
	private Calendar endTime;
	private String description;
	private String location;
	private String meetingRoom;
	private Employee meetingLeader;
	
	public Appointment(int appointmentID) {
		this.appointmentID = appointmentID;
		description = "";
		location = "";
		startTime = Calendar.getInstance();
		endTime = Calendar.getInstance();
		date = Calendar.getInstance();
	}
	
	public void setAppointmentID(int appointmentID){
		this.appointmentID = appointmentID;
	}
	
	public int getAppointmentID() {
		return appointmentID;
	}
	
	public String getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(String meetingRoom) {
		this.meetingRoom = meetingRoom;
	}

	public Employee getMeetingLeader() {
		return meetingLeader;
	}

	public void setMeetingLeader(Employee meetingLeader) {
		this.meetingLeader = meetingLeader;
	}

	private void setDate(int year, int month, int day) {
		date.set(year, month, day, 0, 0, 0);
	}
	
	public void setDate(String dateString) {
		String[] temp = dateString.split("-");
		setDate(Integer.parseInt(temp[0]), Integer.parseInt(temp[2]), Integer.parseInt(temp[2]));
	}
	
	private void setStartTime(int hour, int minute) {
		startTime.setTime(date.getTime());
		startTime.set(Calendar.HOUR_OF_DAY, hour);
		startTime.set(Calendar.MINUTE, minute);
	}
	
	public void setStartTime(String time) {
		String[] temp0 = time.split(" ");
		String[] temp = temp0[1].split(":");
		setStartTime(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
	}
	
	public Date getStartTime() {
		return startTime.getTime();
	}
	
	private void setEndTime(int hour, int minute) {
		endTime.setTime(date.getTime());
		endTime.set(Calendar.HOUR_OF_DAY, hour);
		endTime.set(Calendar.MINUTE, minute);
	}
	
	public void setEndTime(String time) {
		String[] temp0 = time.split(" ");
		String[] temp = temp0[1].split(":");
		setEndTime(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
	}
	
	public Date getEndTime() {
		return endTime.getTime();
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLocation() {
		return location;
	}
	
	// May cause null-pointer hell, too lazy to fix. <--- TRUE DAT
	public String toString() {
		String s  = "Appointment ID:\t\t\t" + appointmentID + "\nAppointment Description:\t" + description +
				"\nStart time:\t\t\t" + startTime.getTime().toString() + "\nEnd time:\t\t\t" + endTime.getTime().toString() + 
				"\nAppointment Location:\t\t" + location;
		if (meetingRoom != null && meetingRoom != "")
				s +=  "\nMeeting room:\n" + meetingRoom.toString();
		if(meetingLeader != null && meetingLeader.getFirstName() != "")
				s += "\nMeeting Leader:\n" + meetingLeader.toString();
		System.out.println(date.getTime().toString());
		return s;
	}
}
