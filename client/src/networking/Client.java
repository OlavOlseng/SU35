package networking;

import java.io.IOException;
import util.*;
import java.net.Socket;
import models.*;

public class Client implements MessageListener {
	private Connection connection;
	private ReceiveWorker receiveWorker;
	private SBPFactory factory;
	private ClientMessageHandler messageHandler;
	private XMLFactory xmlFactory;
	private final static int CONNECTPORT = 49153;
	private final static String HOST = "78.91.12.42";
	
	public Client() {
		try {
			connection = new Connection(new Socket());
			receiveWorker = new ReceiveWorker(connection, this);
		}
		catch(IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		factory = new SBPFactory();
		messageHandler = new ClientMessageHandler();
		xmlFactory = new XMLFactory();
	}
	
	public void connect() {
		try {
			connection.connect(HOST, CONNECTPORT);
			receiveWorker.start();
		}
		catch(IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}
	
	public void disconnect() {
		try {
			if(!connection.isClosed()) {
				connection.close();
			}
		}
		catch(IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}
	
	public void messageReceived(String message) {
		System.out.println(message);
		//messageHandler.handleMessage(message);
	}
	
	public String getHostAddress() {
		return connection.getRemoteAddress().getHostAddress();
	}
	
	public void send(String msg) {
		try {
			connection.send(msg);
		}
		catch(IOException ioe) {
			System.err.println(ioe.getMessage());
			ioe.printStackTrace();
		}
	}
	
	/*
	 * QUERY THE SERVER FOR LOGIN
	 */
	public void sendLoginQuery(String username, String password) {
		String msg = factory.makeLoginMessage(username, password);
		send(msg);
	}
	
	/*
	 * QUERY THE SERVER FOR DATA
	 */
	public void sendEmployeeQuery(String email) {
		String msg = factory.makeGetMessage(SBPFactory.OPTION_EMPLOYEE, email);
		send(msg);
	}
	
	public void sendAppointmentQuery(String ID) {
		String msg = factory.makeGetMessage(SBPFactory.OPTION_APPOINTMENT, ID);
		send(msg);
	}
	
	public void sendInvitationQuery(String email, String ID) {
		String msg;
		if(email != null && ID != null) {
			msg = factory.makeGetMessage(SBPFactory.OPTION_INVITATION, email+"¤"+ID);
		}
		else if(email != null && ID == null) {
			msg = factory.makeGetMessage(SBPFactory.OPTION_INVITATIONS_BY_EMPLOYEE, email);
		}
		else {
			msg = factory.makeGetMessage(SBPFactory.OPTION_INVITATIONS_BY_APPOINTMENT, ID);
		}
		send(msg);
	}
	
	public void sendRoomQuery(String name) {
		String msg = factory.makeGetMessage(SBPFactory.OPTION_ROOM, name);
		send(msg);
	}
	
	public void sendAlarmQuery(String email, String ID) {
		String msg = factory.makeGetMessage(SBPFactory.OPTION_ALARM, email+"¤"+ID);
		send(msg);
	}
	
	public void sendGroupQuery(String email) {
		String msg = factory.makeGetMessage(SBPFactory.OPTION_GROUP, email);
		send(msg);
	}
	
	/*
	 * QUERY THE SERVER FOR DELETION
	 */
	public void sendAppointmentDeletion(String ID) {
		String msg = factory.makeDeleteMessage(SBPFactory.OPTION_APPOINTMENT, ID);
		send(msg);
	}
	
	public void sendInvitationDeletion(String email, String ID) {
		String msg = factory.makeDeleteMessage(SBPFactory.OPTION_INVITATION, email+"¤"+ID);
		send(msg);
	}
	
	public void sendAlarmDeletion(String email, String ID) {
		String msg = factory.makeDeleteMessage(SBPFactory.OPTION_ALARM, email+"¤"+ID);
		send(msg);
	}
	
	/*
	 * QUERY THE SERVER FOR CREATION
	 */
	public void sendAlarmCreation(Alarm alarm) {
		String payload = xmlFactory.makeAlarmXML(alarm);
		System.out.println(payload);
		String msg = factory.createMessage(SBPFactory.MessageType.CREATE, false, "...", SBPFactory.OPTION_ALARM, payload);
		send(msg);
	}
}