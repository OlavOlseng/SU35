package models;

public class Invitation {
	public enum Answer { 
		ACCEPTED(1), 
		DECLINED(0), 
		PENDING(-1);

		int b;

		Answer(int b) {
			this.b = b;
		}
	}

	private Answer answer;
	private final String employeeEmail;
	private final int appointmentID;
	String message;
	
	public Invitation(String employeeEmail, int appointmentID) {
		this.employeeEmail = employeeEmail;
		this.appointmentID = appointmentID;
		answer = Answer.PENDING;

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
		if(answer == Answer.ACCEPTED) {
			return "Invitation accepted";
		}
		else if(answer == Answer.DECLINED){
			return "Invitation declined";
		}
		else {
			return "Invitation pending";
		}
	}
}
