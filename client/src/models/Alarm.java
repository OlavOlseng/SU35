package models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Alarm {
	public static final String ALARM_TIME_PROPERTY = "ALARM_TIME_CHANGED";
	public static final String ALARM_DESCRIPTION_PROPERTY = "ALARM_DESCRIPTION_CHANGED";
	public static final String ALARM_EMLOYEE_EMAIL_PROPERTY = "ALARM_EMPLOYEE_EMAIL_CHANGED";
	public static final String ALARM_APPOINTMENT_ID_PROPERTY = "ALARM_APPOINTMENT_ID_CHANGED";
	
	private int appointmentID;
	private String employeeEmail;
	private String time;
	private String description;
	
	private PropertyChangeSupport pcs;
	
	public Alarm(int appointmentID, String employeeEmail) {
		this.appointmentID = appointmentID;
		this.employeeEmail = employeeEmail;
		time = "";
		description = "";
		pcs = new PropertyChangeSupport(this);
	}

	public void setAppointmentID(int appointmentID) {
		int temp = this.appointmentID;
		this.appointmentID = appointmentID;
		pcs.firePropertyChange(ALARM_APPOINTMENT_ID_PROPERTY, temp, this.getAppointmentID());
	}



	public void setEmployeeEmail(String employeeEmail) {
		String temp = this.employeeEmail;
		this.employeeEmail = employeeEmail;
		pcs.firePropertyChange(ALARM_EMLOYEE_EMAIL_PROPERTY, temp, this.employeeEmail);
	}



	public int getAppointmentID() {
		return appointmentID;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		String temp = this.time;
		this.time = time;
		pcs.firePropertyChange(ALARM_TIME_PROPERTY, temp, this.time);
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		String temp = this.description;
		this.description = description;
		pcs.firePropertyChange(ALARM_DESCRIPTION_PROPERTY, temp, this.description);
	}
	
	public String toString() {
		String ret = "";
		ret += "Employee email:\t\t\t" + employeeEmail;
		ret += "\nAppointment ID:\t\t\t" + appointmentID;
		ret += "\nAlarm Time:\t\t\t" + time;
		ret += "\nAlarm Description:\t\t" + description;
		return ret;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
}
