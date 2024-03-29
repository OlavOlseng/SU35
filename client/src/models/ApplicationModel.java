package models;

import java.util.ArrayList;
import java.util.HashMap;

import networking.Client;
import models.Invitation.Answer;

public class ApplicationModel {
	private static ApplicationModel model = null;
	private HashMap<String, Employee> employees;
	private HashMap<Integer, Appointment> appointment;
	private HashMap<String, Room> rooms;
	private HashMap<String, Invitation> invitations;
	private HashMap<String, Alarm> alarms;
	public String username; 
	public Client connection;
	
	private ArrayList<ModelListener> listeners;
	
	private ApplicationModel(){
		listeners = new ArrayList<ModelListener>();
		employees = new HashMap<String, Employee>();
		appointment = new HashMap<Integer, Appointment>();
		rooms = new HashMap<String, Room>();
		invitations = new HashMap<String, Invitation>();
		alarms = new HashMap<String, Alarm>();
		username = null;
		connection = new Client();
		connection.connect();
	}
	
	public void login(String username, String password) {
		connection.sendLoginQuery(username, password);
	}
	
	public void fetchData() {
		connection.getAllAppointments();
		connection.getAllEmployees();
		connection.getAllRooms();
		connection.getAllInvitations();
	}
	
	// key = email
	public void addEmployee(String key, Employee value){
		if(employees.containsKey(key)) {
			updateEmployee(key, value);
			return;
		}
		employees.put(key, value);
		System.out.println("Key: " + key);
		fireUpdateEvent();
	}
	
	public Employee getEmployee(String email){
		Employee e = employees.get(email);
		if(e == null){
			System.err.println("No entry found in Hashmap");
			e = new Employee(email, "", "", "", "");
			employees.put(email, e);
			connection.sendEmployeeQuery(email);
		}
		return e;
	}
	
	public void updateEmployee(String email, Employee e){
		if(employees.containsKey(email)){
			Employee local = employees.get(email);
			local.update(e);
			fireUpdateEvent();
		}
		else {
			getEmployee(email);
		}
	}
	
	public void deleteEmployee(String email){
		if(employees.containsKey(email)){
			employees.remove(email);
			if(email.equals(username)) {
				connection.sendEmployeeDeletion(email);
			}
			fireUpdateEvent();
		}
	}
	
	// key = appointmentID
	public void addAppointment(int key, Appointment value){
		if(appointment.containsKey(key)) {
			updateAppointment(key, value);
			return;
		}
		appointment.put(key, value);
		System.out.println("Key: " + key);
		fireUpdateEvent();
	}
	
	public void createAppointment(Appointment value) {
		if(appointment.containsKey(value.getAppointmentID())) {
			connection.sendAppointmentUpdate(value);
		}
		else {
			connection.sendAppointmentCreation(value);
		}
	}
	
	public Appointment getAppointment(int aID){
		Appointment a = appointment.get(aID);
		if(a == null){
			System.err.println("No entry found in Hashmap");
			a = new Appointment(aID);
			appointment.put(aID, a);
			connection.sendAppointmentQuery(Integer.toString(aID));
		}
		return a;
	}
	
	public void updateAppointment(int aID, Appointment a){
		if(appointment.containsKey(aID)){
			Appointment local = appointment.get(aID);
			local.update(a);
			fireUpdateEvent();
		}
		else {
			getAppointment(aID);
		}
	}
	
	public void deleteAppointment(int aID){
		if(appointment.containsKey(aID)){
			Appointment app = getAppointment(aID); 
			appointment.remove(aID);
			if(app.getMeetingLeader() != null && app.getMeetingLeader().equals(username)) {
				connection.sendAppointmentDeletion(aID);
			}
			fireUpdateEvent();
		}
	}
	
	// key = email and appointmentID
	public void addInvitation(String email, int appointmentID, Invitation value){
		String key = email + "�" + appointmentID;
		if(invitations.containsKey(key)) {
			updateInvitation(email, appointmentID, value);
			return;
		}
		invitations.put(key, value);
		System.out.println("Key: " + key);
		fireUpdateEvent();
	}
	
	public void createInvitation(Invitation value) {
		if(invitations.containsKey(value.getEmployeeEmail()+"�"+value.getAppointmentID())) {
			connection.sendInvitationUpdate(value);
		}
		else {
			connection.sendInvitationCreation(value);
		}
	}
	
	public Invitation getInvitation(String email, int appointmentID){
		String id = email + "�" + appointmentID;
		Invitation i = invitations.get(id);
		
		if(i == null){
			System.err.println("No entry found in Hashmap");
			i = new Invitation(email,appointmentID);
			invitations.put(id, i);
			connection.sendInvitationQuery(email, Integer.toString(appointmentID));
		}
		return i;
	}
	
	public ArrayList<Appointment> getAppointmentsForUser(String email) {
		ArrayList<Appointment> array = new ArrayList<Appointment>();
		for (String key : invitations.keySet()) {
			if (invitations.get(key).getEmployeeEmail().equals(email) && invitations.get(key).getAnswer()!= Answer.DECLINED) {
				int appointmentKey = (invitations.get(key).getAppointmentID());
				array.add(appointment.get(appointmentKey));
			}
		}
		return array;
	}
	
