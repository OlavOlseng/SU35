package models;

import java.util.Scanner;

import networking.*;

public class ModelTest {
	public static void main(String args[]) {
		Appointment app1 = new Appointment(1);
		app1.setDate("2013-03-18");
		app1.setDescription("Møte 1");
		app1.setLocation("NTNU");
		app1.setStartTime("12:15");
		app1.setEndTime("13:15");
		app1.setMeetingLeader("olav@epost.no");
		app1.setMeetingRoom("Drivhuset");
		//ApplicationModel.getInstance().addAppointment(1, app1);
		
		Appointment app2 = new Appointment(2);
		app2.setDate("2013-03-18");
		app2.setDescription("Møte 2");
		app2.setLocation("NTNU");
		app2.setStartTime("12:55");
		app2.setEndTime("13:55");
		app2.setMeetingLeader("august@epost.no");
		app2.setMeetingRoom("R-bygget R-7");
		//ApplicationModel.getInstance().addAppointment(2, app2);
		
		Appointment app3 = new Appointment(3);
		app3.setDate("2013-03-18");
		app3.setDescription("Møte 3");
		app3.setLocation("NTNU");
		app3.setStartTime("12:00");
		app3.setEndTime("13:00");
		app3.setMeetingLeader("sindre@epost.no");
		app3.setMeetingRoom("Stripa S-1");
		
		Room room1 = new Room("Drivhuset", 30);
		Room room2 = new Room("R-bygget R-7", 500);
		Room room3 = new Room("Stripa S-1", 200);
		
		Appointment app4 = new Appointment(4);
		app4.setDate("2013-03-18");
		app4.setDescription("Møte 4");
		app4.setLocation("NTNU");
		app4.setStartTime("13:05");
		app4.setEndTime("14:05");
		app4.setMeetingLeader("fredrik@epost.no");
		
		RoomManager roomManager = new RoomManager(6, app4.getDate(), app4.getStartTime(), app4.getEndTime());
		roomManager.addAppointment(app1);
		roomManager.addAppointment(app2);
		roomManager.addAppointment(app3);
		roomManager.addRoom(room1.getName(), room1.getRoomSize());
		roomManager.addRoom(room2.getName(), room2.getRoomSize());
		roomManager.addRoom(room3.getName(), room3.getRoomSize());
		
		String room = roomManager.pickSuitableRoom();
		app4.setMeetingRoom(room);
		
		System.out.println(app4.toString());
		System.out.println(app4.getFormattedDate());
		System.out.println(app4.getFormattedStartTime());
		System.out.println(app4.getFormattedEndTime());
		
		Alarm alarm = new Alarm(2, "fredrik@epost.no");
		alarm.setDescription("Kebab fo shizzles!");
		alarm.setTime("15");
		
		Client client = new Client();
		System.out.println("Connecting to server...");
		client.connect();
		System.out.println("Connected to " + client.getHostAddress());
		Scanner scanner = new Scanner(System.in);
		String command = null;
		boolean exit = false;
		do {
			command = scanner.nextLine();
			switch(command) {
				case "EXIT": {
					exit = true;
				} break;
				case "GET_EMPLOYEE": {
					String email = scanner.nextLine();
					client.sendEmployeeQuery(email);
				} break;
				case "GET_APPOINTMENT": {
					String ID = scanner.nextLine();
					client.sendAppointmentQuery(ID);
				} break;
				case "GET_INVITATION": {
					String email = scanner.nextLine();
					if(email.equals("")) email = null;
					String ID = scanner.nextLine();
					if(ID.equals("")) ID = null;
					client.sendInvitationQuery(email, ID);
				} break;
				case "GET_ROOM": {
					String name = scanner.nextLine();
					client.sendRoomQuery(name);
				} break;
				case "GET_ALARM": {
					String email = scanner.nextLine();
					String ID = scanner.nextLine();
					client.sendAlarmQuery(email, ID);
				} break;
				case "GET_GROUP": {
					String email = scanner.nextLine();
					client.sendGroupQuery(email);
				} break;
				case "DELETE_APPOINTMENT": {
					String ID = scanner.nextLine();
					client.sendAppointmentDeletion(ID);
				} break;
				case "DELETE_INVITATION": {
					String email = scanner.nextLine();
					String ID = scanner.nextLine();
					client.sendInvitationDeletion(email, ID);
				} break;
				case "DELETE_ALARM": {
					String email = scanner.nextLine();
					String ID = scanner.nextLine();
					client.sendAlarmDeletion(email, ID);
				} break;
				case "CREATE_ALARM": {
					client.sendAlarmCreation(alarm);
				} break;
				default: {
					System.err.println("UNKNOWN COMMAND");
				} break;
			}
		} while(!exit);
		/*
		String msg = "Per#Mathias".split("#")[0];
		System.out.println(msg);
		*/
		System.out.println("Closing connection to server...");
		client.disconnect();
	}
}
