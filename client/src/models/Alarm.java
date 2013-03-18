package models;

public class Alarm {
	private final int appointmentID;
	private final String employeeEmail;
	private String date;
	private String description;
	
	public Alarm(int appointmentID, String employeeEmail) {
		this.appointmentID = appointmentID;
		this.employeeEmail = employeeEmail;
		date = "";
		description = "";
	}

	public int getAppointmentID() {
		return appointmentID;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
