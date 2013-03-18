package gui;

import models.ApplicationModel;
import models.Employee;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Calendar {
	private static final String LOG_IN = "Login view";
	private static final String CALENDAR_VIEW = "Calendar View";
	private static final String EDIT_VIEW = "Edit View";
	private static final String INFO_VIEW = "Info View";
	
	private static ApplicationModel model;
	public static Employee loggedInUser;
	
	
	private static void createAndShowGui()
		{
		model = new ApplicationModel();
		loggedInUser = model.getEmployee().get("email");
		JFrame frame = new JFrame("Superblaster");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(50, 50, 800, 600);
		
		final JPanel contentPane = new JPanel();
		contentPane.setLayout(new CardLayout());
						
		Login login = new Login(contentPane);
		contentPane.add(login, LOG_IN);
		
		EditView editView = new EditView(contentPane);
		contentPane.add(editView, EDIT_VIEW);
		
		CalendarView calendarView = new CalendarView(contentPane, editView);
		contentPane.add(calendarView, CALENDAR_VIEW);
		
		
		
		InfoView infoView = new InfoView(contentPane);
		contentPane.add(infoView, INFO_VIEW);
		
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


	public static ApplicationModel getModel() {
		return model;
	}


	public static void setModel(ApplicationModel model) {
		Calendar.model = model;
	}
}
