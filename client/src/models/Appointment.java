package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Appointment {
	private int appointmentID;
	private Calendar startTime;
	private Calendar endTime;
	private String description;
	private String location;
	private String meetingRoom;
	private String meetingLeader;
	
	public Appointment(int appointmentID) {
		this.appointmentID = appointmentID;
		startTime = Calendar.getInstance();
		endTime = Calendar.getInstance();
		description = "";
		location = "";
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
		startTime.set(Calendar.YEAR, year+1900);
		startTime.set(Calendar.MONTH, month);
		startTime.set(Calendar.DAY_OF_MONTH, day);
		endTime.set(Calendar.YEAR, year+1900);
		endTime.set(Calendar.MONTH, month);
		endTime.set(Calendar.DAY_OF_MONTH, day);
	}
	
	@SuppressWarnings("deprecation")
	public void setDate(String dateString) {
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateString);
			setDate(date.getYear(), date.getMonth(), date.getDay());
		}
		catch(ParseException pe) {
			System.out.println(pe.getMessage());
		}
	}
	
	private void setStartTime(int hour, int minute) {
		startTime.set(Calendar.HOUR_OF_DAY, hour);
		startTime.set(Calendar.MINUTE, minute);
	}
	
	@SuppressWarnings("deprecation")
	public void setStartTime(String time) {
		Date date;
		try {
			date = new SimpleDateFormat("hh:mm", Locale.ENGLISH).parse(time);
			setStartTime(date.getHours(), date.getMinutes());
		}
		catch(ParseException pe) {
			System.out.println(pe.getMessage());
		}
	}
	
	public Date getStartTime() {
		return startTime.getTime();
	}
	
	private void setEndTime(int hour, int minute) {
		endTime.set(Calendar.HOUR_OF_DAY, hour);
		endTime.set(Calendar.MINUTE, minute);
	}
	
	@SuppressWarnings("deprecation")
	public void setEndTime(String time) {
		Date date;
		try {
			date = new SimpleDateFormat("hh:mm", Locale.ENGLISH).parse(time);
			setEndTime(date.getHours(), date.getMinutes());
		}
		catch(ParseException pe) {
			System.out.println(pe.getMessage());
		}
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
	
	// May cause null-pointer hell, too lazy to fix.
	public String toString() {
		return "Appointment ID:\t\t\t" + appointmentID + "\nAppointment Description:\t" + description +
				"\nStart time:\t\t\t" + startTime.getTime().toString() + "\nEnd time:\t\t\t" + endTime.getTime().toString() + 
				"\nAppointment Location:\t\t" + location + "\nMeeting room:\n" + meetingRoom.toString() + 
				"\nMeeting Leader:\n" + meetingLeader.toString();
	}
}
