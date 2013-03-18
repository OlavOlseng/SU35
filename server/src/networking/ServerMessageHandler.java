package networking;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import networking.SBPFactory.MessageType;

import models.Alarm;
import models.Appointment;
import models.Employee;
import models.Group;
import models.Invitation;
import models.Room;

import dbinterface.DBConnection;
import dbinterface.DBFactory;

public class ServerMessageHandler extends MessageHandler{

	ConnectionBridge bridge;
	DBConnection conn;
	DBFactory dbFactory;
	SBPFactory msgFactory;

	public ServerMessageHandler(ConnectionBridge bridge) {
		this.bridge = bridge;
		this.conn = bridge.getDBConnection();
		this.dbFactory = new DBFactory();
		this.msgFactory = new SBPFactory();
	}

	@Override
	public void getEntry(String[] data) {
		String what = data[3];
		ArrayList<String> response = new ArrayList<String>();
		switch(what) {
		case(SBPFactory.OPTION_EMPLOYEE): 
			response = getEmployees(data);
		break;
		case(SBPFactory.OPTION_APPOINTMENT):
			response = getAppointments(data);
		break;
		case(SBPFactory.OPTION_INVITATION):
			response = getInvitation(data);
		break;
		case(SBPFactory.OPTION_INVITATIONS_BY_EMPLOYEE):
			response = getInvitationsByEmployee(data);
		break;
		case(SBPFactory.OPTION_INVITATIONS_BY_APPOINTMENT):
			response = getInvitationsByAppointment(data);
		break;
		case(SBPFactory.OPTION_ALARM):
			response = getAlarm(data);
		break;
		case(SBPFactory.OPTION_ROOM):
			response = getRooms(data);
		break;
		case(SBPFactory.OPTION_GROUP):
			response = getGroup(data);
		break;
		default:
			response.add(msgFactory.createMessage(MessageType.ERROR, true, "No matching command found", data[3], null));
		}

		try {
			for (String s : response){
				bridge.send(s);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}


	public ArrayList<String> getEmployees(String[] data) {
		boolean error = true;
		String errorMsg = "Unknown error...";
		String id = data[4];
		String payload = null;
		String response;
		ArrayList<Employee> employees = new ArrayList<Employee>();
		ArrayList<String> messages = new ArrayList<String>();

		try {
			String query = String.format("SELECT * FROM employee WHERE email='%s'", id);
			ResultSet set = conn.makeSingleQuery(query);
			employees = dbFactory.getEmployees(set);

			errorMsg = null;
			error = false;

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();

		} finally {
			if(employees.size() < 1) {
				response = msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_EMPLOYEE, payload);
				messages.add(response);
			}
			for(Employee e : employees){
				//TODO make serializer work properly
				payload = (e.toString());
				messages.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_EMPLOYEE, payload));

			}
		}
		return messages;
	}

	public ArrayList<String> getAppointments(String[] data) {
		boolean error = true;
		String errorMsg = "Unknown error...";
		String id = data[4];
		String payload = null;
		ArrayList<String> response = new ArrayList<String>();
		ArrayList<Appointment> apps = new ArrayList<Appointment>();

		String query = String.format("SELECT * FROM appointment WHERE ID='%s'", id);
		ResultSet set;

		try {
			set = conn.makeSingleQuery(query);
			apps = dbFactory.getAppointments(set);

			errorMsg = null;
			error = false;

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();
		} finally {
			if(apps.size() < 1) {
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_APPOINTMENT, null));
			}
			for(Appointment a : apps){
				//TODO make serializer work properly
				payload = (a.toString());
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_APPOINTMENT, payload));
			}
		}
		return response;
	}

	public ArrayList<String> getInvitation(String[] data) {
		boolean error = true;
		String errorMsg = "Unknown error...";
		String ids[] = data[4].split("¤");
		String payload = null;
		ArrayList<String> response = new ArrayList<String>();
		ArrayList<Invitation> invites = new ArrayList<Invitation>();

		String query = String.format("SELECT * FROM invitation WHERE employee_email='%s' AND appointment_ID='%s'", ids[0], ids[1]);
		ResultSet set;

		try {
			set = conn.makeSingleQuery(query);
			invites = dbFactory.getInvitations(set);

			errorMsg = null;
			error = false;

		} catch (SQLException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();
		} finally {
			if ((invites.size() < 1)) {
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_INVITATION, null));
			}
			for(Invitation i : invites){
				//TODO make serializer work properly
				payload = (i.toString());
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_INVITATION, payload));
			}
		}
		return response;
	}


	public ArrayList<String> getInvitationsByEmployee(String[] data) {
		boolean error = true;
		String errorMsg = "Unknown error...";
		String id = data[4];
		String payload = null;
		ArrayList<String> response = new ArrayList<String>();
		ArrayList<Invitation> invites = new ArrayList<Invitation>();

		String query = String.format("SELECT * FROM invitation WHERE employee_email='%s'", id);
		ResultSet set;

		try {
			set = conn.makeSingleQuery(query);
			invites = dbFactory.getInvitations(set);

			errorMsg = null;
			error = false;

		} catch (SQLException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();
		} finally {
			if ((invites.size() < 1)) {
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_INVITATION, null));
			}
			for(Invitation i : invites){
				//TODO make serializer work properly
				payload = (i.toString());
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_INVITATION, payload));
			}
		}
		return response;
	}

	private ArrayList<String> getInvitationsByAppointment(String[] data) {
		boolean error = true;
		String errorMsg = "Unknown error...";
		String id = data[4];
		String payload = null;
		ArrayList<String> response = new ArrayList<String>();
		ArrayList<Invitation> invites = new ArrayList<Invitation>();

		String query = String.format("SELECT * FROM invitation WHERE appointment_ID='%s'", id);
		ResultSet set;

		try {
			set = conn.makeSingleQuery(query);
			invites = dbFactory.getInvitations(set);

			errorMsg = null;
			error = false;

		} catch (SQLException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();
		} finally {
			if ((invites.size() < 1)) {
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_INVITATION, null));
			}
			for(Invitation i : invites){
				//TODO make serializer work properly
				payload = (i.toString());
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_INVITATION, payload));
			}
		}
		return response;
	}

	private ArrayList<String> getAlarm(String[] data) {
		boolean error = true;
		String errorMsg = "Unknown error...";
		String ids[] = data[4].split("¤");
		String payload = null;
		ArrayList<String> response = new ArrayList<String>();
		ArrayList<Alarm> alarms = new ArrayList<Alarm>();

		String query = String.format("SELECT * FROM alarm WHERE employee_email ='%s' AND appointment_ID='%s'", ids[0], ids[1]);
		ResultSet set;

		try {
			set = conn.makeSingleQuery(query);
			alarms = dbFactory.getAlarms(set);

			errorMsg = null;
			error = false;

		} catch (SQLException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();
		} finally {
			if ((alarms.size() < 1)) {
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_ALARM, null));
			}
			for(Alarm i : alarms){
				//TODO make serializer work properly
				payload = (i.toString());
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_ALARM, payload));
			}
		}
		return response;
	}

	private ArrayList<String> getGroup(String[] data) {
		boolean error = true;
		String errorMsg = "Unknown error...";
		String id = data[4];
		String payload = null;
		ArrayList<String> response = new ArrayList<String>(); 
		ArrayList<Group> groups = new ArrayList<Group>();

		String query = String.format("SELECT group_email, employee_email FROM member WHERE group_email='%s'", id);
		ResultSet set;

		try {
			set = conn.makeSingleQuery(query);
			groups = dbFactory.getGroups(set);

			errorMsg = null;
			error = false;

		} catch (SQLException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();
		} finally {
			if ((groups.size() < 1)) {
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_GROUP, null));
			}for(Group i : groups){
				//TODO make serializer work properly
				payload = (i.toString());
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_GROUP, payload));
			}
		}
		return response;
	}


	public ArrayList<String> getRooms(String[] data) {
		boolean error = true;
		String errorMsg = "Unknown error...";
		String payload = null;
		ArrayList<String>  response = new ArrayList<String>();
		ArrayList<Room> rooms = new ArrayList<Room>();

		String query = String.format("SELECT * FROM room");
		ResultSet set;

		try {
			set = conn.makeSingleQuery(query);
			rooms = dbFactory.getMeetingRooms(set);

			errorMsg = null;
			error = false;

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();
		} finally {
			if ((rooms.size() < 1)) {
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_ROOM, null));
			}
			for(Room i : rooms){
				//TODO make serializer work properly
				payload = (i.toString());
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_ROOM, payload));
			}
		}
		return response;
	}

	@Override
	public void updateEntry(String[] data) {

	}

	@Override
	public void makeBatchUpdate(String[] data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createEntry(String[] data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteEntry(String[] data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkLogin(String[] data) {
		String[] msg = data[4].split("¤");
		String user = msg[0];
		String pwd = msg[1];
		String query = String.format("SELECT * FROM employee WHERE email='%s' AND password='%s'", user, pwd);

		boolean error = true;
		String errorMsg = "Invalid login information";
		String response = null;

		try {
			ResultSet rs = conn.makeSingleQuery(query);
			if (rs.first()) {
				error = false;
				errorMsg = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			response = msgFactory.createMessage(MessageType.LOGIN, error, errorMsg, data[3], null);
		}

		try {
			bridge.send(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
