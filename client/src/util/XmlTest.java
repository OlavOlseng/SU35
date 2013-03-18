package util;

import models.Invitation;
import models.Alarm;
import models.Room;

public class XmlTest {

	public static void main(String[] args) {
		XMLFactory factory = new XMLFactory();
		Room r = new Room("R7", 150);
		Invitation i = new Invitation("sindrma@gmail.com", 1);
		i.setMessage("invited to meeting");
		Alarm a = new Alarm(1, "sindrma@gmail.com");
		a.setDate("17:00");
		a.setDescription("møte");
		System.out.println(factory.makeRoomXML(r));
		System.out.println(factory.makeInvitationXML(i));
		System.out.println(factory.makeAlarmXML(a));
	}

}
