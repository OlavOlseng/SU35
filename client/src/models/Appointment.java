package models;

import java.beans.PropertyChangeSupport;
import java.util.Calendar;

public class Appointment {
	public static final String APPOINTMENT_LOCATION_PROPERTY = "APPOINTMENT_LOCATION_CHANGED";
	public static final String APPOINTMENT_DESCRIPTION_PROPERTY = "APPOINTMENT_DESCRIPTION_CHANGED";
	public static final String APPOINTMENT_MEETINGLEADER_PROPERTY = "APPOINTMENT_MEETINGLEADER_CHANGED";
	public static final String APPOINTMENT_MEETINGROOM_PROPERTY = "APPOINTMENT_MEETINGROOM_CHANGED";
	public static final String APPOINTMENT_APPOINTMENT_ID_PROPERTY = "APPOINTMENT_APPOINTMENT_ID_CHANGED";
	public static final String APPOINTMENT_TITLE_PROPERTY = "APPOINTMENT_TITLE_CHANGED";
	public static final String APPOINTMENT_DATE_PROPERTY = "APPOINTMENT_DATE_CHANGED";
	public static final String APPOINTMENT_STARTTIME_PROPERTY = "APPOINTMENT_STARTTIME_CHANGED";
	public static final String APPOINTMENT_ENDTIME_PROPERTY = "APPOINTMENT_ENDTIME_CHANGED";
	
	private int appointmentID;
	private Calendar date;
	private Calendar startTime;
	private Calendar endTime;
	private String title;
	private String description;
	private String location;
	private String meetingRoom;
	private String meetingLeader;
	
	private PropertyChangeSupport pcs;

	public Appointment(int appointmentID) {
		this.appointmentID = appointmentID;
		description = "";
		location = "";
		startTime = Calendar.getInstance();
		endTime = Calendar.getInstance();
		date = Calendar.getInstance();
		pcs = new PropertyChangeSupport(this);
	}

	public void setAppointmentID(int appointmentID){
		int temp = this.appointmentID;
		this.appointmentID = appointmentID;
		pcs.firePropertyChange(APPOINTMENT_APPOINTMENT_ID_PROPERTY, temp, this.appointmentID);
	}

	public int getAppointmentID() {
		return appointmentID;
	}

	public String getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(String meetingRoom) {
		String temp = this.meetingRoom;
		this.meetingRoom = meetingRoom;
		pcs.firePropertyChange(APPOINTMENT_MEETINGROOM_PROPERTY, temp, this.meetingRoom);
	}
	
	public String getMeetingLeader() {
		return meetingLeader;
	}

	public void setMeetingLeader(String meetingLeader) {
		String temp = this.meetingLeader;
		this.meetingLeader = meetingLeader;
		pcs.firePropertyChange(APPOINTMENT_MEETINGLEADER_PROPERTY, temp, this.meetingLeader);
	}
	
	private void setDate(int year, int month, int day) {
		Calendar temp = this.date;
		date.set(year, month-1, day, 0, 0, 0);
		pcs.firePropertyChange(APPOINTMENT_DATE_PROPERTY, temp, this.date);
		temp = this.startTime;
		startTime.set(year, month-1, day);
		pcs.firePropertyChange(APPOINTMENT_STARTTIME_PROPERTY, temp, this.startTime);
		temp = this.endTime;
		endTime.set(year, month-1, day);
		pcs.firePropertyChange(APPOINTMENT_ENDTIME_PROPERTY, temp, this.endTime);
	}

	public void setDate(String dateString) {
		String[] temp = dateString.split("-");
		setDate(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
	}

	private void setStartTime(int hour, int minute) {
		Calendar temp = this.startTime;
		startTime.setTime(date.getTime());
		startTime.set(Calendar.HOUR_OF_DAY, hour);
		startTime.set(Calendar.MINUTE, minute);
		pcs.firePropertyChange(APPOINTMENT_STARTTIME_PROPERTY, temp, this.startTime);
	}

	public void setStartTime(String time) {
		String[] temp = time.split(":");
		setStartTime(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
	}

	private void setEndTime(int hour, int minute) {
		Calendar temp = this.endTime;
		endTime.setTime(date.getTime());
		endTime.set(Calendar.HOUR_OF_DAY, hour);
		endTime.set(Calendar.MINUTE, minute);
		pcs.firePropertyChange(APPOINTMENT_ENDTIME_PROPERTY, temp, this.endTime);
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
		String temp = this.description;
		this.description = description;
		pcs.firePropertyChange(APPOINTMENT_DESCRIPTION_PROPERTY, temp, this.description);
	}

	public String getDescription() {
		return description;
	}

	public void setLocation(String location) {
		String temp = this.location;
		this.location = location;
		pcs.firePropertyChange(APPOINTMENT_LOCATION_PROPERTY, temp, this.location);
	}

	public String getLocation() {
		return location;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		String temp = this.title;
		this.title = title;
		pcs.firePropertyChange(APPOINTMENT_TITLE_PROPERTY, temp, this.title);
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
}
