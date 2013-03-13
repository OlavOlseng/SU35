package models;

public class Alarm {
	private final int alarmID;
	private final String employeeEmail;
	
	public Alarm(int alarmID, String employeeEmail) {
		this.alarmID = alarmID;
		this.employeeEmail = employeeEmail;
	}
}
