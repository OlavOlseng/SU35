package networking;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		boolean error = false;
		String errorMsg = null;
		String msg[] = data[3].split("^");
		String what = msg[0];
		String id = msg[1];
		String payload = null;

		try {
			switch(what) {
				case("employee"): 
					String query = String.format("SELECT * FROM employee WHERE id='%s'", id);
				ResultSet set = conn.makeSingleQuery(query);
				Employee e = dbFactory.getEmployee(set);
				payload = XMLSerializer.personToXml(e);
				break;

			}
		
		} catch (SQLException | ClassNotFoundException | ParserConfigurationException e) {
			e.printStackTrace();
			error = true;
			errorMsg = e.getMessage();
		}
		
		String response = msgFactory.createMessage(MessageType.GET, error, errorMsg, payload);
		try {
			bridge.send(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		// TODO Auto-generated method stub

	}

}
