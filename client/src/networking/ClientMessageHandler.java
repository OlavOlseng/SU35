package networking;

import java.text.ParseException;

import util.XMLAssembler;
import models.ApplicationModel;
import models.Appointment;
import models.Employee;
import models.Invitation;
import models.Room;
import nu.xom.Element;

public class ClientMessageHandler extends MessageHandler{

	@Override
	public void getEntry(String[] data) {
		
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
		default:
			return;
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

	
	public void updateEmployee(Element element) throws ParseException{
		Element email = element.getFirstChildElement("email");
		Employee savedEmployee = ApplicationModel.getInstance().getEmployee(email.getValue());
		if(savedEmployee == null){
			return;
		}
		XMLAssembler.assembleEmployee(element, savedEmployee);
		
	}
	
	public void updateAppointment(Element element) throws ParseException{
		Element aID = element.getFirstChildElement("appointmentID");
		Appointment savedAppointment = ApplicationModel.getInstance().getAppointment(Integer.parseInt(aID.getValue()));
		if(savedAppointment == null){
			return;
		}
		XMLAssembler.assembleAppointment(element, savedAppointment);
	}
	
	public void updateRoom(Element element){
		Element mID = element.getFirstChildElement("meetingroomID");
		Room savedRoom = ApplicationModel.getInstance().getMeetingRoom(Integer.parseInt(mID.getValue()));
		if(savedRoom == null){
			return;
		}
		XMLAssembler.assembleMeetingRoom(element, savedRoom);
	}
	
	public void updateInvitation(Element element){
		Element iID = element.getFirstChildElement("invitationID");
		Invitation savedInvation = ApplicationModel.getInstance().getInvitation(iID.getValue());
		if(savedInvation == null){
			return;
		}
		XMLAssembler.assembleInvitation(element, savedInvation);
	}

}
