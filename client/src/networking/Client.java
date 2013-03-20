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
	public final static int CONNECTPORT = 49153;
	public final static String HOST = "78.91.15.198";
	
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
			System.out.println("Connecting to " + getHostAddress());
			connection.connect(HOST, CONNECTPORT);
			receiveWorker.start();
		}
		catch(IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		System.out.println("Successfully connected to " + getHostAddress());
	}
	
	public void disconnect() {
		try {
			System.out.println("Closing connection to " + getHostAddress());
			if(!connection.isClosed()) {
				connection.close();
			}
		}
		catch(IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		System.out.println("Connection to " + getHostAddress() + " closed successfully");
	}
	
	public void messageReceived(String message) {
		messageHandler.handleMessage(message);
	}
	
	public String getHostAddress() {
		return HOST + ":" + CONNECTPORT;
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
	
	public void getAllAppointments() {
		String msg = factory.makeGetMessage(SBPFactory.OPTION_APPOINTMENT, "all");
		send(msg);
	}
	
	public void getAllEmployees() {
		String msg = factory.makeGetMessage(SBPFactory.OPTION_EMPLOYEE, "all");
		send(msg);
	}
	
	public void getAllRooms() {
		String msg = factory.makeGetMessage(SBPFactory.OPTION_ROOM, "all");
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
	public void sendEmployeeDeletion(String email) {
		Employee employee = new Employee(email,null,null,null,null);
		String payload = xmlFactory.makeEmployeeXML(employee);
		String msg = factory.makeDeleteMessage(SBPFactory.OPTION_EMPLOYEE, payload);
		send(msg);
	}
	
	public void sendAppointmentDeletion(String ID) {
		Appointment appointment = new Appointment(Integer.parseInt(ID));
		String payload = xmlFactory.makeAppointmentXML(appointment);
		String msg = factory.makeDeleteMessage(SBPFactory.OPTION_APPOINTMENT, payload);
		send(msg);
	}
	
	public void sendInvitationDeletion(String email, String ID) {
		Invitation invitation = new Invitation(email, Integer.parseInt(ID));
		String payload = xmlFactory.makeInvitationXML(invitation);
		String msg = factory.makeDeleteMessage(SBPFactory.OPTION_INVITATION, payload);
		send(msg);
	}
	
	public void sendRoomDeletion(String name) {
		Room room = new Room(name, 0);
		String payload = xmlFactory.makeRoomXML(room);
		String msg = factory.makeDeleteMessage(SBPFactory.OPTION_ROOM, payload);
		send(msg);
	}
	
	/*
	public void sendGroupDeletion(String email) {
		Group group = new Group(email);
		String payload = xmlFactory.makeGroupXML(group);
		String msg = factory.makeDeleteMessage(SBPFactory.OPTION_GROUP, payload);
		send(msg);
	}
	*/
	
	public void sendAlarmDeletion(String email, String ID) {
		Alarm alarm = new Alarm(Integer.parseInt(ID), email);
		String payload = xmlFactory.makeAlarmXML(alarm);
		String msg = factory.makeDeleteMessage(SBPFactory.OPTION_ALARM, payload);
		send(msg);
	}
	
	/*
	 * QUERY THE SERVER FOR CREATION
	 */
	public void sendEmployeeCreation(Employee employee) {
		String payload = xmlFactory.makeEmployeeXML(employee);
		String msg = factory.makeCreateMessage(SBPFactory.OPTION_EMPLOYEE, payload);
		send(msg);
	}
	
	public void sendAppointmentCreation(Appointment appointment) {
		String payload = xmlFactory.makeAppointmentXML(appointment);
		String msg = factory.makeCreateMessage(SBPFactory.OPTION_APPOINTMENT, payload);
		send(msg);
	}
	
	public void sendInvitationCreation(Invitation invitation) {
		String payload = xmlFactory.makeInvitationXML(invitation);
		String msg = factory.makeCreateMessage(SBPFactory.OPTION_INVITATION, payload);
		send(msg);
	}
	
	public void sendRoomCreation(Room room) {
		String payload = xmlFactory.makeRoomXML(room);
		String msg = factory.makeCreateMessage(SBPFactory.OPTION_ROOM, payload);
		send(msg);
	}
	
	/*
	public void sendGroupCreation(Group group) {
		String payload = xmlFactory.makeGroupXML(group);
		String msg = factory.makeCreateMessage(SBPFactory.OPTION_GROUP, payload);
		send(msg);
	}
	*/
	
	public void sendAlarmCreation(Alarm alarm) {
		String payload = xmlFactory.makeAlarmXML(alarm);
		String msg = factory.makeCreateMessage(SBPFactory.OPTION_ALARM, payload);
		send(msg);
	}
	
	/*
	 * QUERY THE SERVER FOR UPDATE
	 */
	public void sendEmployeeUpdate(Employee employee) {
		String payload = xmlFactory.makeEmployeeXML(employee);
		String msg = factory.makeUpdateMessage(SBPFactory.OPTION_EMPLOYEE, payload);
		send(msg);
	}
	
	public void sendAppointmentUpdate(Appointment appointment) {
		String payload = xmlFactory.makeAppointmentXML(appointment);
		String msg = factory.makeUpdateMessage(SBPFactory.OPTION_APPOINTMENT, payload);
		send(msg);
	}
	
	public void sendInvitationUpdate(Invitation invitation) {
		String payload = xmlFactory.makeInvitationXML(invitation);
		String msg = factory.makeUpdateMessage(SBPFactory.OPTION_INVITATION, payload);
		send(msg);
	}
	
	public void sendRoomUpdate(Room room) {
		String payload = xmlFactory.makeRoomXML(room);
		String msg = factory.makeUpdateMessage(SBPFactory.OPTION_ROOM, payload);
		send(msg);
	}
	
	public void sendAlarmUpdate(Alarm alarm) {
		String payload = xmlFactory.makeAlarmXML(alarm);
		String msg = factory.makeUpdateMessage(SBPFactory.OPTION_ALARM, payload);
		send(msg);
	}
}