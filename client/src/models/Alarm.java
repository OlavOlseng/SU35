package models;

public class Alarm {
	private int alarmID;
	private String employeeEmail;
	
	public Alarm(int alarmID, String employeeEmail) {
		this.alarmID = alarmID;
		this.employeeEmail = employeeEmail;
	}

	public int getAlarmID() {
		return alarmID;
	}

	public void setAlarmID(int alarmID) {
		this.alarmID = alarmID;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	
	
}
