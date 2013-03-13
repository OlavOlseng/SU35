package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Appointment {
	private final int appointmentID;
	private Calendar startTime;
	private Calendar endTime;
	private String description;
	private String location;
	private MeetingRoom meetingRoom;
	
	public Appointment(int appointmentID) {
		this.appointmentID = appointmentID;
		startTime = Calendar.getInstance();
		endTime = Calendar.getInstance();
	}
	
	public int getAppointmentID() {
		return appointmentID;
	}
	
	public void setDate(String dateString) {
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateString);
			startTime.setTime(date);
			endTime.setTime(date);
		}
		catch(ParseException pe) {
			System.out.println(pe.getMessage());
		}
	}
	
	/*
	public void setStartTime(int hour, int minute) {
		startTime.set(Calendar.HOUR_OF_DAY, hour);
		startTime.set(Calendar.MINUTE, minute);
	}
	*/
	
	public void setStartTime(String time) {
		Date date;
		try {
			date = new SimpleDateFormat("hh:mm", Locale.ENGLISH).parse(time);
			startTime.setTime(date);
		}
		catch(ParseException pe) {
			System.out.println(pe.getMessage());
		}
	}
	
	public Date getStartTime() {
		return startTime.getTime();
	}
	
	/*
	public void setEndTime(int hour, int minute) {
		endTime.set(Calendar.HOUR_OF_DAY, hour);
		endTime.set(Calendar.MINUTE, minute);
	}
	*/
	
	public void setEndTime(String time) {
		Date date;
		try {
			date = new SimpleDateFormat("hh:mm", Locale.ENGLISH).parse(time);
			endTime.setTime(date);
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
	
	public void setMeetingRoom(MeetingRoom meetingRoom) {
		this.meetingRoom = meetingRoom;
	}
	
	public MeetingRoom getMeetingRoom() {
		return meetingRoom;
	}
	
	public String toString() {
		return "Start time:\t" + startTime.toString() + "\nEnd time:\t" + endTime.toString() + 
				"\nMeeting room:\t" + meetingRoom.toString();
	}
}
