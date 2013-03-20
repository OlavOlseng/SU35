package models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Invitation {
	public static final String INVITATION_ANSWER_PROPERTY = "INVITATION_ANSWER_CHANGED";
	public static final String INVITATION_MESSAGE_PROPERTY = "INVITATION_MESSAGE_CHANGED";
	public static final String INVITATION_EMLOYEE_EMAIL_PROPERTY = "INVITATION_EMPLOYEE_EMAIL_CHANGED";
	public static final String INVITATION_APPOINTMENT_ID_PROPERTY = "INVITATION_APPOINTMENT_ID_CHANGED";

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
	
	private PropertyChangeSupport pcs;
	
	public Invitation(String employeeEmail, int appointmentID) {
		this.employeeEmail = employeeEmail;
		this.appointmentID = appointmentID;
		answer = Answer.PENDING;
		pcs = new PropertyChangeSupport(this);

	}
	
	public void setEmployeeEmail(String employeeEmail) {
		String temp = this.employeeEmail;
		this.employeeEmail = employeeEmail;
		pcs.firePropertyChange(INVITATION_EMLOYEE_EMAIL_PROPERTY, temp, this.employeeEmail);
	}



	public void setAppointmentID(int appointmentID) {
		int temp = this.appointmentID;
		this.appointmentID = appointmentID;
		pcs.firePropertyChange(INVITATION_APPOINTMENT_ID_PROPERTY, temp, this.appointmentID);
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public int getAppointmentID() {
		return appointmentID;
	}

	public void setMessage(String message) {
		String temp = this.message;
		this.message = message;
		pcs.firePropertyChange(INVITATION_MESSAGE_PROPERTY, temp, this.message);
	}

	public String getMessage() {
		return message;
	}

	public void setAnswer(Answer answer){
		Answer temp = answer;
		this.answer = answer;
		pcs.firePropertyChange(INVITATION_ANSWER_PROPERTY, temp, this.answer);
	}

	public Answer getAnswer() {
		return answer;
	}

	public String toString() {
		String s = String.format("Employee: %s \n Appointment: %d\n, Answer: %s \n Message: %s", employeeEmail, appointmentID, answer, message);
		return s;
	}
	public void addPropertyChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
}
