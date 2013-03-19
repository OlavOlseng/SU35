package gui;

import models.ApplicationModel;
import models.Appointment;
import models.Employee;
import models.Invitation;
import models.Room;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalendarProgram {
	private static final String LOG_IN = "Login view";
	private static final String CALENDAR_VIEW = "Calendar View";
	private static final String EDIT_VIEW = "Edit View";
	private static final String INFO_VIEW = "Info View";
	
	private static ApplicationModel model;
	public static String loggedInUser;
	
	private static void createAndShowGui()
		{
		model = ApplicationModel.getInstance();
		loggedInUser = "email";
		JFrame frame = new JFrame("Superblaster");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(50, 50, 800, 600);
		
		final JPanel contentPane = new JPanel();
		contentPane.setLayout(new CardLayout());
						
		Login login = new Login(contentPane);
		contentPane.add(login, LOG_IN);
		
		EditView editView = new EditView(contentPane);
		contentPane.add(editView, EDIT_VIEW);
		
		CalendarView calendarView = new CalendarView(contentPane);
		contentPane.add(calendarView, CALENDAR_VIEW);
		
		InfoView infoView = new InfoView(contentPane);
		contentPane.add(infoView, INFO_VIEW);
		
		calendarView.setEditView(editView);
		
		frame.add(contentPane);
		frame.pack();
		frame.setVisible(true);
		
		
		//Testdata for employees
		model.addEmployee("fredrik@ntnu.no", new Employee("fredrik@ntnu.no",
				"Fredrik", "Haave", "54576859", "54367867"));
		model.addEmployee("sindre@ntnu.no", new Employee("sindre@ntnu.no",
				"Sindre", "Magnussen", "54476834", "14389867"));
		model.addEmployee("august@ntnu.no", new Employee("august@ntnu.no",
				"August", "Kvernmo", "54576834", "54389867"));
		model.addEmployee("olav@ntnu.no", new Employee("olav@ntnu.no",
				"Olav", "Olseng", "54506834", "14389869"));
		model.addEmployee("yngve@ntnu.no", new Employee("yngve@ntnu.no",
				"Yngve", "Bloch Hoel", "54576834", "54389867"));
		model.addEmployee("kenneth@ntnu.no", new Employee("kenneth@ntnu.no",
				"Kenneth", "Lund", "54576834", "54389867"));
		
		//Testdata for rooms
		model.addRoom("Room A1", new Room("Room A1", 225));
		model.addRoom("Room C4", new Room("Room C4", 75));
		model.addRoom("Room K1", new Room("Room K1", 15));
		model.addRoom("Room K2", new Room("Room K1", 28));
		
		//Testdata for appointment 1
		Appointment appointment1 = new Appointment(0);
		appointment1.setDate("22-03-13");
		appointment1.setStartTime("14:00:00");
		appointment1.setEndTime("15:00:00");
		appointment1.setDescription("Et moete der vi tar opp aktuelle " +
				"spoersmaal angaaende fellesprosjektet.");
		appointment1.setLocation("Room K1");
		appointment1.setMeetingRoom("Room K1");
		appointment1.setMeetingLeader("Kenneth Lund");
		
		model.addAppointment(appointment1.getAppointmentID(), appointment1);
		
		//Testdata for appointment2
		Appointment appointment2 = new Appointment(1);
		appointment1.setDate("12-04-13");
		appointment1.setStartTime("16:00:00");
		appointment1.setEndTime("18:00:00");
		appointment1.setDescription("Et moete der vi tar opp aktuelle " +
				"spoersmaal angaaende ulike problemstillinger.");
		appointment1.setLocation("Room K2");
		appointment1.setMeetingRoom("Room K2");
		appointment1.setMeetingLeader("Sindre Magnussen");
		
		model.addAppointment(appointment2.getAppointmentID(), appointment2);
		
		//Participants in appointment1
		model.addInvitation("kenneth@ntnu.no", appointment1.getAppointmentID(),
				new Invitation("kenneth@ntnu.no", 
						appointment1.getAppointmentID()));		
		model.addInvitation("olav@ntnu.no", appointment1.getAppointmentID(),
				new Invitation("olav@ntnu.no",
						appointment1.getAppointmentID()));		
		model.addInvitation("yngve@ntnu.no", appointment1.getAppointmentID(),
				new Invitation("yngve@ntnu.no",
						appointment1.getAppointmentID()));		
		
		//Participants for appointment2
		model.addInvitation("sindre@ntnu.no", appointment2.getAppointmentID(),
				new Invitation("sindre@ntnu.no",
						appointment2.getAppointmentID()));		
		model.addInvitation("august@ntnu.no", appointment2.getAppointmentID(),
				new Invitation("august@ntnu.no",
						appointment2.getAppointmentID()));		
		model.addInvitation("fredrik@ntnu.no", appointment2.getAppointmentID(),
				new Invitation("fredrik@ntnu.no",
						appointment2.getAppointmentID()));		
		model.addInvitation("yngve@ntnu.no", appointment2.getAppointmentID(),
				new Invitation("yngve@ntnu.no", 
						appointment2.getAppointmentID()));		
		}
	
	
	public static void main(String args[])
		{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
			{
			public void run()
				{ createAndShowGui(); }
				
			});
		}


	public static ApplicationModel getModel() {
		return model;
	}


	public static void setModel(ApplicationModel model) {
		CalendarProgram.model = model;
	}
}
