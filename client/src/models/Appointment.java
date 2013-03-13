package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Appointment {
	protected final int appointmentID;
	protected Calendar startTime;
	protected Calendar endTime;
	protected String description;
	protected String location;
	
	public Appointment(int appointmentID) {
		this.appointmentID = appointmentID;
		startTime = Calendar.getInstance();
		endTime = Calendar.getInstance();
	}
	
	public int getAppointmentID() {
		return appointmentID;
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
	
	public String toString() {
		return "Start time:\t" + startTime.getTime().toString() + "\nEnd time:\t" + endTime.getTime().toString();
	}
}
