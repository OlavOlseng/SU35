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
				d = assembler.getDocument(data[4]);
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
				addEmployee(e); break;
			case "appointment":
				addAppointment(e); break;
			case "room":
				addRoom(e); break;
			case "invitation":
				addInvitation(e); break;
			case "alarm":
				addAlarm(e); break;
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
				d = assembler.getDocument(data[4]);
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
				updateEmployee(e); break;
			case "appointment":
				updateAppointment(e); break;
			case "room":
				updateRoom(e); break;
			case "invitation":
				updateInvitation(e); break;
			case "alarm":
				updateAlarm(e); break;
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
		// TODO Create a better implementation
		getEntry(data);
	}

	@Override
	public void deleteEntry(String[] data) {
		if(Integer.parseInt(data[1]) == 0){
			Document d = null;
			try {
				d = assembler.getDocument(data[4]);
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
				deleteEmployee(e); break;
			case "appointment":
				deleteAppointment(e); break;
			case "room":
				deleteRoom(e); break;
			case "invitation":
				deleteInvitation(e); break;
			case "alarm":
				deleteAlarm(e); break;
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
		if(data[1].equals("0")) {
			ApplicationModel.getInstance().username = data[4];
			System.out.println("Login successful");
		}
		else {
			System.err.println("Login failed");
		}
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
		Element email = element.getFirstChildElement("employeeEmail");
		Element aID = element.getFirstChildElement("appointmentID");
		Invitation savedInvation = ApplicationModel.getInstance().getInvitation(email.getValue(), Integer.parseInt(aID.getValue()));
		if(savedInvation == null){
			return;
		}
		ApplicationModel.getInstance().updateInvitation(savedInvation.getEmployeeEmail(), savedInvation.getAppointmentID(), assembler.assembleInvitation(element));
	}
	
	public void updateAlarm(Element element){
		Element email = element.getFirstChildElement("employeeEmail");
		Element aID = element.getFirstChildElement("appointmentID");
		Alarm savedAlarm = ApplicationModel.getInstance().getAlarm(email.getValue(), Integer.parseInt(aID.getValue()));
		if(savedAlarm == null){
			return;
		}
		ApplicationModel.getInstance().updateAlarm(savedAlarm.getEmployeeEmail(), savedAlarm.getAppointmentID(), assembler.assembleAlarm(element));
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
		ApplicationModel.getInstance().addInvitation(i.getEmployeeEmail(), i.getAppointmentID(), i);
	}
	
	public void addAlarm(Element element){
		Alarm a = assembler.assembleAlarm(element);
		ApplicationModel.getInstance().addAlarm(a.getEmployeeEmail(), a.getAppointmentID(),a);
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
		Element email = element.getFirstChildElement("employeeEmail");
		Element aID = element.getFirstChildElement("appointmentID");
		Invitation savedInvation = ApplicationModel.getInstance().getInvitation(email.getValue(), Integer.parseInt(aID.getValue()));
		if(savedInvation == null){
			return;
		}
		ApplicationModel.getInstance().deleteInvitation(savedInvation.getEmployeeEmail(), savedInvation.getAppointmentID());
	}
	
	public void deleteAlarm(Element element){
		Element email = element.getFirstChildElement("employeeEmail");
		Element aID = element.getFirstChildElement("appointmentID");
		Alarm savedAlarm = ApplicationModel.getInstance().getAlarm(email.getValue(), Integer.parseInt(aID.getValue()));
		if(savedAlarm == null){
			return;
		}
		ApplicationModel.getInstance().deleteAlarm(savedAlarm.getEmployeeEmail(), savedAlarm.getAppointmentID());
	}
}
