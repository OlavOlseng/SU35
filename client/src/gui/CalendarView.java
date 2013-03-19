package gui;
import models.Appointment;
import models.ApplicationModel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;


public class CalendarView extends JPanel{

//	static JFrame frame;
	private JPanel topLeftPanel, topRightPanel, bottomLeftPanel, bottomRightPanel, calendarPanel, _parentContentPane;
	private JButton btnNotifications, btnMe, btnCalendars, btnGoto, btnCreate, btnLogout, btnMore, btnEdit, btnPreviousWeek, btnNextWeek;
	private JTextArea textAreaInfo;
	private JLabel lblWeek, lblMonday, lblTuesday, lblWednesday, lblThursday, lblFriday, lblSaturday, lblSunday, lbl_00, lbl_06, lbl_12, lbl_18, lbl_24;
	private JPopupMenu menuNotifications, menuCalendars;
	private ArrayList<JButton> buttonList;
	private int indexOfSelectedButton;
	private EditView _editView;
	private InfoView _infoView;
	private int selectedWeek = Calendar.getInstance().WEEK_OF_YEAR;
	private int appointmentID;
	
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		
		
		frame = new JFrame("Superblaster");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(50, 50, 800, 600);
		frame.setContentPane(new CalendarView());
		frame.pack();
		frame.setVisible(true);
	}
*/
	/**
	 * Create the application.
	 */
	public CalendarView(JPanel parentContentPane) {
		initialize(parentContentPane);
		updateInfo();
	}
	
	public void updateInfo() {
		btnNotifications.setText("Notifications (" + Integer.toString(menuNotifications.getComponentCount()) + ")");
		
		//Finds the appointments for the logged in user
		buttonList = new ArrayList<JButton>();
		ArrayList<Appointment> appointmentsForUser = new ArrayList<Appointment>();
		appointmentsForUser = ApplicationModel.getInstance().getAppointmentsForUser(CalendarProgram.loggedInUser);
		System.out.println(appointmentsForUser);
		for (Appointment app : appointmentsForUser) {
			CustomCalendarButton button = new CustomCalendarButton();
			button.keyOfRelatedAppointment = app.getAppointmentID();
			buttonList.add(button);
			buttonList.get(buttonList.size()-1).setLayout(new BorderLayout());
			JLabel label1 = new JLabel(app.getDescription());
			JLabel label2 = new JLabel(app.getFormattedStartTime().toString());
			buttonList.get(buttonList.size()-1).add(BorderLayout.NORTH,label1);
			buttonList.get(buttonList.size()-1).add(BorderLayout.SOUTH,label2);
			buttonList.get(buttonList.size()-1).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					indexOfSelectedButton = buttonList.indexOf(e.getSource());
					CustomCalendarButton tempButton = (CustomCalendarButton) e.getSource();
					Appointment tempAppointment = ApplicationModel.getInstance().getAppointment(tempButton.keyOfRelatedAppointment);
					appointmentID = tempButton.keyOfRelatedAppointment;
					textAreaInfo.setText("Owner:\n" + tempAppointment.getMeetingLeader() + "\n\nDescription:\n" +
							tempAppointment.getDescription() + "\n\nStart:\n" + tempAppointment.getFormattedStartTime()
							+ "\n\nEnd:\n" + tempAppointment.getFormattedEndTime() + "\n\nWhere:\n" + tempAppointment.getLocation());
					btnMore.setEnabled(true);
					if (ApplicationModel.getInstance().username == tempAppointment.getMeetingLeader()) {
						btnEdit.setEnabled(true);
					}
				}
			});
			GridBagConstraints gbc_btnAppointment = new GridBagConstraints();
			gbc_btnAppointment.fill = GridBagConstraints.VERTICAL;
			gbc_btnAppointment.gridx = 0;
			gbc_btnAppointment.gridy = 2;
			gbc_btnAppointment.gridheight = 3;
