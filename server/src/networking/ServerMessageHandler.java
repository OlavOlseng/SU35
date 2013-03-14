package networking;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import networking.SBPFactory.MessageType;

import util.XMLSerializer;


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
		String msg[] = data[3].split("¤");
		String what = msg[0];
		String response = null;
		
		switch(what) {
			case("employee"): 
				response = getEmployee(data);
				break;
			default:
				response = msgFactory.createMessage(MessageType.GET, true, "No matching command found", null);
		}
		
		try {
			bridge.send(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getEmployee(String[] data) {
		boolean error = true;
		String errorMsg = "Unknown error...";
		String msg[] = data[3].split("¤");
		String id = msg[1];
		String payload = null;
		String response;
		
		try {
			String query = String.format("SELECT * FROM employee WHERE email='%s'", id);
			ResultSet set = conn.makeSingleQuery(query);
			ArrayList<Employee> employees = dbFactory.getEmployees(set);
			for(Employee e : employees){
				payload = XMLSerializer.personToXml(e);
			}
			errorMsg = null;
			error = false;

		} catch (SQLException | ClassNotFoundException | ParserConfigurationException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();
			
		} finally {
			response = msgFactory.createMessage(MessageType.GET, error, errorMsg, payload);
		}
		
		return response;
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
		String[] msg = data[3].split("¤");
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
			response = msgFactory.createMessage(MessageType.LOGIN, error, errorMsg, null);
		}
		
		try {
			bridge.send(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
