package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ScrollPaneConstants;

import models.Alarm;
import models.ApplicationModel;
import models.Appointment;
import models.Invitation;
import models.Invitation.Answer;
import models.ModelListener;


public class InfoView extends JPanel implements ModelListener
	{
	private int 					_appointmentId;
	private JButton 				editButton;
	private JButton 				calendarButton;
	private JButton 				saveButton;
	private JList 					_attendingList;
	private JList 					_declinedList;
	private JList 					_notAnsweredList;
	private JPanel 				_parentContentPane;
	private final JLabel 		startLabel = new JLabel("Start:");
	private JLabel 				endLabel;
	private JLabel 				ownerLabel;
	private JLabel 				dateLabel;
	private JLabel 				titleLabel;
	private JLabel 				attendingLabel;
	private JLabel 				declinedLabel;
	private JLabel 				notAnsweredLabel;
	private JLabel 				alarmLabel;
	private JLabel 				locationLabel;
	private JLabel 				descriptionLabel;
	private JTextField 			ownerField;
	private JTextField 			titleField;
	private JTextField 			startField;
	private JTextField 			endField;
	private JTextField 			locationField;
	private JTextField 			alarmField;
	private JTextField 			dateField;
	private JTextArea 			descriptionTextArea;
	private EditView     		_editView;
	private DefaultListModel 	_attendingListModel;
	private DefaultListModel	_declinedListModel;
	private DefaultListModel	_notAnsweredListModel;
	private JRadioButton attendRadioButton;
	private JRadioButton declineRadioButton;
	private ButtonGroup buttonGroup;
	private ArrayList<Invitation> invitationList = new ArrayList<Invitation>();

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		JFrame frame = new JFrame("Superblaster");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(50, 50, 800, 600);
		frame.setContentPane(new InfoView());
		frame.pack();
		frame.setVisible(true);

//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					InfoView frame = new InfoView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}
*/
	/**
	 * Create the frame.
	 */
	public InfoView(JPanel parentContentPane) {
		_parentContentPane = parentContentPane;
		ApplicationModel.getInstance().addModelListener(this);
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 800, 600);
		//contentPane = new JPanel();
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setBackground(new Color(153, 190, 255));
		//setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		//gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		//gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0};
		gbl_contentPane.columnWidths = new int[]{50, 117, 117, 116, 116, 117, 117, 50};
		gbl_contentPane.rowHeights = new int[]{75, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 75};
		//gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		//gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gbl_contentPane);
		_attendingListModel 		= new DefaultListModel();
		_declinedListModel  	 	= new DefaultListModel();
		_notAnsweredListModel 	= new DefaultListModel();
		
		calendarButton = new JButton("Calendar");
		calendarButton.addActionListener(new CalendarListener());
			
		GridBagConstraints gbc_backButton = new GridBagConstraints();
		gbc_backButton.fill = GridBagConstraints.BOTH;
		gbc_backButton.insets = new Insets(0, 0, 5, 5);
		gbc_backButton.gridx = 1;
		gbc_backButton.gridy = 1;
		this.add(calendarButton, gbc_backButton);
		
		editButton = new JButton("Edit");
		editButton.addActionListener(new EditListener());

		
		saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveListener());
			
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.fill = GridBagConstraints.BOTH;
		gbc_saveButton.insets = new Insets(0, 0, 5, 5);
		gbc_saveButton.gridx = 5;
		gbc_saveButton.gridy = 1;
		add(saveButton, gbc_saveButton);
		GridBagConstraints gbc_editButton = new GridBagConstraints();
		gbc_editButton.fill = GridBagConstraints.BOTH;
		gbc_editButton.insets = new Insets(0, 0, 5, 5);
		gbc_editButton.gridx = 6;
		gbc_editButton.gridy = 1;
		this.add(editButton, gbc_editButton);
		
		ownerLabel = new JLabel("Owner:");
		GridBagConstraints gbc_ownerLabel = new GridBagConstraints();
		gbc_ownerLabel.anchor = GridBagConstraints.EAST;
		gbc_ownerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ownerLabel.gridx = 1;
		gbc_ownerLabel.gridy = 3;
		this.add(ownerLabel, gbc_ownerLabel);
		
		ownerField = new JTextField();
		GridBagConstraints gbc_ownerField = new GridBagConstraints();
		gbc_ownerField.insets = new Insets(0, 0, 5, 5);
		gbc_ownerField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ownerField.gridx = 2;
		gbc_ownerField.gridy = 3;
		this.add(ownerField, gbc_ownerField);
		//ownerField.setColumns(15);
		ownerField.setEditable(false);
		
		dateLabel = new JLabel("Date:");
		GridBagConstraints gbc_dateLabel = new GridBagConstraints();
		gbc_dateLabel.anchor = GridBagConstraints.EAST;
		gbc_dateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dateLabel.gridx = 3;
		gbc_dateLabel.gridy = 3;
		this.add(dateLabel, gbc_dateLabel);
		
		dateField = new JTextField();
		GridBagConstraints gbc_dateField = new GridBagConstraints();
		gbc_dateField.insets = new Insets(0, 0, 5, 5);
		gbc_dateField.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateField.gridx = 4;
		gbc_dateField.gridy = 3;
		this.add(dateField, gbc_dateField);
		//dateField.setColumns(20);
		dateField.setEditable(false);
		
		GridBagConstraints gbc_startLabel = new GridBagConstraints();
		gbc_startLabel.anchor = GridBagConstraints.EAST;
		gbc_startLabel.insets = new Insets(0, 0, 5, 5);
		gbc_startLabel.gridx = 5;
		gbc_startLabel.gridy = 3;
		this.add(startLabel, gbc_startLabel);
		
		startField = new JTextField();
		GridBagConstraints gbc_startField = new GridBagConstraints();
		gbc_startField.insets = new Insets(0, 0, 5, 5);
		gbc_startField.fill = GridBagConstraints.HORIZONTAL;
		gbc_startField.gridx = 6;
		gbc_startField.gridy = 3;
		this.add(startField, gbc_startField);
		//startField.setColumns(20);
		startField.setEditable(false);
		
		titleLabel = new JLabel("Title:");
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.anchor = GridBagConstraints.EAST;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 1;
		gbc_titleLabel.gridy = 4;
		this.add(titleLabel, gbc_titleLabel);
		
		titleField = new JTextField();
		GridBagConstraints gbc_titleField = new GridBagConstraints();
		gbc_titleField.insets = new Insets(0, 0, 5, 5);
		gbc_titleField.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleField.gridx = 2;
		gbc_titleField.gridy = 4;
		this.add(titleField, gbc_titleField);
		//titleField.setColumns(20);
		titleField.setEditable(false);
		
		endLabel = new JLabel("End:");
		GridBagConstraints gbc_endLabel = new GridBagConstraints();
		gbc_endLabel.anchor = GridBagConstraints.EAST;
		gbc_endLabel.insets = new Insets(0, 0, 5, 5);
		gbc_endLabel.gridx = 5;
		gbc_endLabel.gridy = 4;
		this.add(endLabel, gbc_endLabel);
		
		endField = new JTextField();
		GridBagConstraints gbc_endField = new GridBagConstraints();
		gbc_endField.insets = new Insets(0, 0, 5, 5);
		gbc_endField.fill = GridBagConstraints.HORIZONTAL;
		gbc_endField.gridx = 6;
		gbc_endField.gridy = 4;
		this.add(endField, gbc_endField);
		//endField.setColumns(20);
		endField.setEditable(false);
		
		attendingLabel = new JLabel("Attending");
		GridBagConstraints gbc_attendingLabel = new GridBagConstraints();
		gbc_attendingLabel.insets = new Insets(0, 0, 5, 5);
		gbc_attendingLabel.gridx = 1;
		gbc_attendingLabel.gridy = 5;
		this.add(attendingLabel, gbc_attendingLabel);
		
		declinedLabel = new JLabel("Declined");
		GridBagConstraints gbc_declinedLabel = new GridBagConstraints();
		gbc_declinedLabel.insets = new Insets(0, 0, 5, 5);
		gbc_declinedLabel.gridx = 2;
		gbc_declinedLabel.gridy = 5;
		this.add(declinedLabel, gbc_declinedLabel);
		
		notAnsweredLabel = new JLabel("Not answered");
		GridBagConstraints gbc_notAnsweredLabel = new GridBagConstraints();
		gbc_notAnsweredLabel.insets = new Insets(0, 0, 5, 5);
		gbc_notAnsweredLabel.gridx = 3;
		gbc_notAnsweredLabel.gridy = 5;
		this.add(notAnsweredLabel, gbc_notAnsweredLabel);
		
		descriptionLabel = new JLabel("Description");
		GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
		gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionLabel.gridx = 5;
		gbc_descriptionLabel.gridy = 5;
		this.add(descriptionLabel, gbc_descriptionLabel);
		
		_attendingList = new JList(_attendingListModel);
		_attendingList.setVisibleRowCount(5);
		_attendingList.setFixedCellWidth(117);
		//attendingList.setMaximumSize(new Dimension(117, 45));
		//attendingList.setFixedCellHeight(1);
		
		JScrollPane attendingScrollPane = new JScrollPane(_attendingList);
		GridBagConstraints gbc_attendingScrollPane = new GridBagConstraints();
		gbc_attendingScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_attendingScrollPane.fill = GridBagConstraints.BOTH;
		gbc_attendingScrollPane.gridx = 1;
		gbc_attendingScrollPane.gridy = 6;
		gbc_attendingScrollPane.gridheight = 3;
		this.add(attendingScrollPane, gbc_attendingScrollPane);
		
		_declinedList = new JList(_declinedListModel);
		_declinedList.setVisibleRowCount(5);
		_declinedList.setFixedCellWidth(117);
		//declinedList.setFixedCellHeight(1);
		
		JScrollPane declinedScrollPane = new JScrollPane(_declinedList);
		GridBagConstraints gbc_declinedScrollPane = new GridBagConstraints();
		gbc_declinedScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_declinedScrollPane.fill = GridBagConstraints.BOTH;
		gbc_declinedScrollPane.gridx = 2;
		gbc_declinedScrollPane.gridy = 6;
		gbc_declinedScrollPane.gridheight = 3;
		this.add(declinedScrollPane, gbc_declinedScrollPane);
		
		_notAnsweredList = new JList(_notAnsweredListModel);
		_notAnsweredList.setVisibleRowCount(5);
		_notAnsweredList.setFixedCellWidth(117);
		//notAnsweredList.setFixedCellHeight(8);
		
		JScrollPane notAnsweredScrollPane = new JScrollPane(_notAnsweredList);
		GridBagConstraints gbc_notAnsweredScrollPane = new GridBagConstraints();
		gbc_notAnsweredScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_notAnsweredScrollPane.fill = GridBagConstraints.BOTH;
		gbc_notAnsweredScrollPane.gridx = 3;
		gbc_notAnsweredScrollPane.gridy = 6;
		gbc_notAnsweredScrollPane.gridheight = 3;
		this.add(notAnsweredScrollPane, gbc_notAnsweredScrollPane);
		
		descriptionTextArea = new JTextArea();
		descriptionTextArea.setEditable(false);
		descriptionTextArea.setLineWrap(true);
		
		JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_descriptionScrollPane = new GridBagConstraints();
		gbc_descriptionScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionScrollPane.fill = GridBagConstraints.BOTH;
		gbc_descriptionScrollPane.gridx = 5;
		gbc_descriptionScrollPane.gridy = 6;
		gbc_descriptionScrollPane.gridheight = 3;
		gbc_descriptionScrollPane.gridwidth = 2;
		this.add(descriptionScrollPane, gbc_descriptionScrollPane);
					
		locationLabel = new JLabel("Location:");
		GridBagConstraints gbc_locationLabel = new GridBagConstraints();
		gbc_locationLabel.anchor = GridBagConstraints.EAST;
		gbc_locationLabel.insets = new Insets(0, 0, 5, 5);
		gbc_locationLabel.gridx = 1;
		gbc_locationLabel.gridy = 9;
		this.add(locationLabel, gbc_locationLabel);
		
		locationField = new JTextField();
		GridBagConstraints gbc_locationField = new GridBagConstraints();
		gbc_locationField.insets = new Insets(0, 0, 5, 5);
		gbc_locationField.fill = GridBagConstraints.HORIZONTAL;
		gbc_locationField.gridx = 2;
		gbc_locationField.gridy = 9;
		this.add(locationField, gbc_locationField);
		//locationField.setColumns(20);
		locationField.setEditable(false);
		
		attendRadioButton = new JRadioButton("Attend");
		attendRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Invitation invitation : invitationList) {
					if (invitation.getEmployeeEmail().equals(ApplicationModel.getInstance().username)) {
						invitation.setAnswer(Answer.ACCEPTED);
						ApplicationModel.getInstance().createInvitation(invitation);
						//buttonGroup.setSelected(attendRadioButton.getModel(), true);
					}
				}
			}
		});
		GridBagConstraints gbc_attendRadioButton = new GridBagConstraints();
		gbc_attendRadioButton.anchor = GridBagConstraints.WEST;
		gbc_attendRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_attendRadioButton.gridx = 6;
		gbc_attendRadioButton.gridy = 9;
		this.add(attendRadioButton, gbc_attendRadioButton);
		
		alarmLabel = new JLabel("Alarm:");
		GridBagConstraints gbc_alarmLabel = new GridBagConstraints();
		gbc_alarmLabel.anchor = GridBagConstraints.EAST;
		gbc_alarmLabel.insets = new Insets(0, 0, 5, 5);
		gbc_alarmLabel.gridx = 1;
		gbc_alarmLabel.gridy = 10;
		this.add(alarmLabel, gbc_alarmLabel);
		
		alarmField = new JTextField();
		GridBagConstraints gbc_alarmField = new GridBagConstraints();
		gbc_alarmField.insets = new Insets(0, 0, 5, 5);
		gbc_alarmField.fill = GridBagConstraints.HORIZONTAL;
		gbc_alarmField.gridx = 2;
		gbc_alarmField.gridy = 10;
		this.add(alarmField, gbc_alarmField);
		//alarmField.setColumns(10);
		
		declineRadioButton = new JRadioButton("Decline");
		declineRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Invitation invitation : invitationList) {
					if (invitation.getEmployeeEmail().equals(ApplicationModel.getInstance().username)) {
						invitation.setAnswer(Answer.DECLINED);
						ApplicationModel.getInstance().createInvitation(invitation);
						//buttonGroup.setSelected(declineRadioButton.getModel(), true);
					}
				}
			}
		});
		GridBagConstraints gbc_declineRadioButon = new GridBagConstraints();
		gbc_declineRadioButon.anchor = GridBagConstraints.WEST;
		gbc_declineRadioButon.insets = new Insets(0, 0, 5, 5);
		gbc_declineRadioButon.gridx = 6;
		gbc_declineRadioButon.gridy = 10;
		this.add(declineRadioButton, gbc_declineRadioButon);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(attendRadioButton);
		buttonGroup.add(declineRadioButton);
	}
	//--------------------------------------------------------------------------
	public void refresh()
		{
		
		}
	//--------------------------------------------------------------------------
	//Here we initialize the different fields, lists and so on.
	public void initialize(int appointmentId)
		{ 
		_attendingListModel.clear();
		_declinedListModel.clear();
		_notAnsweredListModel.clear();
		_appointmentId = appointmentId;
		Appointment appointment = 
				ApplicationModel.getInstance().getAppointment(appointmentId);
		
		ownerField.setText(appointment.getMeetingLeader());
		titleField.setText(appointment.getTitle());
		dateField.setText(appointment.getFormattedDate());
		startField.setText(appointment.getFormattedStartTime());
		endField.setText(appointment.getFormattedEndTime());
		descriptionTextArea.setText(appointment.getDescription());
		locationField.setText(appointment.getLocation());
//		alarmField.setText(ApplicationModel.getInstance().getAlarm(
//				CalendarProgram.loggedInUser, appointmentId).getTime());
		
		
		//If this user is not the same user who created this appointment, then
		// he will not be able to edit this appointment. We therefore deactivate
		// the editButton button
		if(!ApplicationModel.getInstance().username.equals(appointment.getMeetingLeader()))
			{ editButton.setEnabled(false); }
		
		
		//We also need to fill the peopleList with employees
		invitationList = ApplicationModel.getInstance().getInvitationsByAppointment(appointmentId);
		
		for(Invitation invitation : invitationList)
			{
			if(invitation.getAnswer() == Invitation.Answer.ACCEPTED)
				{ _attendingListModel.addElement(invitation.getEmployeeEmail()); }
			else if(invitation.getAnswer() == Invitation.Answer.DECLINED)
				{ _declinedListModel.addElement(invitation.getEmployeeEmail()); }
			else
				{ _notAnsweredListModel.addElement(
						invitation.getEmployeeEmail()); }
			}
		
		for (Invitation invitation : invitationList) {
			if (invitation.getEmployeeEmail().equals(ApplicationModel.getInstance().username)) {
				if (invitation.getAnswer() == Answer.ACCEPTED) {
					buttonGroup.setSelected(attendRadioButton.getModel(), true);
				}
				else if (invitation.getAnswer() == Answer.DECLINED) {
					buttonGroup.setSelected(declineRadioButton.getModel(), true);
				}
			}
		}
		
		alarmField.setText(ApplicationModel.getInstance().getAlarm(ApplicationModel.getInstance().username, _appointmentId).getTime());
	}
	//--------------------------------------------------------------------------
	public void setEditView(EditView editView)
		{ _editView = editView; }
	//**************************************************************************
	class CalendarListener implements ActionListener
		{
		public void actionPerformed(ActionEvent e) 
			{
			CardLayout c1 = (CardLayout)(_parentContentPane.getLayout());
			c1.show(_parentContentPane, "Calendar View");
			}
		}
	//**************************************************************************
	class EditListener implements ActionListener
		{
		public void actionPerformed(ActionEvent e) 
			{
			_editView.initialize(_appointmentId);
			CardLayout c1 = (CardLayout)(_parentContentPane.getLayout());
			c1.show(_parentContentPane, "Edit View");
			}
		}
	//**************************************************************************
	class SaveListener implements ActionListener
		{
		public void actionPerformed(ActionEvent e) 
			{
			Alarm alarm = new Alarm(_appointmentId,
					ApplicationModel.getInstance().username);
			alarm.setTime(alarmField.getText());
			ApplicationModel.getInstance().createAlarm(alarm);
			
			JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(getRootPane()),
					"Your answer has been saved.",
					"Your answer has been saved.",
					JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
