package models;

import java.util.Calendar;

public class Appointment {
	private int appointmentID;
	private Calendar date;
	private Calendar startTime;
	private Calendar endTime;
	private String title;
	private String description;
	private String location;
	private String meetingRoom;
	private String meetingLeader;

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
	
	public String getMeetingLeader() {
		return meetingLeader;
	}

	public void setMeetingLeader(String meetingLeader) {
		this.meetingLeader = meetingLeader;
	}
	
	private void setDate(int year, int month, int day) {
		date.set(year, month-1, day, 0, 0, 0);
		startTime.set(year, month-1, day);
		endTime.set(year, month-1, day);
	}

	public void setDate(String dateString) {
		String[] temp = dateString.split("-");
		setDate(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
	}

	private void setStartTime(int hour, int minute) {
		startTime.setTime(date.getTime());
		startTime.set(Calendar.HOUR_OF_DAY, hour);
		startTime.set(Calendar.MINUTE, minute);
	}

	public void setStartTime(String time) {
		String[] temp = time.split(":");
		setStartTime(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
	}

	private void setEndTime(int hour, int minute) {
		endTime.setTime(date.getTime());
		endTime.set(Calendar.HOUR_OF_DAY, hour);
		endTime.set(Calendar.MINUTE, minute);
	}

	public void setEndTime(String time) {
		String[] temp = time.split(":");
		setEndTime(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
	}

	public String getFormattedDate() {
		String s = "";
		s += (date.getTime().getYear()+1900) + "-";
		int month = date.getTime().getMonth();
		if(month < 9) {
			s += "0";
		}
		s += (month+1) + "-";
		int day = date.getTime().getDate();
		if(day < 10) {
			s += "0";
		}
		s += (day);
		return s;
	}

	public String getFormattedStartTime() {
		String s = "";
		int hours = startTime.getTime().getHours();
		if(hours < 10) {
			s += "0";
		}
		s += hours + ":";
		int minutes = startTime.getTime().getMinutes();
		if(minutes < 10) {
			s += "0";
		}
		s += minutes;
		return s;
	}

	public String getFormattedEndTime() {
		String s = "";
		int hours = endTime.getTime().getHours();
		if(hours < 10) {
			s += "0";
		}
		s += hours + ":";
		int minutes = endTime.getTime().getMinutes();
		if(minutes < 10) {
			s += "0";
		}
		s += minutes;
		return s;
	}

	public Calendar getDate() {
		return date;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public Calendar getEndTime() {
		return endTime;
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
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// May cause null-pointer hell, too lazy to fix. <--- TRUE DAT
	public String toString() {
		String s = "Appointment ID:\t\t\t" + appointmentID + "\nAppointment Description:\t" + description +
				"\nStart time:\t\t\t" + startTime.getTime().toString() + "\nEnd time:\t\t\t" + endTime.getTime().toString() + 
				"\nAppointment Location:\t\t" + location;
		if (meetingRoom != null && meetingRoom != "")
				s += "\nMeeting room:\t\t\t" + meetingRoom;
		if(meetingLeader != null && meetingLeader != "")
				s += "\nMeeting Leader:\t\t\t" + meetingLeader;
		return s;
	}

	public void update(Appointment a) {
		setDate(a.getFormattedDate());
		setStartTime(a.getFormattedStartTime());
		setEndTime(a.getFormattedEndTime());
		setTitle(a.getTitle());
		setDescription(a.getDescription());
		setLocation(a.getLocation());
		setMeetingLeader(a.getMeetingLeader());
		setMeetingRoom(a.getMeetingRoom());
	}
}
