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
	
	@Override
	public void getEntry(String[] data) {
		if(Integer.parseInt(data[1]) == 0){
			Document d = null;
			try {
				d = XMLAssembler.getDocument(data[4]);
			} catch (ValidityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParsingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
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
				return;
			}
		}else{
			System.out.println("Error: " + data[1] + " : " + data[2]);
		}
		
	}

	@Override
	public void updateEntry(String[] data) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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
		ApplicationModel.getInstance().addEmployee(savedEmployee.getEmail(), assembler.assembleEmployee(element, savedEmployee));
		
	}
	
	public void updateAppointment(Element element){
		Element aID = element.getFirstChildElement("appointmentID");
		Appointment savedAppointment = ApplicationModel.getInstance().getAppointment(Integer.parseInt(aID.getValue()));
		if(savedAppointment == null){
			return;
		}
		ApplicationModel.getInstance().addAppointment(savedAppointment.getAppointmentID(), assembler.assembleAppointment(element, savedAppointment));
	}
	
	public void updateRoom(Element element){
		Element mID = element.getFirstChildElement("meetingroomID");
		Room savedRoom = ApplicationModel.getInstance().getRoom(Integer.parseInt(mID.getValue()));
		if(savedRoom == null){
			return;
		}
		ApplicationModel.getInstance().addRoom(savedRoom.getName(), assembler.assembleMeetingRoom(element, savedRoom));
	}
	
	public void updateInvitation(Element element){
		Element iID = element.getFirstChildElement("invitationID");
		Invitation savedInvation = ApplicationModel.getInstance().getInvitation(iID.getValue());
		if(savedInvation == null){
			return;
		}
		String key = savedInvation.getEmployeeEmail() + "," + savedInvation.getAppointmentID();
		ApplicationModel.getInstance().addInvitation(key, assembler.assembleInvitation(element, savedInvation));
	}
	
	public void updateAlarm(Element element){
		Element alarmID = element.getFirstChildElement("alarmID");
		Alarm savedAlarm = ApplicationModel.getInstance().getAlarm(Integer.parseInt(alarmID.getValue()));
		if(savedAlarm == null){
			return;
		}
		ApplicationModel.getInstance().addAlarm(savedAlarm.getAlarmID(), assembler.assembleAlarm(element, savedAlarm));
	}

}