//					((int) (Calendar.getModel().appointment.get(key).getEndTime().getTimeInMillis() -
//					Calendar.getModel().appointment.get(key).getStartTime().getTimeInMillis())) / 1000*60*60;
			calendarPanel.add(button, gbc_btnAppointment);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JPanel parentContentPane) {
		_parentContentPane 	= parentContentPane;
	
		this.setBorder(new EmptyBorder(5,5,5,5));
		this.setBackground(new Color(153, 190, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{120, 670, 0};
		gridBagLayout.rowHeights = new int[]{50, 540, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		this.setLayout(gridBagLayout);
		
		topLeftPanel = new JPanel();
		GridBagConstraints gbc_topLeftPanel = new GridBagConstraints();
		gbc_topLeftPanel.insets = new Insets(0, 0, 5, 5);
		gbc_topLeftPanel.fill = GridBagConstraints.BOTH;
		gbc_topLeftPanel.gridx = 0;
		gbc_topLeftPanel.gridy = 0;
		this.add(topLeftPanel, gbc_topLeftPanel);
		GridBagLayout gbl_topLeftPanel = new GridBagLayout();
		gbl_topLeftPanel.columnWidths = new int[]{0, 0};
		gbl_topLeftPanel.rowHeights = new int[]{0, 0};
		gbl_topLeftPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_topLeftPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		topLeftPanel.setLayout(gbl_topLeftPanel);
		
		btnNotifications = new JButton("Notifications");
		
		menuNotifications = new JPopupMenu();
		menuNotifications.add("Menuitem 1");
		menuNotifications.add("Menuitem 2");
		menuNotifications.add(new JMenuItem("Menuitem 3"));

		btnNotifications.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        menuNotifications.show(btnNotifications, btnNotifications.getBounds().x, btnNotifications.getBounds().y
		           + btnNotifications.getBounds().height);
		    }
		});
		
		GridBagConstraints gbc_btnNotifications = new GridBagConstraints();
		gbc_btnNotifications.fill = GridBagConstraints.BOTH;
		gbc_btnNotifications.gridx = 0;
		gbc_btnNotifications.gridy = 0;
		topLeftPanel.add(btnNotifications, gbc_btnNotifications);
		
		topRightPanel = new JPanel();
		topRightPanel.setBackground(new Color(153, 190, 255));
		GridBagConstraints gbc_topRightPanel = new GridBagConstraints();
		gbc_topRightPanel.insets = new Insets(0, 0, 5, 0);
		gbc_topRightPanel.fill = GridBagConstraints.BOTH;
		gbc_topRightPanel.gridx = 1;
		gbc_topRightPanel.gridy = 0;
		this.add(topRightPanel, gbc_topRightPanel);
		GridBagLayout gbl_topRightPanel = new GridBagLayout();
		gbl_topRightPanel.columnWidths = new int[]{170, 100, 100, 100, 100, 100, 0};
		gbl_topRightPanel.rowHeights = new int[]{0, 0};
		gbl_topRightPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_topRightPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		topRightPanel.setLayout(gbl_topRightPanel);
		
		btnMe = new JButton("Me");
		GridBagConstraints gbc_btnMe = new GridBagConstraints();
		gbc_btnMe.fill = GridBagConstraints.BOTH;
		gbc_btnMe.insets = new Insets(0, 0, 0, 5);
		gbc_btnMe.gridx = 1;
		gbc_btnMe.gridy = 0;
		topRightPanel.add(btnMe, gbc_btnMe);
		
		btnCalendars = new JButton("Calendars");
		menuCalendars = new JPopupMenu();
		menuCalendars.add("Menuitem 1");
		menuCalendars.add("Menuitem 2");
		//menuCalendars.add(new JMenuItem(Calendar.getModel().getEmployee("email").getEmail()));

		btnCalendars.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        menuCalendars.show(btnCalendars, btnCalendars.getBounds().x -270, btnCalendars.getBounds().y
		           + btnCalendars.getBounds().height);
		    }
		});
		
		GridBagConstraints gbc_btnCalendars = new GridBagConstraints();
		gbc_btnCalendars.fill = GridBagConstraints.BOTH;
		gbc_btnCalendars.insets = new Insets(0, 0, 0, 5);
		gbc_btnCalendars.gridx = 2;
		gbc_btnCalendars.gridy = 0;
		topRightPanel.add(btnCalendars, gbc_btnCalendars);
		
		btnGoto = new JButton("Go to");
		btnGoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GotoDialog gotoDialog = new GotoDialog((JFrame) btnGoto.getTopLevelAncestor(), true);
				gotoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				gotoDialog.setVisible(true);
				
				if (gotoDialog.getAnswer() == 1) {
					//System.out.println(gotoDialog.getWeek());
					//Update CalendarView with week from gotoDialog.getWeek()
				}
				else if (gotoDialog.getAnswer() == 2) {
					//System.out.println(gotoDialog.getDate());
					//Update CalendarView with week from gotoDialog.getWeek()
				}
			}
		});
		
		GridBagConstraints gbc_btnGoto = new GridBagConstraints();
		gbc_btnGoto.fill = GridBagConstraints.BOTH;
		gbc_btnGoto.insets = new Insets(0, 0, 0, 5);
		gbc_btnGoto.gridx = 3;
		gbc_btnGoto.gridy = 0;
		topRightPanel.add(btnGoto, gbc_btnGoto);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Open EditView
				//We call initialize with the parameter -1 to indicate that we
				// want to create a new appointment
				_editView.initialize(-1);
				CardLayout c1 = (CardLayout)(_parentContentPane.getLayout());
				c1.show(_parentContentPane, "Edit View");
				
			}
		});
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.fill = GridBagConstraints.BOTH;
		gbc_btnCreate.insets = new Insets(0, 0, 0, 5);
		gbc_btnCreate.gridx = 4;
		gbc_btnCreate.gridy = 0;
		topRightPanel.add(btnCreate, gbc_btnCreate);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int n = JOptionPane.showConfirmDialog(
					    JOptionPane.getFrameForComponent(getRootPane()),
					    "Are you sure you wish to logout?",
					    "Logout",
					    JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					JOptionPane.getFrameForComponent(btnLogout).dispose();
				}
			}
		});
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.fill = GridBagConstraints.BOTH;
		gbc_btnLogout.gridx = 5;
		gbc_btnLogout.gridy = 0;
		topRightPanel.add(btnLogout, gbc_btnLogout);
		
		bottomLeftPanel = new JPanel();
		bottomLeftPanel.setBackground(new Color(153, 190, 255));
		GridBagConstraints gbc_bottomLeftPanel = new GridBagConstraints();
		gbc_bottomLeftPanel.insets = new Insets(0, 0, 5, 5);
		gbc_bottomLeftPanel.fill = GridBagConstraints.BOTH;
		gbc_bottomLeftPanel.gridx = 0;
		gbc_bottomLeftPanel.gridy = 1;
		this.add(bottomLeftPanel, gbc_bottomLeftPanel);
		GridBagLayout gbl_bottomLeftPanel = new GridBagLayout();
		gbl_bottomLeftPanel.columnWidths = new int[]{0, 0};
		gbl_bottomLeftPanel.rowHeights = new int[]{470, 0, 0, 0};
		gbl_bottomLeftPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_bottomLeftPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		bottomLeftPanel.setLayout(gbl_bottomLeftPanel);
		
		textAreaInfo = new JTextArea();
		textAreaInfo.setEditable(false);
		textAreaInfo.setRows(19);
		//textAreaInfo.setText("Info:");
		GridBagConstraints gbc_textAreaInfo = new GridBagConstraints();
		gbc_textAreaInfo.anchor = GridBagConstraints.NORTH;
		gbc_textAreaInfo.insets = new Insets(0, 0, 5, 0);
		gbc_textAreaInfo.fill = GridBagConstraints.BOTH;
		gbc_textAreaInfo.gridx = 0;
		gbc_textAreaInfo.gridy = 0;
		bottomLeftPanel.add(textAreaInfo, gbc_textAreaInfo);
		
		btnMore = new JButton("More");
		btnMore.setEnabled(false);
		btnMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Open InfoView
				System.out.println(appointmentID);
				_infoView.initialize(appointmentID);
				CardLayout c1 = (CardLayout)(_parentContentPane.getLayout());
				c1.show(_parentContentPane, "Info View");
			}
		});
		GridBagConstraints gbc_btnMore = new GridBagConstraints();
		gbc_btnMore.anchor = GridBagConstraints.SOUTH;
		gbc_btnMore.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMore.insets = new Insets(0, 0, 5, 0);
		gbc_btnMore.gridx = 0;
		gbc_btnMore.gridy = 1;
		bottomLeftPanel.add(btnMore, gbc_btnMore);
		
		btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Open EditView
				//We will call intialize with the appointmentID as a parameter.
				//The number 1 is for testing purposes
				_editView.initialize(1);
				CardLayout c1 = (CardLayout)(_parentContentPane.getLayout());
				c1.show(_parentContentPane, "Edit View");
			}
		});
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.anchor = GridBagConstraints.SOUTH;
		gbc_btnEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEdit.gridx = 0;
		gbc_btnEdit.gridy = 2;
		bottomLeftPanel.add(btnEdit, gbc_btnEdit);
		
		bottomRightPanel = new JPanel();
		bottomRightPanel.setBackground(new Color(153, 190, 255));
		GridBagConstraints gbc_bottomRightPanel = new GridBagConstraints();
		gbc_bottomRightPanel.insets = new Insets(0, 0, 5, 0);
		gbc_bottomRightPanel.fill = GridBagConstraints.BOTH;
		gbc_bottomRightPanel.gridx = 1;
		gbc_bottomRightPanel.gridy = 1;
		this.add(bottomRightPanel, gbc_bottomRightPanel);
		GridBagLayout gbl_bottomRightPanel = new GridBagLayout();
		gbl_bottomRightPanel.columnWidths = new int[]{80, 80, 80, 80, 80, 80, 80, 80, 0};
		gbl_bottomRightPanel.rowHeights = new int[]{30, 50, 80, 80, 80, 80, 80, 0};
		gbl_bottomRightPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_bottomRightPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		bottomRightPanel.setLayout(gbl_bottomRightPanel);
		
		btnPreviousWeek = new JButton("Previous week");
		btnPreviousWeek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Load previous week in calendar
			}
		});
		GridBagConstraints gbc_btnPreviousWeek = new GridBagConstraints();
		gbc_btnPreviousWeek.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPreviousWeek.gridwidth = 2;
		gbc_btnPreviousWeek.insets = new Insets(0, 0, 5, 5);
		gbc_btnPreviousWeek.gridx = 0;
		gbc_btnPreviousWeek.gridy = 0;
		bottomRightPanel.add(btnPreviousWeek, gbc_btnPreviousWeek);
		
		lblWeek = new JLabel("Week: XX");
		GridBagConstraints gbc_lblWeek = new GridBagConstraints();
		gbc_lblWeek.gridwidth = 4;
		gbc_lblWeek.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeek.gridx = 2;
		gbc_lblWeek.gridy = 0;
		bottomRightPanel.add(lblWeek, gbc_lblWeek);
		
		btnNextWeek = new JButton("Next week");
		btnNextWeek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Load next week in calendar
			}
		});
		GridBagConstraints gbc_btnNextWeek = new GridBagConstraints();
		gbc_btnNextWeek.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNextWeek.gridwidth = 2;
		gbc_btnNextWeek.insets = new Insets(0, 0, 5, 5);
		gbc_btnNextWeek.gridx = 6;
		gbc_btnNextWeek.gridy = 0;
		//frame.getContentPane().
		bottomRightPanel.add(btnNextWeek, gbc_btnNextWeek);
		
		lblMonday = new JLabel("Monday");
		GridBagConstraints gbc_lblMonday = new GridBagConstraints();
		gbc_lblMonday.insets = new Insets(0, 0, 5, 5);
		gbc_lblMonday.gridx = 1;
		gbc_lblMonday.gridy = 1;
		bottomRightPanel.add(lblMonday, gbc_lblMonday);
		
		lblTuesday = new JLabel("Tuesday");
		GridBagConstraints gbc_lblTuesday = new GridBagConstraints();
		gbc_lblTuesday.insets = new Insets(0, 0, 5, 5);
		gbc_lblTuesday.gridx = 2;
		gbc_lblTuesday.gridy = 1;
		bottomRightPanel.add(lblTuesday, gbc_lblTuesday);
		
		lblWednesday = new JLabel("Wednesday");
		GridBagConstraints gbc_lblWednesday = new GridBagConstraints();
		gbc_lblWednesday.insets = new Insets(0, 0, 5, 5);
		gbc_lblWednesday.gridx = 3;
		gbc_lblWednesday.gridy = 1;
		bottomRightPanel.add(lblWednesday, gbc_lblWednesday);
		
		lblThursday = new JLabel("Thursday");
		GridBagConstraints gbc_lblThursday = new GridBagConstraints();
		gbc_lblThursday.insets = new Insets(0, 0, 5, 5);
		gbc_lblThursday.gridx = 4;
		gbc_lblThursday.gridy = 1;
		bottomRightPanel.add(lblThursday, gbc_lblThursday);
		
		lblFriday = new JLabel("Friday");
		GridBagConstraints gbc_lblFriday = new GridBagConstraints();
		gbc_lblFriday.insets = new Insets(0, 0, 5, 5);
		gbc_lblFriday.gridx = 5;
		gbc_lblFriday.gridy = 1;
		bottomRightPanel.add(lblFriday, gbc_lblFriday);
		
		lblSaturday = new JLabel("Saturday");
		GridBagConstraints gbc_lblSaturday = new GridBagConstraints();
		gbc_lblSaturday.insets = new Insets(0, 0, 5, 5);
		gbc_lblSaturday.gridx = 6;
		gbc_lblSaturday.gridy = 1;
		bottomRightPanel.add(lblSaturday, gbc_lblSaturday);
		
		lblSunday = new JLabel("Sunday");
		GridBagConstraints gbc_lblSunday = new GridBagConstraints();
		gbc_lblSunday.insets = new Insets(0, 0, 5, 5);
		gbc_lblSunday.gridx = 7;
		gbc_lblSunday.gridy = 1;
		bottomRightPanel.add(lblSunday, gbc_lblSunday);
		
		lbl_00 = new JLabel("00:00");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 2;
		bottomRightPanel.add(lbl_00, gbc_label_1);
		
		calendarPanel = new JPanel();
		calendarPanel.setBackground(new Color(153, 190, 255));
		GridBagConstraints gbc_calendarPanel = new GridBagConstraints();
		gbc_calendarPanel.gridwidth = 7;
		gbc_calendarPanel.gridheight = 5;
		gbc_calendarPanel.fill = GridBagConstraints.BOTH;
		gbc_calendarPanel.gridx = 1;
		gbc_calendarPanel.gridy = 2;
		bottomRightPanel.add(calendarPanel, gbc_calendarPanel);
		
		GridBagLayout gbl_calendarPanel = new GridBagLayout();
		gbl_calendarPanel.columnWidths = new int[]{80, 80, 80, 80, 80, 80, 80};
		gbl_calendarPanel.rowHeights = new int[]{17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17};
		//gbl_calendarPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		//gbl_calendarPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		calendarPanel.setLayout(gbl_calendarPanel);
		
		lbl_06 = new JLabel("06:00");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 3;
		bottomRightPanel.add(lbl_06, gbc_label_2);
		
		lbl_12 = new JLabel("12:00");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 4;
		bottomRightPanel.add(lbl_12, gbc_label_3);
		
		lbl_18 = new JLabel("18:00");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 5;
		bottomRightPanel.add(lbl_18, gbc_label_4);
		
		lbl_24 = new JLabel("23:59");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.insets = new Insets(0, 0, 0, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 6;
		bottomRightPanel.add(lbl_24, gbc_label_5);
		
	}
	//--------------------------------------------------------------------------
	public void setEditView(EditView editView)
		{ _editView = editView; }
	
	public void setInfoView(InfoView infoView)
	{ _infoView = infoView; }
}