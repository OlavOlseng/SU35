package dbinterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.*;
import models.Invitation.Answer;

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
		String roomName ="";
		int roomSize=0;
		while(rs.next()) {
			roomSize = rs.getInt(2);
			roomName = rs.getString(1);
		}
		Room meetingRoom = new Room(roomName, roomSize);
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
			String date = startTime.split(" ")[0];
			String description = rs.getString(4);
			String location = rs.getString(5);
			String meetingRoom = rs.getString(6);
			String meetingLeaderEmail = rs.getString(7);
			
			Appointment appointment = new Appointment(appointmentID);
			appointment.setDate(date);
			appointment.setStartTime(startTime);
			appointment.setEndTime(endTime);
			appointment.setDescription(description);
			appointment.setLocation(location);
			appointment.setMeetingRoom(meetingRoom);
			appointment.setMeetingLeader(meetingLeaderEmail);
			
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

	public ArrayList<Invitation> getInvitations(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Invitation> invites = new ArrayList<Invitation>();
		
		if(!rs.first()) throw new SQLException("No matching entry found"); 
		
		rs.beforeFirst();
		while(rs.next())
		{
			String employee_email = rs.getString(1);
			int appointmentID = rs.getInt(2);
			int accepted = rs.getInt(3);
			String message = rs.getString(4);
			
			Invitation i = new Invitation(employee_email, appointmentID);
			
			Answer a;
			switch(accepted) {
			case(1):
				a = Answer.ACCEPTED;
			break;
			case(0):
				a = Answer.DECLINED;
			break;
			default:
				a = Answer.PENDING;
			}
			
			i.setAnswer(a);
			i.setMessage(message);
			invites.add(i);
		}
		return invites;
	}
}
