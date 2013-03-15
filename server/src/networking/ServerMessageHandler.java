package networking;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import networking.SBPFactory.MessageType;

import models.Appointment;
import models.Employee;

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
		msgFactory = new SBPFactory();
	}

	@Override
	public void getEntry(String[] data) {
		String what = data[3];
		String response = null;

		switch(what) {
		case("employee"): 
			getEmployees(data);
		break;
		case("appointment"):
			getAppointments(data);
		break;
		default:
			response = msgFactory.createMessage(MessageType.GET, true, "No matching command found", data[3], null);
			try {
				bridge.send(response);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	public void getEmployees(String[] data) {
		boolean error = true;
		String errorMsg = "Unknown error...";
		String id = data[4];
		String payload = null;
		String response;
		ArrayList<Employee> employees = new ArrayList<Employee>();

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

			for(Employee e : employees){
				//TODO make serializer work properly
				payload = data[3] + "¤" + (e.toString());
				response = msgFactory.createMessage(MessageType.GET, error, errorMsg, data[3], payload);
				try {
					this.bridge.send(response);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	public void getAppointments(String[] data) {
		boolean error = true;
		String errorMsg = "Unknown error...";
		String id = data[4];
		String payload = null;
		String response;
		ArrayList<Appointment> apps = new ArrayList<Appointment>();
		
		String query = String.format("SELECT * FROM appointment WHERE appointmentID='%s'", id);
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

			for(Appointment a : apps){
				//TODO make serializer work properly
				payload = data[3] + "¤" + (a.toString());
				response = msgFactory.createMessage(MessageType.GET, error, errorMsg, data[3], payload);
				try {
					this.bridge.send(response);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void updateEntry(String[] data) {
		// TODO Auto-generated method stub

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			response = msgFactory.createMessage(MessageType.LOGIN, error, errorMsg, data[3], null);
		}

		try {
			bridge.send(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
