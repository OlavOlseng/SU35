package networking;

import java.io.IOException;
import util.XMLAssembler;
import models.Alarm;
import models.ApplicationModel;
import models.Appointment;
import models.Employee;
import models.Invitation;
import models.Room;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

public class ClientMessageHandler extends MessageHandler{
	private XMLAssembler assembler;
	
	public ClientMessageHandler(){
		assembler = new XMLAssembler();
	}
	
	@Override
	public void getEntry(String[] data) {
		if(Integer.parseInt(data[1]) == 0){
			Document d = null;
			try {
				d = XMLAssembler.getDocument(data[4]);
			} catch (ValidityException e1) {
				e1.printStackTrace();
			} catch (ParsingException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Element e = d.getRootElement();
			switch(e.getLocalName()){
			case "employee":
				addEmployee(e);
			case "appointment":
				addAppointment(e);
			case "meetingroom":
				addRoom(e);
			case "invitation":
				addInvitation(e);
			case "alarm":
				addAlarm(e);
			default:
				System.err.println("Unknown localname.");
				return;
			}
		}else{
			System.err.println("Error: " + data[1] + " : " + data[2]);
		}
		
	}

	@Override
	public void updateEntry(String[] data) {
		if(Integer.parseInt(data[1]) == 0){
			Document d = null;
			try {
				d = XMLAssembler.getDocument(data[4]);
			} catch (ValidityException e1) {
				e1.printStackTrace();
			} catch (ParsingException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Element e = d.getRootElement();
			switch(e.getLocalName()){
			case "employee":
				updateEmployee(e);
			case "appointment":
				updateAppointment(e);
			case "meetingroom":
				updateRoom(e);
			case "invitation":
				updateInvitation(e);
			case "alarm":
				updateAlarm(e);
			default:
				System.err.println("Unknown localname.");
				return;
			}
		}else{
			System.err.println("Error: " + data[1] + " : " + data[2]);
		}
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
		if(Integer.parseInt(data[1]) == 0){
			Document d = null;
			try {
				d = XMLAssembler.getDocument(data[4]);
			} catch (ValidityException e1) {
				e1.printStackTrace();
			} catch (ParsingException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Element e = d.getRootElement();
			switch(e.getLocalName()){
			case "employee":
				deleteEmployee(e);
			case "appointment":
				deleteAppointment(e);
			case "meetingroom":
				deleteRoom(e);
			case "invitation":
				deleteInvitation(e);
			case "alarm":
				deleteAlarm(e);
			default:
				System.err.println("Unknown localname.");
				return;
			}
		}else{
			System.err.println("Error: " + data[1] + " : " + data[2]);
		}
	}

	@Override
	public void checkLogin(String[] data) {
		// TODO Auto-generated method stub
	}

	
	public void updateEmployee(Element element){
		Element email = element.getFirstChildElement("email");
		Employee savedEmployee = ApplicationModel.getInstance().getEmployee(email.getValue());
		if(savedEmployee == null){
			return;
		}
		ApplicationModel.getInstance().updateEmployee(savedEmployee.getEmail(), assembler.assembleEmployee(element));
		
	}
	
	public void updateAppointment(Element element){
		Element aID = element.getFirstChildElement("appointmentID");
		Appointment savedAppointment = ApplicationModel.getInstance().getAppointment(Integer.parseInt(aID.getValue()));
		if(savedAppointment == null){
			return;
		}
		ApplicationModel.getInstance().updateAppointment(savedAppointment.getAppointmentID(), assembler.assembleAppointment(element));
	}
	
	public void updateRoom(Element element){
		Element rID = element.getFirstChildElement("roomID");
		Room savedRoom = ApplicationModel.getInstance().getRoom(rID.getValue());
		if(savedRoom == null){
			return;
		}
		ApplicationModel.getInstance().updateRoom(savedRoom.getName(), assembler.assembleRoom(element));
	}
	
	public void updateInvitation(Element element){
		Element iID = element.getFirstChildElement("invitationID");
		Invitation savedInvation = ApplicationModel.getInstance().getInvitation(iID.getValue());
		if(savedInvation == null){
			return;
		}
		String key = savedInvation.getEmployeeEmail() + "," + savedInvation.getAppointmentID();
		ApplicationModel.getInstance().updateInvitation(key, assembler.assembleInvitation(element));
	}
	
	public void updateAlarm(Element element){
		Element alarmID = element.getFirstChildElement("alarmID");
		Alarm savedAlarm = ApplicationModel.getInstance().getAlarm(Integer.parseInt(alarmID.getValue()));
		if(savedAlarm == null){
			return;
		}
		String id = savedAlarm.getEmployeeEmail() + "," + savedAlarm.getAppointmentID();
		ApplicationModel.getInstance().updateAlarm(id, assembler.assembleAlarm(element));
	}

	public void addEmployee(Element element){
		Employee employee = assembler.assembleEmployee(element);
		ApplicationModel.getInstance().addEmployee(employee.getEmail(), employee);
	}
	
	public void addAppointment(Element element){
		Appointment appointment = assembler.assembleAppointment(element);
		ApplicationModel.getInstance().addAppointment(appointment.getAppointmentID(), appointment);
	}
	
	public void addRoom(Element element){
		Room room = assembler.assembleRoom(element);
		ApplicationModel.getInstance().addRoom(room.getName(), room);
	}
	
	public void addInvitation(Element element){
		Invitation i = assembler.assembleInvitation(element);
		String key = i.getEmployeeEmail() + "," + i.getAppointmentID();
		ApplicationModel.getInstance().addInvitation(key, i);
	}
	
	public void addAlarm(Element element){
		Alarm a = assembler.assembleAlarm(element);
		String id = a.getEmployeeEmail() + "," + a.getAppointmentID();
		ApplicationModel.getInstance().addAlarm(id, a);
	}
	
	public void deleteEmployee(Element element){
		Element email = element.getFirstChildElement("email");
		Employee savedEmployee = ApplicationModel.getInstance().getEmployee(email.getValue());
		if(savedEmployee == null){
			return;
		}
		ApplicationModel.getInstance().deleteEmployee(savedEmployee.getEmail());
	}
	
	public void deleteAppointment(Element element){
		Element aID = element.getFirstChildElement("appointmentID");
		Appointment savedAppointment = ApplicationModel.getInstance().getAppointment(Integer.parseInt(aID.getValue()));
		if(savedAppointment == null){
			return;
		}
		ApplicationModel.getInstance().deleteAppointment(savedAppointment.getAppointmentID());
	}
	
	public void deleteRoom(Element element){
		Element rID = element.getFirstChildElement("roomID");
		Room savedRoom = ApplicationModel.getInstance().getRoom(rID.getValue());
		if(savedRoom == null){
			return;
		}
		ApplicationModel.getInstance().deleteRoom(savedRoom.getName());
	}
	
	public void deleteInvitation(Element element){
		Element iID = element.getFirstChildElement("invitationID");
		Invitation savedInvation = ApplicationModel.getInstance().getInvitation(iID.getValue());
		if(savedInvation == null){
			return;
		}
		String key = savedInvation.getEmployeeEmail() + "," + savedInvation.getAppointmentID();
		ApplicationModel.getInstance().deleteInvitation(key);
	}
	
	public void deleteAlarm(Element element){
		Element alarmID = element.getFirstChildElement("alarmID");
		Alarm savedAlarm = ApplicationModel.getInstance().getAlarm(Integer.parseInt(alarmID.getValue()));
		if(savedAlarm == null){
			return;
		}
		String id = savedAlarm.getEmployeeEmail() + "," + savedAlarm.getAppointmentID();
		ApplicationModel.getInstance().deleteAlarm(id);
	}
}
