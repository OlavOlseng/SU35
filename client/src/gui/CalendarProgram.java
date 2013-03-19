package gui;

import models.ApplicationModel;
import models.Employee;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalendarProgram {
	private static final String LOG_IN = "Login view";
	private static final String CALENDAR_VIEW = "Calendar View";
	private static final String EDIT_VIEW = "Edit View";
	private static final String INFO_VIEW = "Info View";
	
	private static ApplicationModel model;
	
	private static void createAndShowGui()
		{
		model = ApplicationModel.getInstance();
		model.username = "email";
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
