package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import models.Invitation.Answer;

public class ApplicationModel {
	private static ApplicationModel model = null;
	private HashMap<String, Employee> employees;
	private HashMap<Integer, Appointment> appointment;
	private HashMap<String, Room> rooms;
	private HashMap<String, Invitation> invitations;
	private HashMap<String, Alarm> alarms;
	public String username; 
	
	private ApplicationModel(){
		employees = new HashMap<String, Employee>();
		appointment = new HashMap<Integer, Appointment>();
		rooms = new HashMap<String, Room>();
		invitations = new HashMap<String, Invitation>();
		alarms = new HashMap<String, Alarm>();
	}
	
	// key = email
	public void addEmployee(String key, Employee value){
		employees.put(key, value);
		System.out.println("Key: " + key);
	}
	
	public Employee getEmployee(String email){
		Employee e = employees.get(email);
		if(e == null){
			System.err.println("No entry found in Hashmap");
		}
		return e;
	}
	
	public void updateEmployee(String email, Employee e){
		if(employees.containsKey(email)){
			employees.put(email, e);
		}
	}
	
	public void deleteEmployee(String email){
		if(employees.containsKey(email)){
			employees.remove(email);
		}
	}
	
	// key = appointmentID
	public void addAppointment(int key, Appointment value){
		appointment.put(key, value);
		System.out.println("Key: " + key);
	}
	
	public Appointment getAppointment(int aID){
		Appointment a = appointment.get(aID);
		if(a == null){
			System.err.println("No entry found in Hashmap");
		}
		return a;
	}
	
	public void updateAppointment(int aID, Appointment a){
		if(appointment.containsKey(aID)){
			appointment.put(aID, a);
		}
	}
	
	public void deleteAppointment(int aID){
		if(appointment.containsKey(aID)){
			appointment.remove(aID);
		}
	}
	
	// key = email and appointmentID
	public void addInvitation(String email, int appointmentID, Invitation value){
		String key = email + "�" + appointmentID;
		invitations.put(key, value);
		System.out.println("Key: " + key);
	}
	
	public Invitation getInvitation(String email, int appointmentID){
		String id = email + "�" + appointmentID;
		Invitation i = invitations.get(id);
		if(i == null){
			System.err.println("No entry found in Hashmap");
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
	
	public void updateInvitation(String email, int appointmentID, Invitation i){
		String id = email + "�" + appointmentID;
		if(invitations.containsKey(id)){
			invitations.put(id, i);
		}
	}
	
	public void deleteInvitation(String email, int appointmentID){
		String id = email + "�" + appointmentID;
		if(invitations.containsKey(id)){
			invitations.remove(id);
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
		alarms.put(key, value);
	}
	
	public Alarm getAlarm(String email, int appointmentID){
		String id = email + "�" + appointmentID;
		Alarm a = alarms.get(id);
		if(a == null){
			System.err.println("No entry found in Hashmap");
		}
		return a;
	}
	
	public void updateAlarm(String email, int appointmentID, Alarm r){
		String id = email + "�" + appointmentID;
		if(alarms.containsKey(id)){
			alarms.put(id, r);
		}
	}
	
	public void deleteAlarm(String email, int appointmentID){
		String id = email + "�" + appointmentID;
		if(alarms.containsKey(id)){
			alarms.remove(id);
		}
	}
	
	public static ApplicationModel getInstance(){
		if(model == null){
			model = new ApplicationModel();
		}
		return model;
	}
	
	public HashSet<String> getRooms() {
		return new HashSet<String>(rooms.keySet());
	}
	
	public HashSet<Integer> getAppointments() {
		return new HashSet<Integer>(appointment.keySet());
	}
	
	public String[] getEmployees() {
		return (String[])employees.keySet().toArray();
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
	
}
