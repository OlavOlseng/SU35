package ghostmodels;

import java.util.ArrayList;

public class Calendar {
	private ArrayList<Appointment> appointments;
	private final String employeeEmail;
	
	public Calendar(String employeeEmail) {
		this.employeeEmail = employeeEmail;
		appointments = new ArrayList<Appointment>();
	}
	
	public ArrayList<Appointment> getAppointments() {
		String query = "SELECT Appointment FROM Employee WHERE email=" + employeeEmail;
		/*
		 * send query to server
		 */
		return appointments;
	}
}
