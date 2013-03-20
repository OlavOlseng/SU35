package models;

public class Alarm {
	private int appointmentID;
	private String employeeEmail;
	private String time;
	private String description;
	
	public Alarm(int appointmentID, String employeeEmail) {
		this.appointmentID = appointmentID;
		this.employeeEmail = employeeEmail;
		time = "";
		description = "";
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
		this.time = time;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void update(Alarm other) {
		this.setDescription(other.getDescription());
		this.setTime(other.getTime());
	} 
	
	public String toString() {
		String ret = "";
		ret += "Employee email:\t\t\t" + employeeEmail;
		ret += "\nAppointment ID:\t\t\t" + appointmentID;
		ret += "\nAlarm Time:\t\t\t" + time;
		ret += "\nAlarm Description:\t\t" + description;
		return ret;
	}
}
