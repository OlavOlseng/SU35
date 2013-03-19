package networking;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import util.XMLAssembler;
import util.XMLFactory;

import networking.SBPFactory.MessageType;
import nu.xom.Document;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

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
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_APPOINTMENT, data[4]));
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
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_INVITATION, data[4]));
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
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_INVITATION, data[4]));
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
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_INVITATION, data[4]));
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
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_ALARM, data[4]));
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
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_GROUP, data[4]));
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
				response.add(msgFactory.createMessage(MessageType.GET, error, errorMsg, SBPFactory.OPTION_ROOM, data[4]));
			}
			for(Room i : rooms){
				//TODO make serializer work properly
				payload = (i.toString());
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

	public ArrayList<String> updateAppointment(String[] data) {
		//TODO implement
		ArrayList<String> response = new ArrayList<String>();


		return response;
	}
	public ArrayList<String> updateInvitation(String[] data) {
		//TODO implement
		ArrayList<String> response = new ArrayList<String>();

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
			conn.makeSingleUpdate(sql);
			error = false;
			errorMsg = null;

		} catch (SQLException | ParsingException | IOException e) {
			// TODO Auto-generated catch block
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
		// TODO Auto-generated method stub

	}

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
			//			response = updateAppointment(data);
			break;
		case(SBPFactory.OPTION_INVITATION):
			//			response = updateInvitation(data);
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

			
			System.out.println("INSERTING INTO DATABASE!");
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