	public ArrayList<Integer> getPendingAppointmentsForUser(String email) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (String key : invitations.keySet()) {
			if (invitations.get(key).getEmployeeEmail().equals(email) && invitations.get(key).getAnswer()== Answer.PENDING) {
				int appointmentKey = invitations.get(key).getAppointmentID();
				array.add(appointmentKey);
			}
		}
		return array;
	}
	
	public void updateInvitation(String email, int appointmentID, Invitation i){
		String id = email + "�" + appointmentID;
		if(invitations.containsKey(id)){
			Invitation local = invitations.get(id);
			local.update(i);
			fireUpdateEvent();
		}
		else {
			getInvitation(email, appointmentID);
		}
	}
	
	public void deleteInvitation(String email, int appointmentID){
		String id = email + "�" + appointmentID;
		if(invitations.containsKey(id)){
			invitations.remove(id);
			if(email.equals(username)) {
				connection.sendInvitationDeletion(email, appointmentID);
			}
			fireUpdateEvent();
		}
	}
	
	// key = name
	public void addRoom(String key, Room value){
		rooms.put(key, value);
		System.out.println("Key: " + key);
	}
	
	public Room getRoom(String id){
		Room r = rooms.get(id);
		if(r == null){
			System.err.println("No entry found in Hashmap");
		}
		return r;
	}
	
	public void updateRoom(String id, Room r){
		if(rooms.containsKey(id)){
			rooms.put(id, r);
		}
	}
	
	public void deleteRoom(String id){
		if(rooms.containsKey(id)){
			rooms.remove(id);
		}
	}
	
	// key = alarmID
	public void addAlarm(String email, int appointmentID, Alarm value){
		String key = email + "�" + appointmentID;
		System.out.println("Key: " + key);
		if (alarms.containsKey(key)) {
			updateAlarm(email, appointmentID, value);
			return;
		}
		alarms.put(key, value);
		fireUpdateEvent();
	}
	
	public void createAlarm(Alarm value) {
		if(appointment.containsKey(value.getEmployeeEmail()+"�"+value.getAppointmentID())) {
			connection.sendAlarmUpdate(value);
		}
		else {
			connection.sendAlarmCreation(value);
		}
	}
	
	public Alarm getAlarm(String email, int appointmentID){
		String id = email + "�" + appointmentID;
		Alarm a = alarms.get(id);
		if(a == null){
			System.err.println("No entry found in Hashmap");
			a = new Alarm(appointmentID, email);
			alarms.put(id, a);
			connection.sendAlarmQuery(email, Integer.toString(appointmentID));
		}
		return a;
	}
	
	public void updateAlarm(String email, int appointmentID, Alarm r){
		String id = email + "�" + appointmentID;
		if(alarms.containsKey(id)){
			Alarm a = alarms.get(id);
			a.update(r);
			fireUpdateEvent();
		}
		else {
			getAlarm(email, appointmentID);
		}
	}
	
	public void deleteAlarm(String email, int appointmentID){
		String id = email + "�" + appointmentID;
		if(alarms.containsKey(id)){
			alarms.remove(id);
			if(email.equals(username)) {
				connection.sendAlarmDeletion(email, appointmentID);
			}
			fireUpdateEvent();
		}
	}
	
	public static ApplicationModel getInstance(){
		if(model == null){
			model = new ApplicationModel();
		}
		return model;
	}
	
	public ArrayList<String> getRooms() {
		return new ArrayList<String>(rooms.keySet());
	}
	
	public ArrayList<Integer> getAppointments() {
		return new ArrayList<Integer>(appointment.keySet());
	}
	
	public ArrayList<String> getEmployees() {
		return new ArrayList<String>(employees.keySet());
	}
	
	public void addModelListener(ModelListener ml) {
		listeners.add(ml);
	}
	
	public void removeModelListener(ModelListener ml) {
		listeners.remove(ml);
	}
	
	private void fireUpdateEvent() {
		for(ModelListener ml : listeners) {
			ml.refresh();
		}
	}
	
	public String listKeys() {
		String keys = "Employee keys:\n";
		keys += employees.keySet();
		keys += "\nAppointment keys:\n";
		keys += appointment.keySet();
		keys += "\nRoom keys:\n";
		keys += rooms.keySet();
		keys += "\nInvitation keys:\n";
		keys += invitations.keySet();
		keys += "\nAlarm keys:\n";
		keys += alarms.keySet();
		keys += "\nCurrent user: " + username;
		return keys;
	}
	
	public ArrayList<Invitation> getInvitationsByAppointment(int appointmentID) {
		ArrayList<Invitation> invites = new ArrayList<Invitation>();
		ArrayList<String> keys = new ArrayList<String>(invitations.keySet());
		for(String s : keys) {
			if(s.split("�")[1].equals(Integer.toString(appointmentID))){
				invites.add(invitations.get(s));
			}
		}
		return invites;
	}
	
}

