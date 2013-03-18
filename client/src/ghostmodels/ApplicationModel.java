package ghostmodels;

import java.util.HashMap;

public class ApplicationModel {
	private static ApplicationModel model = null;
	public HashMap<String, Employee> employees;
	public HashMap<Integer, Appointment> appointment;
	private HashMap<String, Room> rooms;
	private HashMap<String, Invitation> invitations;
	private HashMap<Integer, Alarm> alarms;
	public String username; 
	
	public ApplicationModel(){
		employees = new HashMap<String, Employee>();
		appointment = new HashMap<Integer, Appointment>();
		rooms = new HashMap<String, Room>();
		invitations = new HashMap<String, Invitation>();
		alarms = new HashMap<Integer, Alarm>();
		
		employees.put("email", new Employee("email","fornavn","etternavn","12345678","87654321"));
		appointment.put(0, new Appointment(0));
		appointment.get(0).setMeetingLeader(employees.get("email"));
		appointment.get(0).setDescription("Møte");
	}
	
	// key = email
	public void addEmployee(String key, Employee value){
		employees.put(key, value);
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
	
	// key = appointmentID
	public void addAppointment(int key, Appointment value){
		appointment.put(key, value);
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
	
	// key = email and appointmentID
	public void addInvitation(String key, Invitation value){
		invitations.put(key, value);
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
	
	// key = name
	public void addRoom(String key, Room value){
		rooms.put(key, value);
	}
	
	public Room getRoom(int id){
		Room r = rooms.get(id);
		if(r == null){
			//TODO add method to clientmessagehandler
		}
		return r;
	}
	
	public void updateRoom(String id, Room r){
		if(rooms.containsKey(id)){
			rooms.put(id, r);
		}
	}
	
	public void deleteRoom(int id){
		if(rooms.containsKey(id)){
			rooms.remove(id);
		}
	}
	
	// key = alarmID
	public void addAlarm(int key, Alarm value){
		alarms.put(key, value);
	}
	
	public Alarm getAlarm(int id){
		Alarm a = alarms.get(id);
		if(a == null){
			// TODO add method to clientmessagehandler
		}
		return a;
	}
	
	public void updateAlarm(int id, Alarm r){
		if(alarms.containsKey(id)){
			alarms.put(id, r);
		}
	}
	
	public void deleteAlarm(int id){
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
	
}
