package models;

import java.awt.datatransfer.StringSelection;

public class Invitation {
	public enum Answer { 
		ACCEPTED(1), 
		DECLINED(0), 
		PENDING(-1);

		int b;

		Answer(int b) {
			this.b = b;
		}
		
		public int getValue() {
			return b;
		}
	}

	private Answer answer;
	String message;
	private String employeeEmail;
	private int appointmentID;
	
	public Invitation(String employeeEmail, int appointmentID) {
		this.employeeEmail = employeeEmail;
		this.appointmentID = appointmentID;
		answer = Answer.PENDING;

	}
	
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}



	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public int getAppointmentID() {
		return appointmentID;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setAnswer(Answer answer){
		this.answer = answer;
	}

	public Answer getAnswer() {
		return answer;
	}

	public String toString() {
		String s = String.format("Employee: %s \n Appointment: %d\n, Answer: %s \n Message: %s", employeeEmail, appointmentID, answer, message);
		return s;
	}

	public void update(Invitation i) {
		setAnswer(i.getAnswer());
		setMessage(i.getMessage());
	}
}
