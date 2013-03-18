package util;


import java.util.ArrayList;

import models.Alarm;
import models.Appointment;
import models.Employee;
import models.Invitation;
import models.Room;
import nu.xom.Document;
import nu.xom.Element;

public class XMLFactory {
	
	private Element root;
	
	public String makeEmployeeXML(Employee e) {
		ArrayList<String> employeeInfo = makeStringArray(e.getEmail(), e.getFirstName(), 
				e.getLastName(),e.getHomePhone(),e.getMobilePhone());
		createNewObject("employee");
		addChild(employeeInfo, "email", "firstname", "lastname", "homephone", "mobilephone");
		return getXML();
	}
	
	public String makeAppointmentXML(Appointment a){
		ArrayList<String> appointmentInfo = makeStringArray(Integer.toString(
				a.getAppointmentID()), a.getFormattedStartTime(), 
				a.getFormattedEndTime(), a.getDescription(), 
				a.getLocation(), a.getMeetingLeader(), a.getMeetingRoom());
		createNewObject("appointment");
		addChild(appointmentInfo, "appointmentID", "starttime", "endtime", 
				"description", "location", "meetingleader", "room");
		return getXML();
	}
	
	public String makeRoomXML(Room r){
		ArrayList<String> roomInfo = makeStringArray(Integer.toString(r.getRoomSize()), r.getName());
		createNewObject("room");
		addChild(roomInfo, "size", "name");
		return getXML();
	}
	
	public String makeInvitationXML(Invitation i){
		ArrayList<String> invitationInfo = makeStringArray(i.getAnswer().toString(), i.getMessage(), String.valueOf(i.getAppointmentID()), i.getEmployeeEmail());
		createNewObject("invitation");
		addChild(invitationInfo, "answer", "message", "appointmentID", "employeeEmail");
		return getXML();
	}
	
	public String makeAlarmXML(Alarm a){
		String aID = a.getEmployeeEmail() + "¤" + a.getAppointmentID();
		ArrayList<String> alarmInfo = makeStringArray(aID, a.getDate(), a.getDescription());
		createNewObject("invitation");
		addChild(alarmInfo, "alarmID", "date", "description");
		return getXML();
	}
	
	private void createNewObject(String name) {
		root = new Element(name);
	}
	
	private void addChild(ArrayList<String> values, String ...names) {
		int count = 0;
		for (String s : values) {
			Element element = new Element(names[count]);
			element.appendChild(s);
			root.appendChild(element);
			count++;
		}
	}
	
	private String getXML() {
		Document d = new Document(root);
		root = null;
		return d.toXML();
	}
	
	private ArrayList<String> makeStringArray(String ...values){
		ArrayList<String> arrValues = new ArrayList<String>();
		for(String s : values){
			arrValues.add(s);
		}
		return arrValues;
	}
}
