package models;

public class Alarm {
	private final int appointmentID;
	private final String employeeEmail;
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
}
