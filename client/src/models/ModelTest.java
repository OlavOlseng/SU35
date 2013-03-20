package models;

import java.util.Scanner;

import networking.*;

public class ModelTest {
	public static void main(String args[]) {
		Client client = ApplicationModel.getInstance().connection;
		Scanner scanner = new Scanner(System.in);
		String command = null;
		boolean exit = false;
		do {
			command = scanner.nextLine();
			switch(command) {
				case "EXIT": {
					exit = true;
				} break;
				case "LOGIN": {
					System.out.print("Username: ");
					String username = scanner.nextLine();
					System.out.print("Password: ");
					String password = scanner.nextLine();
					client.sendLoginQuery(username, password);
					//client.getAllAppointments();
					//client.getAllEmployees();
					//client.sendRoomQuery("foo");
				} break;
				case "GET_EMPLOYEE": {
					System.out.print("Employee email: ");
					String email = scanner.nextLine();
					ApplicationModel.getInstance().getEmployee(email);
				} break;
				case "GET_APPOINTMENT": {
					System.out.print("Appointment ID: ");
					int ID = Integer.parseInt(scanner.nextLine());
					ApplicationModel.getInstance().getAppointment(ID);
				} break;
				case "GET_INVITATION": {
					System.out.print("Employee email: ");
					String email = scanner.nextLine();
					if(email.equals("")) email = null;
					System.out.print("Appointment ID: ");
					int ID = Integer.parseInt(scanner.nextLine());
					ApplicationModel.getInstance().getInvitation(email, ID);
				} break;
				case "GET_ROOM": {
					System.out.print("Room name: ");
					String name = scanner.nextLine();
					client.sendRoomQuery(name);
				} break;
				case "GET_ALARM": {
					System.out.print("Employee email: ");
					String email = scanner.nextLine();
					System.out.print("Appointment ID: ");
					String ID = scanner.nextLine();
					client.sendAlarmQuery(email, ID);
				} break;
				case "GET_GROUP": {
					System.out.print("Group email: ");
					String email = scanner.nextLine();
					client.sendGroupQuery(email);
				} break;
				case "DELETE_EMPLOYEE": {
					System.out.print("Employee email: ");
					String email = scanner.nextLine();
					client.sendEmployeeDeletion(email);
				} break;
				case "DELETE_APPOINTMENT": {
					System.out.print("Appointment ID: ");
					String ID = scanner.nextLine();
					client.sendAppointmentDeletion(ID);
				} break;
				case "DELETE_INVITATION": {
					System.out.print("Employee email: ");
					String email = scanner.nextLine();
					System.out.print("Appointment ID: ");
					String ID = scanner.nextLine();
					client.sendInvitationDeletion(email, ID);
				} break;
				case "DELETE_ROOM": {
					System.out.print("Room name: ");
					String name = scanner.nextLine();
					client.sendRoomDeletion(name);
				} break;
				case "DELETE_ALARM": {
					System.out.print("Employee email: ");
					String email = scanner.nextLine();
					System.out.print("Appointment ID: ");
					String ID = scanner.nextLine();
					client.sendAlarmDeletion(email, ID);
				} break;
				case "CREATE_APPOINTMENT": {
					System.out.print("Appointment ID: ");
					int ID = Integer.parseInt(scanner.nextLine());
					System.out.print("Appointment title: ");
					String title = scanner.nextLine();
					System.out.print("Appointment date: ");
					String date = scanner.nextLine();
					System.out.print("Appointment start time: ");
					String startTime = scanner.nextLine();
					System.out.print("Appointment end time: ");
					String endTime = scanner.nextLine();
					System.out.print("Appointment description: ");
					String description = scanner.nextLine();
					System.out.print("Appointment location: ");
					String location = scanner.nextLine();
					System.out.print("Appointment meeting leader: ");
					String leader = scanner.nextLine();
					System.out.print("Appointment meeting room: ");
					String room = scanner.nextLine();
					Appointment appointment = new Appointment(ID);
					appointment.setTitle(title);
					appointment.setDate(date);
					appointment.setStartTime(startTime);
					appointment.setEndTime(endTime);
					appointment.setDescription(description);
					appointment.setLocation(location);
					appointment.setMeetingLeader(leader);
					appointment.setMeetingRoom(room);
					client.sendAppointmentCreation(appointment);
				} break;
				case "CREATE_INVITATION": {
					System.out.print("Employee email: ");
					String email = scanner.nextLine();
					System.out.print("Appointment ID: ");
					int ID = Integer.parseInt(scanner.nextLine());
					System.out.print("Invitation answer: ");
					String answer = scanner.nextLine();
					System.out.print("Invitation message: ");
					String message = scanner.nextLine();
					Invitation invitation = new Invitation(email, ID);
					switch(answer) {
						case "ACCEPTED": {
							invitation.setAnswer(Invitation.Answer.ACCEPTED);
						} break;
						case "DECLINED": {
							invitation.setAnswer(Invitation.Answer.DECLINED);
						} break;
						default: {
							invitation.setAnswer(Invitation.Answer.PENDING);
						} break;
					}
					invitation.setMessage(message);
					client.sendInvitationCreation(invitation);
				} break;
				case "CREATE_ALARM": {
					System.out.print("Employee email: ");
					String email = scanner.nextLine();
					System.out.print("Appointment ID: ");
					int ID = Integer.parseInt(scanner.nextLine());
					System.out.print("Alarm time: ");
					String time = scanner.nextLine();
					System.out.print("Alarm description: ");
					String description = scanner.nextLine();
					Alarm alarm = new Alarm(ID, email);
					alarm.setTime(time);
					alarm.setDescription(description);
					client.sendAlarmCreation(alarm);
				} break;
				case "UPDATE_EMPLOYEE": {
					System.out.print("Employee email: ");
					String email = scanner.nextLine();
					System.out.print("Employee first name: ");
					String first = scanner.nextLine();
					System.out.print("Employee last name: ");
					String last = scanner.nextLine();
					System.out.print("Employee homephone: ");
					String home = scanner.nextLine();
					System.out.print("Employee mobilephone: ");
					String mobile = scanner.nextLine();
					Employee employee = new Employee(email, first, last, home, mobile);
					client.sendEmployeeUpdate(employee);
				} break;
				case "UPDATE_APPOINTMENT": {
					System.out.print("Appointment ID: ");
					int ID = Integer.parseInt(scanner.nextLine());
					System.out.print("Appointment title: ");
					String title = scanner.nextLine();
					System.out.print("Appointment date: ");
					String date = scanner.nextLine();
					System.out.print("Appointment start time: ");
					String startTime = scanner.nextLine();
					System.out.print("Appointment end time: ");
					String endTime = scanner.nextLine();
					System.out.print("Appointment description: ");
					String description = scanner.nextLine();
					System.out.print("Appointment location: ");
					String location = scanner.nextLine();
					System.out.print("Appointment meeting leader: ");
					String leader = scanner.nextLine();
					System.out.print("Appointment meeting room: ");
					String room = scanner.nextLine();
					Appointment appointment = new Appointment(ID);
					appointment.setTitle(title);
					appointment.setDate(date);
					appointment.setStartTime(startTime);
					appointment.setEndTime(endTime);
					appointment.setDescription(description);
					appointment.setLocation(location);
					appointment.setMeetingLeader(leader);
					appointment.setMeetingRoom(room);
					client.sendAppointmentUpdate(appointment);
				} break;
				case "UPDATE_INVITATION": {
					System.out.print("Employee email: ");
					String email = scanner.nextLine();
					System.out.print("Appointment ID: ");
					int ID = Integer.parseInt(scanner.nextLine());
					System.out.print("Invitation answer: ");
					String answer = scanner.nextLine();
					System.out.print("Invitation message: ");
					String message = scanner.nextLine();
					Invitation invitation = new Invitation(email, ID);
					switch(answer) {
						case "ACCEPTED": {
							invitation.setAnswer(Invitation.Answer.ACCEPTED);
						} break;
						case "DECLINED": {
							invitation.setAnswer(Invitation.Answer.DECLINED);
						} break;
						default: {
							invitation.setAnswer(Invitation.Answer.PENDING);
						} break;
					}
					invitation.setMessage(message);
					client.sendInvitationUpdate(invitation);
				} break;
				case "UPDATE_ROOM": {
					System.out.print("Room name: ");
					String email = scanner.nextLine();
					System.out.print("Room size: ");
					int size = Integer.parseInt(scanner.nextLine());
					Room room = new Room(email, size);
					client.sendRoomUpdate(room);
				} break;
				case "UPDATE_ALARM": {
					System.out.print("Employee email: ");
					String email = scanner.nextLine();
					System.out.print("Appointment ID: ");
					int ID = Integer.parseInt(scanner.nextLine());
					System.out.print("Alarm time: ");
					String time = scanner.nextLine();
					System.out.print("Alarm description: ");
					String description = scanner.nextLine();
					Alarm alarm = new Alarm(ID, email);
					alarm.setTime(time);
					alarm.setDescription(description);
					client.sendAlarmUpdate(alarm);
				} break;
				case "SHOW_EMPLOYEE": {
					System.out.print("Employee email: ");
					String email = scanner.nextLine();
					Employee employee = ApplicationModel.getInstance().getEmployee(email);
					System.out.println(employee.toString());
				} break;
				case "SHOW_APPOINTMENT": {
					System.out.print("Appointment ID: ");
					int ID = Integer.parseInt(scanner.nextLine());
					Appointment appointment = ApplicationModel.getInstance().getAppointment(ID);
					System.out.println(appointment.toString());
				} break;
				case "SHOW_INVITATION": {
					System.out.print("Employee email: ");
					String email = scanner.nextLine();
					System.out.print("Appointment ID: ");
					int ID = Integer.parseInt(scanner.nextLine());
					Invitation invitation = ApplicationModel.getInstance().getInvitation(email,ID);
					System.out.println(invitation.toString());
				} break;
				case "SHOW_ROOM": {
					System.out.print("Room name: ");
					String name = scanner.nextLine();
					Room room = ApplicationModel.getInstance().getRoom(name);
					System.out.println(room.toString());
				} break;
				case "SHOW_ALARM": {
					System.out.print("Employee email: ");
					String email = scanner.nextLine();
					System.out.print("Appointment ID: ");
					int ID = Integer.parseInt(scanner.nextLine());
					Alarm alarm = ApplicationModel.getInstance().getAlarm(email, ID);
					System.out.println(alarm.toString());
				} break;
				case "SHOW_KEYS": {
					System.out.println(ApplicationModel.getInstance().listKeys());
				} break;
				case "BOOK_ROOM": {
					System.out.print("Room size: ");
					int minSize = Integer.parseInt(scanner.nextLine());
					System.out.print("Appointment ID: ");
					int ID = Integer.parseInt(scanner.nextLine());
					String roomID = "";
					try {
						roomID = RoomManager.pickSuitableRoom(minSize, ApplicationModel.getInstance().getAppointment(ID));
					}
					catch(RoomManager.NoAvailableRooms nar) {
						System.err.println(nar.getMessage());
					}
					System.out.println(roomID);
				} break;
				default: {
					System.err.println("UNKNOWN COMMAND");
				} break;
			}
		} while(!exit);
		client.disconnect();
	}
}
