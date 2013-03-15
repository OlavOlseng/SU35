package dbinterface;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.xml.internal.ws.api.addressing.WSEndpointReference.Metadata;

import models.*;

public class DBFactory {
	public ArrayList<Employee> getEmployees(ResultSet rs) throws ClassNotFoundException, SQLException {
		//String query = "SELECT * FROM Employee WHERE email=" + employeeEmail;
		if(!rs.first()) throw new SQLException("No matching entry found"); 
		
		ArrayList<Employee> employees = new ArrayList<Employee>();
		rs.beforeFirst();
			
		while(rs.next()) {
			String email = rs.getString(1);
			String firstName = rs.getString(2);
			String lastName = rs.getString(3);
			String homePhone = rs.getString(4);
			String mobilePhone = rs.getString(5);
			employees.add(new Employee(email, firstName, lastName, homePhone, mobilePhone));
		}

		return employees;
	}
	
	public Room getMeetingRoom(ResultSet rs) throws ClassNotFoundException, SQLException {
		//String query = "SELECT * FROM Meetingroom WHERE meetingroomID=" + roomID;
		int roomID=0;
		int roomSize=0;
		String roomName = "";
		while(rs.next()) {
			roomID = rs.getInt(1);
			roomSize = rs.getInt(2);
			roomName = rs.getString(3);
		}
		Room meetingRoom = new Room(roomID, roomSize);
		meetingRoom.setName(roomName);
		return meetingRoom;
	}
	
	public ArrayList<Appointment> getAppointments(ResultSet rs) throws ClassNotFoundException, SQLException {
		//String query = "SELECT * FROM Appointment WHERE appointmentID=(SELECT appointmentID FROM Employee WHERE email=" + employeeEmail + ")";
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		
		if(!rs.first()) throw new SQLException("No matching entry found"); 
		
		rs.beforeFirst();
		while(rs.next())
		{
			int appointmentID = rs.getInt(1);
			String startTime = rs.getString(2);
			String endTime = rs.getString(3);
			// a date field needs to be added to the SQLDB
			String date = rs.getString(4);
			String description = rs.getString(5);
			String location = rs.getString(6);
			//int meetingRoomID = rs.getInt(7);
			//String meetingLeaderEmail = rs.getString(8);
			Appointment appointment = new Appointment(appointmentID);
			appointment.setStartTime(startTime);
			appointment.setEndTime(endTime);
			appointment.setDate(date);
			appointment.setDescription(description);
			appointment.setLocation(location);
			/*
			// get the MeetingRoom object associated with the appointment
			MeetingRoom meetingRoom = getMeetingRoom(meetingRoomID);
			appointment.setMeetingRoom(meetingRoom);
			// get the Employee object assigned as appointment leader
			Employee meetingLeader = getEmployee(meetingLeaderEmail);
			appointment.setMeetingLeader(meetingLeader);
			*/
			appointments.add(appointment);
		}
		return appointments;
	}
}
