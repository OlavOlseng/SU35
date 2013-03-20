package networking;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.XMLAssembler;
import util.XMLFactory;

import networking.SBPFactory.MessageType;
import nu.xom.Document;
import nu.xom.ParsingException;

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
	XMLFactory xmlFactory;
	XMLAssembler xmlAssembler;

	public ServerMessageHandler(ConnectionBridge bridge) {
		this.bridge = bridge;
		this.conn = bridge.getDBConnection();
		this.dbFactory = new DBFactory();
		this.msgFactory = new SBPFactory();
		this.xmlFactory = new XMLFactory();
		this.xmlAssembler = new XMLAssembler();
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

			if(data[4].equals("all")) {
				query = "SELECT * FROM employee";
			} 

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
				response = msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_EMPLOYEE, data[4]);
				messages.add(response);
			}
			for(Employee e : employees){
				payload = xmlFactory.makeEmployeeXML(e);
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
		if (id.equals("all")) {
			query = "SELECT * FROM appointment";
		}
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
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_APPOINTMENT, data[4]));
			}
			for(Appointment a : apps){
				payload = xmlFactory.makeAppointmentXML(a);
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_APPOINTMENT, payload));
			}
		}
		return response;
	}

	public ArrayList<String> getInvitation(String[] data) {
		boolean error = true;
		String errorMsg = "Unknown error...";
		ArrayList<String> response = new ArrayList<String>();
		ArrayList<Invitation> invites = new ArrayList<Invitation>();
		String query = "SELECT * FROM invitation";
		
		if(!data[4].equals("all")) {
			String ids[] = data[4].split("¤");
			query = String.format("SELECT * FROM invitation WHERE employee_email='%s' AND appointment_ID='%s'", ids[0], ids[1]);
		}
		
		String payload = null;

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
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_INVITATION, data[4]));
			}
			for(Invitation i : invites){
				payload = xmlFactory.makeInvitationXML(i);
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
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_INVITATION, data[4]));
			}
			for(Invitation i : invites){
				payload = xmlFactory.makeInvitationXML(i);
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
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_INVITATION, data[4]));
			}
			for(Invitation i : invites){
				payload = xmlFactory.makeInvitationXML(i);
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
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_ALARM, data[4]));
			}
			for(Alarm i : alarms){
				payload = xmlFactory.makeAlarmXML(i);
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
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_GROUP, data[4]));
			}for(Group i : groups){
				//TODO Implement serialization, fo reals
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
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_ROOM, data[4]));
			}
			for(Room i : rooms){
				payload = xmlFactory.makeRoomXML(i);
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_ROOM, payload));
			}
		}
		return response;
	}

	//GET CODE END

	//UPDATE CODE START

	@Override
	public void updateEntry(String[] data) {
		if (data.length < 4) {
			data[3] = "error";
		}
		boolean broadcast = true;

		ArrayList<String> response = new ArrayList<String>();
		String what = data[3];

		switch(what) {
		case(SBPFactory.OPTION_APPOINTMENT):
			response = updateAppointment(data);
		break;
		case(SBPFactory.OPTION_INVITATION):
			response = updateInvitation(data);
		break;
		case(SBPFactory.OPTION_ALARM):
			response = updateAlarm(data);
		broadcast = false;
		break;
		default:
			response.add(msgFactory.createMessage(MessageType.ERROR, true, "No matching command found", data[3], null));
		}
		try {
			for (String s : response){
				if(broadcast) {
					ConnectionPool.getInstance().broadcast(s);
				} else {
					bridge.send(s);
				}
			}
		} catch (IOException | SQLException e1) {
			e1.printStackTrace();

		}

	}

	public ArrayList<String> updateInvitation(String[] data) {
		ArrayList<String> response = new ArrayList<String>();
		boolean error = true;
		String errorMsg = "Unknown error...";

		try {
			Document d = xmlAssembler.getDocument(data[4]);
			Invitation inv = xmlAssembler.assembleInvitation(d.getRootElement());

			int accepted = inv.getAnswer().getValue();
			int appID = inv.getAppointmentID();
			String emp_email = inv.getEmployeeEmail();
			String message = inv.getMessage();

			String sql = String.format("UPDATE invitation SET accepted='%d', message='%s' WHERE employee_email='%s' AND appointment_ID='%d'", accepted, message, emp_email, appID);
			int i = conn.makeSingleUpdate(sql);
			if (i != -1) {

				error = false;
				errorMsg = null;
			}

		} catch (SQLException | ParsingException | IOException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();
		} finally {
			response.add(msgFactory.createMessage(MessageType.UPDATE, error, errorMsg, data[3], data[4]));
		}
		return response;	
	}

	public ArrayList<String> updateAppointment(String[] data) {
		ArrayList<String> response = new ArrayList<String>();
		boolean error = true;
		String errorMsg = "Unknown error...";

		try {
			Document d = xmlAssembler.getDocument(data[4]);
			Appointment app = xmlAssembler.assembleAppointment(d.getRootElement());

			int appID = app.getAppointmentID();
			String date = app.getFormattedDate();
			String startTime = app.getFormattedStartTime();
			String endTime = app.getFormattedEndTime();
			String title = app.getTitle();
			String desc = app.getDescription();
			String location = app.getLocation();
			String meetingRoom = app.getMeetingRoom();
			String meetingLeader = app.getMeetingLeader();

			String sql = String.format
					("UPDATE appointment SET title='%s', date='%s', starttime='%s', endtime='%s', description='%s', place='%s', meetingleader='%s', room_name ='%s' WHERE ID=%d", 
							title, date, startTime, endTime, desc, location, meetingLeader, meetingRoom, appID);
			int i = conn.makeSingleUpdate(sql);
			if (i < 1) {

				error = false;
				errorMsg = null;
			}

		} catch (SQLException | ParsingException | IOException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();
		} finally {
			response.add(msgFactory.createMessage(MessageType.UPDATE, error, errorMsg, data[3], data[4]));
		}
		return response;
	}

	public ArrayList<String> updateAlarm(String[] data) {
		ArrayList<String> response = new ArrayList<String>();
		boolean error = true;
		String errorMsg = "Unknown error...";

		try {
			Document d = xmlAssembler.getDocument(data[4]);
			Alarm al = xmlAssembler.assembleAlarm(d.getRootElement());

			String time = al.getTime();
			int appID = al.getAppointmentID();
			String empEmail = al.getEmployeeEmail();
			String desc = al.getDescription();

			String sql = String.format("UPDATE alarm SET time='%s', description='%s' WHERE employee_email='%s' AND appointment_ID='%d'", time, desc, empEmail, appID);
			int i = conn.makeSingleUpdate(sql);
			if (i < 1) {

				error = false;
				errorMsg = "No matching entry found...";
			}

		} catch (SQLException | ParsingException | IOException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();
		} finally {
			response.add(msgFactory.createMessage(MessageType.UPDATE, error, errorMsg, data[3], data[4]));
		}
		return response;
	}


	@Override
	public void makeBatchUpdate(String[] data) {

	}

	//UPDATE CODE END

	//CREATE CODE BEGIN

	@Override
	public void createEntry(String[] data) {
		if (data.length < 4) {
			data[3] = "error";
		}
		boolean broadcast = true;

		ArrayList<String> response = new ArrayList<String>();
		String what = data[3];

		switch(what) {
		case(SBPFactory.OPTION_APPOINTMENT):
			response = createAppointment(data);
		break;
		case(SBPFactory.OPTION_INVITATION):
			response = createInvitation(data);
		break;
		case(SBPFactory.OPTION_ALARM):
			response = createAlarm(data);
		broadcast = false;
		break;
		default:
			response.add(msgFactory.createMessage(MessageType.ERROR, true, "No matching command found", data[3], null));
		}
		try {
			for (String s : response){
				if(broadcast) {
					ConnectionPool.getInstance().broadcast(s);
				} else {
					bridge.send(s);
				}
			}
		} catch (IOException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	public ArrayList<String> createAppointment(String[] data) {
		ArrayList<String> response = new ArrayList<String>();
		boolean error = true;
		String errorMsg = "Unknown error...";

		try {

			Document d = xmlAssembler.getDocument(data[4]);
			Appointment app = xmlAssembler.assembleAppointment(d.getRootElement());

			String date = app.getFormattedDate();
			String startTime = app.getFormattedStartTime();
			String endTime = app.getFormattedEndTime();
			String title = app.getTitle();
			String desc = app.getDescription();
			String location = app.getLocation();
			String meetingRoom = app.getMeetingRoom();
			String meetingLeader = app.getMeetingLeader();

			int id = app.getAppointmentID();

			String sql = String.format("INSERT INTO appointment VALUES (%d,'%s','%s','%s','%s','%s','%s','%s','%s')", 
					id, title, date, startTime, endTime, desc, location, meetingLeader, meetingRoom);
			conn.makeSingleUpdate(sql);
			app.setAppointmentID(id);
			data[4] = xmlFactory.makeAppointmentXML(app);

			error = false;
			errorMsg = null;

		} catch (SQLException | ParsingException | IOException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();
		} finally {
			response.add(msgFactory.createMessage(MessageType.CREATE, error, errorMsg, data[3], data[4]));
		}
		return response;
	}



	public ArrayList<String> createInvitation(String[] data) {
		ArrayList<String> response = new ArrayList<String>();
		boolean error = true;
		String errorMsg = "Unknown error...";

		try {

			Document d = xmlAssembler.getDocument(data[4]);
			Invitation inv = xmlAssembler.assembleInvitation(d.getRootElement());

			int accepted = -1;
			int appID = inv.getAppointmentID();
			String emp_email = inv.getEmployeeEmail();
			String message = inv.getMessage();

			String sql = String.format("INSERT INTO invitation VALUES ('%s',%d, %d,'%s')", emp_email, appID, accepted, message);
			conn.makeSingleUpdate(sql);
			error = false;
			errorMsg = null;

		} catch (SQLException | ParsingException | IOException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();
		} finally {
			response.add(msgFactory.createMessage(MessageType.CREATE, error, errorMsg, data[3], data[4]));
		}

		return response;
	}

	public ArrayList<String> createAlarm(String[] data) {
		ArrayList<String> response = new ArrayList<String>();
		boolean error = true;
		String errorMsg = "Unknown error...";

		try {

			Document d = xmlAssembler.getDocument(data[4]);
			Alarm al = xmlAssembler.assembleAlarm(d.getRootElement());

			String time = al.getTime();
			int appID = al.getAppointmentID();
			String empEmail = al.getEmployeeEmail();
			String desc = al.getDescription();

			String sql = String.format("INSERT INTO alarm VALUES ('%s',%d,'%s','%s')", empEmail, appID, time, desc);
			conn.makeSingleUpdate(sql);
			error = false;
			errorMsg = null;

		} catch (SQLException | ParsingException | IOException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();
		} finally {
			response.add(msgFactory.createMessage(MessageType.CREATE, error, errorMsg, data[3], data[4]));
		}

		return response;
	}

	@Override
	public void deleteEntry(String[] data) {
		if (data.length < 4) {
			data[3] = "error";
		}
		boolean broadcast = true;

		ArrayList<String> response = new ArrayList<String>();
		String what = data[3];

		switch(what) {
		case(SBPFactory.OPTION_APPOINTMENT):
			response = deleteAppointment(data);
		break;
		case(SBPFactory.OPTION_INVITATION):
			response = deleteInvitation(data);
		break;
		case(SBPFactory.OPTION_ALARM):
			response = deleteAlarm(data);
		broadcast = false;
		break;
		default:
			response.add(msgFactory.createMessage(MessageType.ERROR, true, "No matching command found", data[3], null));
		}
		try {
			for (String s : response){
				if(broadcast) {
					ConnectionPool.getInstance().broadcast(s);
				} else {
					bridge.send(s);
				}
			}
		} catch (IOException | SQLException e1) {
			e1.printStackTrace();
		}
	}


	public ArrayList<String> deleteAppointment(String[] data) {
		ArrayList<String> response = new ArrayList<String>();
		boolean error = true;
		String errorMsg = "Unknown error...";

		try {
			Document d = xmlAssembler.getDocument(data[4]);
			Appointment app = xmlAssembler.assembleAppointment(d.getRootElement());

			int appID = app.getAppointmentID();

			String sql = String.format("DELETE FROM appointment WHERE ID='%d'", appID);
			conn.makeSingleUpdate(sql);
			error = false;
			errorMsg = null;

		} catch (SQLException | ParsingException | IOException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();
		} finally {
			response.add(msgFactory.createMessage(MessageType.DELETE, error, errorMsg, data[3], data[4]));
		}
		return response;
	}

	public ArrayList<String> deleteInvitation(String[] data) {
		ArrayList<String> response = new ArrayList<String>();
		boolean error = true;
		String errorMsg = "Unknown error...";

		try {
			Document d = xmlAssembler.getDocument(data[4]);
			Invitation inv = xmlAssembler.assembleInvitation(d.getRootElement());

			int appID = inv.getAppointmentID();
			String empEmail = inv.getEmployeeEmail();

			String sql = String.format("DELETE FROM invitation WHERE employee_email='%s' AND appointment_ID='%d'", empEmail, appID);
			conn.makeSingleUpdate(sql);
			error = false;
			errorMsg = null;

		} catch (SQLException | ParsingException | IOException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();
		} finally {
			response.add(msgFactory.createMessage(MessageType.DELETE, error, errorMsg, data[3], data[4]));
		}
		return response;
	}

	public ArrayList<String> deleteAlarm(String[] data) {
		ArrayList<String> response = new ArrayList<String>();
		boolean error = true;
		String errorMsg = "Unknown error...";

		try {
			Document d = xmlAssembler.getDocument(data[4]);
			Alarm al = xmlAssembler.assembleAlarm(d.getRootElement());

			int appID = al.getAppointmentID();
			String empEmail = al.getEmployeeEmail();

			String sql = String.format("DELETE FROM alarm WHERE employee_email='%s' AND appointment_ID='%d'", empEmail, appID);
			conn.makeSingleUpdate(sql);
			error = false;
			errorMsg = null;

		} catch (SQLException | ParsingException | IOException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();
		} finally {
			response.add(msgFactory.createMessage(MessageType.DELETE, error, errorMsg, data[3], data[4]));
		}
		return response;
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
			response = msgFactory.createMessage(MessageType.LOGIN, error, errorMsg, data[3], msg[0]);
		}
		try {
			bridge.send(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void errorResponse(String[] data) {
		String response = msgFactory.createMessage(MessageType.ERROR, true, "You shall not pass!!!!", null, null);
		try {
			bridge.send(response);
			bridge.close();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
}
