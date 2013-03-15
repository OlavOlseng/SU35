package util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import javax.xml.parsers.*;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import models.Alarm;
import models.ApplicationModel;
import models.Appointment;
import models.Employee;
import models.Invitation;
import models.Invitation.Answer;
import models.Room;

public class XMLAssembler {
	
	public static Document getDocument(String XML) throws ValidityException, ParsingException, IOException{
		Document d = new Builder().build(XML);
		return d;
	}
	
	public Employee assembleEmployee(Element personElement, Employee e) {
		
		Element element = personElement.getFirstChildElement("email");
		if (element != null) {
			e.setEmail(element.getValue());
		}
		element = personElement.getFirstChildElement("firstname");
		if (element != null) {
			e.setFirstName(element.getValue());
		}
		element = personElement.getFirstChildElement("lastname");
		if (element != null) {
			e.setLastName(element.getValue());
		}
		element = personElement.getFirstChildElement("homephone");
		if (element != null) {
			e.setHomePhone(element.getValue());
		}
		
		element = personElement.getFirstChildElement("mobilephone");
		if (element != null) {
			e.setMobilePhone(element.getValue());
		}
		return e;
		
	}
	
	public Appointment assembleAppointment(Element appointmentElement, Appointment a)  {
		
		Element element = appointmentElement.getFirstChildElement("appointmentID");
		if (element != null) {
			a.setAppointmentID(Integer.parseInt(element.getValue()));
		}
		element = appointmentElement.getFirstChildElement("starttime");
		if(element != null){
			a.setStartTime(element.getValue());
		}
		element = appointmentElement.getFirstChildElement("endtime");
		if(element != null){
			a.setEndTime(element.getValue());
		}
		element = appointmentElement.getFirstChildElement("description");
		if(element != null){
			a.setDescription(element.getValue());
		}
		element = appointmentElement.getFirstChildElement("location");
		if(element != null){
			a.setLocation(element.getValue());
		}
		element = appointmentElement.getFirstChildElement("meetingleader");
		if(element != null){
			a.setMeetingLeader(element.getValue());
		}
		element = appointmentElement.getFirstChildElement("meetingroom");
		if(element != null){
			a.setMeetingRoom(element.getValue());
		}
		return a;
	}
	
	public Room assembleMeetingRoom(Element meetingRoomElement, Room mr){
		Element element = meetingRoomElement.getFirstChildElement("name");
		if(element != null){
			mr.setName(element.getValue());
		}
		element = meetingRoomElement.getFirstChildElement("size");
		if(element != null){
			mr.setRoomSize(Integer.parseInt(element.getValue()));
		}
		return mr;
	}
	
	public Invitation assembleInvitation(Element invitationElement, Invitation invite){
		Element element = invitationElement.getFirstChildElement("answer");
		if(element != null){
			invite.setAnswer(Answer.valueOf(element.getValue()));
		}
		element = invitationElement.getFirstChildElement("employeeEmail");
		if(element != null){
			invite.setEmployeeEmail(element.getValue());
		}
		element = invitationElement.getFirstChildElement("appointmentID");
		if(element != null){
			invite.setAppointmentID(Integer.parseInt(element.getValue()));
		}
		return invite;
	}
	
	public Alarm assembleAlarm(Element alarmElement, Alarm alarm){
		Element element = alarmElement.getFirstChildElement("alarmID");
		if(element != null){
			alarm.setAlarmID(Integer.parseInt(element.getValue()));
		}
		element = alarmElement.getFirstChildElement("employeeEmail");
		if(element != null){
			alarm.setEmployeeEmail(element.getValue());
		}
		return alarm;
	}
	

}


