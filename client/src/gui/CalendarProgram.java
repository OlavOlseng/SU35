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
	
	//private static ApplicationModel model;
	public static String loggedInUser;
	
	private static void createAndShowGui()
		{
		//model = ApplicationModel.getInstance();
		//Testdata for employees
		loggedInUser = "kenneth@ntnu.no";
		
		ApplicationModel.getInstance().addEmployee("fredrik@ntnu.no", new Employee("fredrik@ntnu.no",
				"Fredrik", "Haave", "54576859", "54367867"));
		ApplicationModel.getInstance().addEmployee("sindre@ntnu.no", new Employee("sindre@ntnu.no",
				"Sindre", "Magnussen", "54476834", "14389867"));
		ApplicationModel.getInstance().addEmployee("august@ntnu.no", new Employee("august@ntnu.no",
				"August", "Kvernmo", "54576834", "54389867"));
		ApplicationModel.getInstance().addEmployee("olav@ntnu.no", new Employee("olav@ntnu.no",
				"Olav", "Olseng", "54506834", "14389869"));
		ApplicationModel.getInstance().addEmployee("yngve@ntnu.no", new Employee("yngve@ntnu.no",
				"Yngve", "Bloch Hoel", "54576834", "54389867"));
		ApplicationModel.getInstance().addEmployee("kenneth@ntnu.no", new Employee("kenneth@ntnu.no",
				"Kenneth", "Lund", "54576834", "54389867"));
		
		//Testdata for rooms
		ApplicationModel.getInstance().addRoom("Room A1", new Room("Room A1", 225));
		ApplicationModel.getInstance().addRoom("Room C4", new Room("Room C4", 75));
		ApplicationModel.getInstance().addRoom("Room K1", new Room("Room K1", 15));
		ApplicationModel.getInstance().addRoom("Room K2", new Room("Room K1", 28));
		
		//Testdata for appointment 1
		Appointment appointment1 = new Appointment(0);

		appointment1.setTitle("Moete");
		appointment1.setDate("2013-03-22");
		appointment1.setStartTime("14:00");
		appointment1.setEndTime("15:00");
		appointment1.setDescription("Et moete der vi tar opp aktuelle " +
				"spoersmaal angaaende fellesprosjektet.");
		appointment1.setLocation("Room K1");
		appointment1.setMeetingRoom("Room K1");
		appointment1.setMeetingLeader("Kenneth Lund");
		
		ApplicationModel.getInstance().addAppointment(appointment1.getAppointmentID(), appointment1);
		
		//Testdata for appointment2
		Appointment appointment2 = new Appointment(1);

		appointment2.setTitle("Moete");
		appointment2.setDate("2013-04-12");
		appointment2.setStartTime("16:00");
		appointment2.setEndTime("18:00");
		appointment2.setDescription("Et moete der vi tar opp aktuelle " +
				"spoersmaal angaaende ulike problemstillinger.");
		appointment2.setLocation("Room K2");
		appointment2.setMeetingRoom("Room K2");
		appointment2.setMeetingLeader("Sindre Magnussen");
		
		ApplicationModel.getInstance().addAppointment(appointment2.getAppointmentID(), appointment2);
		
		//Participants in appointment1
		ApplicationModel.getInstance().addInvitation("kenneth@ntnu.no", appointment1.getAppointmentID(),
				new Invitation("kenneth@ntnu.no", 
						appointment1.getAppointmentID()));		
		ApplicationModel.getInstance().addInvitation("olav@ntnu.no", appointment1.getAppointmentID(),
				new Invitation("olav@ntnu.no",
						appointment1.getAppointmentID()));		
		ApplicationModel.getInstance().addInvitation("yngve@ntnu.no", appointment1.getAppointmentID(),
				new Invitation("yngve@ntnu.no",
						appointment1.getAppointmentID()));		
		
		//Participants for appointment2
		ApplicationModel.getInstance().addInvitation("sindre@ntnu.no", appointment2.getAppointmentID(),
				new Invitation("sindre@ntnu.no",
						appointment2.getAppointmentID()));		
		ApplicationModel.getInstance().addInvitation("august@ntnu.no", appointment2.getAppointmentID(),
				new Invitation("august@ntnu.no",
						appointment2.getAppointmentID()));		
		ApplicationModel.getInstance().addInvitation("fredrik@ntnu.no", appointment2.getAppointmentID(),
				new Invitation("fredrik@ntnu.no",
						appointment2.getAppointmentID()));		
		ApplicationModel.getInstance().addInvitation("yngve@ntnu.no", appointment2.getAppointmentID(),
				new Invitation("yngve@ntnu.no", 
						appointment2.getAppointmentID()));
		
		
		
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
		calendarView.setInfoView(infoView);
		
		frame.add(contentPane);
		frame.pack();
		frame.setVisible(true);
		}
	
	
	public static void main(String args[])
		{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
			{
			public void run()
				{ createAndShowGui(); }
				
			});
		}


//	public static ApplicationModel getModel() {
//		return ApplicationModel.getInstance();
//	}
//
//
//	public static void setModel(ApplicationModel model) {
//		ApplicationModel.getInstance() = model;
//	}
}
