package dbinterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.*;
import models.Invitation.Answer;

public class DBFactory {
	public ArrayList<Employee> getEmployees(ResultSet rs) throws ClassNotFoundException, SQLException {
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
	
	public ArrayList<Room> getMeetingRooms(ResultSet rs) throws ClassNotFoundException, SQLException {
		ArrayList<Room> rooms = new ArrayList<Room>();
		if(!rs.first()) throw new SQLException("No matching entry found");
		rs.beforeFirst();
		while(rs.next()) {
			int roomSize = rs.getInt(2);
			String roomName = rs.getString(1);
			Room meetingRoom = new Room(roomName, roomSize);
			meetingRoom.setName(roomName);
			rooms.add(meetingRoom);
		}
		return rooms;
	}
	
	public ArrayList<Appointment> getAppointments(ResultSet rs) throws ClassNotFoundException, SQLException {
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
			appointments.add(appointment);
		}
		return appointments;
	}

	public ArrayList<Invitation> getInvitations(ResultSet rs) throws SQLException {
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

	public ArrayList<Alarm> getAlarms(ResultSet rs) throws SQLException {
		ArrayList<Alarm> alarms = new ArrayList<Alarm>();
		if(!rs.first()) throw new SQLException("No matching entry found");
		rs.beforeFirst();
		while(rs.next()) {
			String employeeEmail = rs.getString(1);
			int appointmentID = rs.getInt(2);
			String date = rs.getString(3);
			String description = rs.getString(4);
			Alarm alarm = new Alarm(appointmentID, employeeEmail);
			alarm.setDate(date);
			alarm.setDescription(description);
			alarms.add(alarm);
		}
		return alarms;
	}
	
	public ArrayList<Group> getGroups(ResultSet rs) throws SQLException {
		ArrayList<Group> groups = new ArrayList<Group>();
		if(!rs.first()) throw new SQLException("No matching entry found");
		rs.beforeFirst();
		String groupEmail = "";
		ArrayList<String> members = new ArrayList<String>();
		groupEmail = rs.getString(1);
		while(rs.next()) {
			members.add(rs.getString(2));
		}
		Group group = new Group(groupEmail);
		for(String member : members) {
			group.addMember(member);
		}
		groups.add(group);
		return groups;
	}
}
