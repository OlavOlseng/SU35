package models;

import java.util.HashMap;

public class ApplicationModel {
	private static ApplicationModel model = null;
	private HashMap<String, Employee> employees;
	private HashMap<Integer, Appointment> appointment;
	private HashMap<Integer, Room> rooms;
	private HashMap<String, Invitation> invitations;
	public String username; 
	
	private ApplicationModel(){
		employees = new HashMap<String, Employee>();
		appointment = new HashMap<Integer, Appointment>();
		rooms = new HashMap<Integer, Room>();
		invitations = new HashMap<String, Invitation>();
	}
	
	public Employee getEmployee(String email){
		Employee e = employees.get(email);
		if(e == null){
			//TODO add method to clientmessagehandler
		}
		return e;
	}
	
	public void updateEmployee(String email, Employee e){
		if(employees.containsKey(email)){
			employees.put(email, e);
		}
	}
	
	public Appointment getAppointment(int aID){
		Appointment a = appointment.get(aID);
		if(a == null){
			// TODO add method to clientmessagehandler
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
	
	public Invitation getInvitation(String id){
		Invitation i = invitations.get(id);
		if(i == null){
			// TODO add method to clientmessagehandler
		}
		return i;
	}
	
	public void updateInvitation(String id, Invitation i){
		if(invitations.containsKey(id)){
			invitations.put(id, i);
		}
	}
	
	public void deleteInvitation(String id){
		if(invitations.containsKey(id)){
			invitations.remove(id);
		}
	}
	
	public Room getMeetingRoom(int id){
		Room r = rooms.get(id);
		if(r == null){
			//TODO add method to clientmessagehandler
		}
		return r;
	}
	
	public void updateRoom(int id, Room r){
		if(rooms.containsKey(id)){
			rooms.put(id, r);
		}
	}
	
	public void deleteRoom(int id){
		if(rooms.containsKey(id)){
			rooms.remove(id);
		}
	}
	
	public static ApplicationModel getInstance(){
		if(model == null){
			model = new ApplicationModel();
		}
		return model;
	}
	
}
