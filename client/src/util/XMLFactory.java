package util;


import java.util.ArrayList;

import models.Appointment;
import models.Employee;
import nu.xom.Document;
import nu.xom.Element;

public class XMLFactory {
	
	private Element root;
	
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
	
	public String makeEmployee(Employee e) {
		ArrayList<String> employeeInfo = makeStringArray(e.getEmail(), e.getFirstName(), 
				e.getLastName(),e.getHomePhone(),e.getMobilePhone());
		createNewObject("employee");
		addChild(employeeInfo, "email", "firstname", "lastname", "homephone", "mobilephone");
		return getXML();
	}
	
	public String makeAppointment(Appointment a){
		ArrayList<String> appointmentInfo = makeStringArray(Integer.toString(
				a.getAppointmentID()), a.getFormattedStartTime(), 
				a.getFormattedEndTime(), a.getDescription(), 
				a.getLocation(), a.getMeetingLeader(), a.getMeetingRoom());
		createNewObject("appointment");
		addChild(appointmentInfo, "appointmentID", "starttime", "endtime", 
				"description", "location", "meetingleader", "room");
		return getXML();
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
