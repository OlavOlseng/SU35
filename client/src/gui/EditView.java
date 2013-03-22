package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.SwingConstants;

import models.ApplicationModel;
import models.Appointment;
import models.Employee;
import models.Invitation;
import models.Invitation.Answer;


public class EditView extends JPanel/*JFrame*/ {
	private int 					_appointmentId;
	private ActionListener 		peopleListener;
	private JButton 				addButton;
	private JButton 				deleteButton;
	private JButton 				saveButton;
	private JButton 				removeButton;
	private JButton 				calendarButton;
	private JButton 				chooseDate;
	private JButton 				bookRoomButton;
	private JButton 				unbookRoomButton;
	private JList					peopleList;
	private JPanel 				_parentContentPane;
	private JPopupMenu 			addPeopleMenu;
	private JScrollPane 			peopleListScrollPane;
	private JTextArea 			descriptionArea;
	private JTextField 			titleField;
	private JTextField 			dateField;
	private JTextField 			startField;
	private JTextField 			endField;
	private JTextField 			locationField;
	private DefaultListModel 	peopleListModel;
	
	/**
	 * Launch the application.
	 */
	/*	public static void main(String[] args) {
			JFrame frame = new JFrame("Superblaster");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(50, 50, 800, 600);
			frame.setContentPane(new EditView());
			frame.pack();
			frame.setVisible(true);
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					EditView frame = new EditView();
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
	public EditView(JPanel parentContentPane) {
		_parentContentPane = parentContentPane;
		peopleListModel	 = new DefaultListModel();
		
		
		/*setTitle("Superblaster");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		//setBounds(50, 50, 800, 600);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setBackground(new Color(153, 190, 255));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.rowHeights = new int[]{75, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 75};
		//gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_contentPane.columnWidths = new int[]{50, 100, 300, 110, 125, 125, 50};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0};
		
		//gbl_contentPane.rowHeights = new int[]{20, 50, 0, 0, 0, 0, 0, 0, 0};
		//gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		//gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gbl_contentPane);
		
		
		
		
		calendarButton = new JButton("Calendar");
		calendarButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		CardLayout c1 = (CardLayout)(_parentContentPane.getLayout());
		c1.show(_parentContentPane, "Calendar View");
		
		}
		});
		
		GridBagConstraints gbc_calendarButton = new GridBagConstraints();
		gbc_calendarButton.fill = GridBagConstraints.BOTH;
		gbc_calendarButton.insets = new Insets(0, 0, 5, 5);
		gbc_calendarButton.gridx = 1;
		gbc_calendarButton.gridy = 1;
		this.add(calendarButton, gbc_calendarButton);
		
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveListener());
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.fill = GridBagConstraints.BOTH;
		gbc_saveButton.insets = new Insets(0, 0, 5, 5);
		gbc_saveButton.gridx = 4;
		gbc_saveButton.gridy = 1;
		this.add(saveButton, gbc_saveButton);
		
		
		deleteButton = new JButton("Delete");
		//TODO IMPLEMENT DELEITION MOTHERFUCKER
		deleteButton.addActionListener(new DeleteListener());
		
		GridBagConstraints gbc_deleteButton = new GridBagConstraints();
		gbc_deleteButton.fill = GridBagConstraints.BOTH;
		gbc_deleteButton.insets = new Insets(0, 0, 5, 5);
		gbc_deleteButton.gridx = 5;
		gbc_deleteButton.gridy = 1;
		this.add(deleteButton, gbc_deleteButton);
		
		
		JLabel titleLabel = new JLabel("Title:");
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.anchor = GridBagConstraints.EAST;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 1;
		gbc_titleLabel.gridy = 3;
		this.add(titleLabel, gbc_titleLabel);
		
		
		titleField = new JTextField();
		GridBagConstraints gbc_titleField = new GridBagConstraints();
		gbc_titleField.insets = new Insets(0, 0, 5, 5);
		gbc_titleField.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleField.gridx = 2;
		gbc_titleField.gridy = 3;
		this.add(titleField, gbc_titleField);
		titleField.setColumns(20);
		
		
		JLabel peopleLabel = new JLabel("People:");
		GridBagConstraints gbc_peopleLabel = new GridBagConstraints();
		gbc_peopleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_peopleLabel.gridx = 4;
		gbc_peopleLabel.gridy = 3;
		gbc_peopleLabel.gridwidth = 2;
		this.add(peopleLabel, gbc_peopleLabel);
		
		
		JLabel dateLabel = new JLabel("Date:");
		dateLabel.setToolTipText("Enter date in the format: YYYY-MM-DD");
		GridBagConstraints gbc_dateLabel = new GridBagConstraints();
		gbc_dateLabel.anchor = GridBagConstraints.EAST;
		gbc_dateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dateLabel.gridx = 1;
		gbc_dateLabel.gridy = 4;
		this.add(dateLabel, gbc_dateLabel);
		
		
		dateField = new JTextField();
		GridBagConstraints gbc_dateField = new GridBagConstraints();
		gbc_dateField.insets = new Insets(0, 0, 5, 5);
		gbc_dateField.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateField.gridx = 2;
		gbc_dateField.gridy = 4;
		this.add(dateField, gbc_dateField);
		dateField.setColumns(20);
		
		
		chooseDate = new JButton("Choose Date");
		GridBagConstraints gbc_chooseDate = new GridBagConstraints();
		gbc_chooseDate.fill = GridBagConstraints.BOTH;
		gbc_chooseDate.insets = new Insets(0, 0, 5, 5);
		gbc_chooseDate.gridx = 3;
		gbc_chooseDate.gridy = 4;
		add(chooseDate, gbc_chooseDate);
		
		chooseDate.addActionListener(new ChooseDateListener());
		
		
		
		//Here we create the list that will contain emailaddresses for the people
		// who will be participating in a meeting 
		peopleList = new JList(peopleListModel);
		peopleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		peopleListScrollPane = new JScrollPane(peopleList);
		
		GridBagConstraints gbc_peopleListScrollPane = new GridBagConstraints();
		gbc_peopleListScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_peopleListScrollPane.fill = GridBagConstraints.BOTH;
		gbc_peopleListScrollPane.gridx = 4;
		gbc_peopleListScrollPane.gridy = 4;
		gbc_peopleListScrollPane.gridwidth = 2;
		gbc_peopleListScrollPane.gridheight = 3;
		this.add(peopleListScrollPane, gbc_peopleListScrollPane);
		
		
		JLabel startLabel = new JLabel("Start:");
		startLabel.setToolTipText("Enter time in the format: HH:MM");
		GridBagConstraints gbc_startLabel = new GridBagConstraints();
		gbc_startLabel.anchor = GridBagConstraints.EAST;
		gbc_startLabel.insets = new Insets(0, 0, 5, 5);
		gbc_startLabel.gridx = 1;
		gbc_startLabel.gridy = 5;
		this.add(startLabel, gbc_startLabel);
		
		
		startField = new JTextField();
		GridBagConstraints gbc_startField = new GridBagConstraints();
		gbc_startField.insets = new Insets(0, 0, 5, 5);
		gbc_startField.fill = GridBagConstraints.HORIZONTAL;
		gbc_startField.gridx = 2;
		gbc_startField.gridy = 5;
		this.add(startField, gbc_startField);
		startField.setColumns(20);
		
		
		JLabel endLabel = new JLabel("End:");
		endLabel.setToolTipText("Enter time in the format: HH:MM");
		GridBagConstraints gbc_endLabel = new GridBagConstraints();
		gbc_endLabel.anchor = GridBagConstraints.EAST;
		gbc_endLabel.insets = new Insets(0, 0, 5, 5);
		gbc_endLabel.gridx = 1;
		gbc_endLabel.gridy = 6;
		this.add(endLabel, gbc_endLabel);
		
		
		endField = new JTextField();
		GridBagConstraints gbc_endField = new GridBagConstraints();
		gbc_endField.insets = new Insets(0, 0, 5, 5);
		gbc_endField.fill = GridBagConstraints.HORIZONTAL;
		gbc_endField.gridx = 2;
		gbc_endField.gridy = 6;
		this.add(endField, gbc_endField);
		endField.setColumns(20);
		
		
		addButton = new JButton("Add");
		addButton.addActionListener(new AddListener());
		
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.fill = GridBagConstraints.BOTH;
		gbc_addButton.insets = new Insets(0, 0, 5, 5);
		gbc_addButton.gridx = 4;
		gbc_addButton.gridy = 7;
		this.add(addButton, gbc_addButton);
		
		
		removeButton = new JButton("Remove");
		removeButton.setEnabled(false);
		removeButton.addActionListener(new RemoveListener());
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.fill = GridBagConstraints.BOTH;
		gbc_removeButton.insets = new Insets(0, 0, 5, 5);
		gbc_removeButton.gridx = 5;
		gbc_removeButton.gridy = 7;
		this.add(removeButton, gbc_removeButton);
		
		
		JLabel descriptionLabel = new JLabel("Description:");
		GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
		gbc_descriptionLabel.anchor = GridBagConstraints.EAST;
		gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionLabel.gridx = 1;
		gbc_descriptionLabel.gridy = 7;
		this.add(descriptionLabel, gbc_descriptionLabel);
		
		
		descriptionArea = new JTextArea();
		descriptionArea.setLineWrap(true);
		
		JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
		GridBagConstraints gbc_descriptionScrollPane = new GridBagConstraints();
		gbc_descriptionScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionScrollPane.fill = GridBagConstraints.BOTH;
		gbc_descriptionScrollPane.gridx = 2;
		gbc_descriptionScrollPane.gridy = 7;
		gbc_descriptionScrollPane.gridheight = 4;
		this.add(descriptionScrollPane, gbc_descriptionScrollPane);
		
		
		JLabel locationLabel = new JLabel("Location:");
		GridBagConstraints gbc_locationLabel = new GridBagConstraints();
		gbc_locationLabel.insets = new Insets(0, 0, 5, 5);
		gbc_locationLabel.gridx = 4;
		gbc_locationLabel.gridy = 8;
		gbc_locationLabel.gridwidth = 2;
		this.add(locationLabel, gbc_locationLabel);
		
		
		locationField = new JTextField();
		GridBagConstraints gbc_whereField = new GridBagConstraints();
		gbc_whereField.insets = new Insets(0, 0, 5, 5);
		gbc_whereField.fill = GridBagConstraints.HORIZONTAL;
		gbc_whereField.gridx = 4;
		gbc_whereField.gridy = 9;
		gbc_whereField.gridwidth = 2;
		this.add(locationField, gbc_whereField);
		locationField.setColumns(10);
		
		
		bookRoomButton = new JButton("Book room");
		bookRoomButton.addActionListener(new BookRoomListener());
		
		GridBagConstraints gbc_btnBookRoom = new GridBagConstraints();
		gbc_btnBookRoom.fill = GridBagConstraints.BOTH;
		gbc_btnBookRoom.insets = new Insets(0, 0, 5, 5);
		gbc_btnBookRoom.gridx = 4;
		gbc_btnBookRoom.gridy = 10;
		this.add(bookRoomButton, gbc_btnBookRoom);
		
		
		unbookRoomButton = new JButton("Unbook");
		unbookRoomButton.addActionListener(new UnbookRoomListener());
		GridBagConstraints gbc_unbookRoomButton = new GridBagConstraints();
		gbc_unbookRoomButton.fill = GridBagConstraints.BOTH;
		gbc_unbookRoomButton.insets = new Insets(0, 0, 5, 5);
		gbc_unbookRoomButton.gridx = 5;
		gbc_unbookRoomButton.gridy = 10;
		this.add(unbookRoomButton, gbc_unbookRoomButton);
		}
//--------------------------------------------------------------------------
	public void initialize(int appointmentId)//Appointment appointment)
		{
		_appointmentId = appointmentId;
	
		//Make sure peopleList is empty
		peopleListModel.clear();
		peopleList.clearSelection();
	
		//If appointmentID is -1 this means that this is a new appointment and we
		// need to make sure that the fields are empty 
		if(_appointmentId == 0) { 
			
			resetPanel(); 
			deleteButton.setEnabled(false);
			}
		//This is an appointment that we might want to edit.  Get data from
		// the specified appointment 
		else
			{
			deleteButton.setEnabled(true);
			Appointment appointment = 
					ApplicationModel.getInstance().getAppointment(appointmentId);
	
	
			titleField.setText(appointment.getTitle());
			dateField.setText(appointment.getFormattedDate());
			startField.setText(appointment.getFormattedStartTime());
			endField.setText(appointment.getFormattedEndTime());
			descriptionArea.setText(appointment.getDescription());
	
	
			//If the location is not a meeting room
			if(appointment.getMeetingRoom().equals(""))
				{
				locationField.setText(appointment.getLocation());
				unbookRoomButton.setEnabled(false);
				}
			//Location is a meeting room a we handle this
			else
				{
				locationField.setText(appointment.getMeetingRoom());
				bookRoomButton.setEnabled(false);
				unbookRoomButton.setEnabled(true);
				locationField.setEnabled(false);
				}
	
	
			//We also need to fill the peopleList with employees
			ArrayList<Invitation> invitationList = 
					ApplicationModel.getInstance().getInvitationsByAppointment(
							appointmentId);
	
			for(Invitation invitation : invitationList)
				{
				peopleListModel.addElement(invitation.getEmployeeEmail());
				}
	
			//			public ArrayList<String> getEmployeesForApplication(applicationId)
			//				{
			//				ArrayList<String> = new ArrayList<String>
			//				}
			}
		}
	//--------------------------------------------------------------------------
	public void setLocationField(String location)
		{ locationField.setText(location); }
	//--------------------------------------------------------------------------
	public void setLocationFieldEnabled(boolean enabled)
		{
		if(enabled == true)
			{ locationField.setEnabled(true); }
		else
			{ locationField.setEnabled(false); }
		}
	//--------------------------------------------------------------------------
	public void setBookRoomButtonEnabled(boolean enabled)
		{
		if(enabled == true)
			{ bookRoomButton.setEnabled(true); }
		else
			{ bookRoomButton.setEnabled(false); }
		}
	//--------------------------------------------------------------------------
	public void setUnbookRoomButtonEnabled(boolean enabled)
		{
		if(enabled == true)
			{ unbookRoomButton.setEnabled(true); }
		else
			{ unbookRoomButton.setEnabled(false); }
		}
	//--------------------------------------------------------------------------
	private void resetPanel()
		{
		//Set all fields to empty fields
		titleField.setText("");
		dateField.setText("");
		startField.setText("");
		endField.setText("");
		descriptionArea.setText("");
		locationField.setText("");
	
		//Make sure peopleList is empty
		peopleListModel.clear();
		peopleList.clearSelection();
	
		//We have to make sure that the user is automatically inserted into the 
		// the peopleList
		peopleListModel.addElement(ApplicationModel.getInstance().username);
	
		unbookRoomButton.setEnabled(false);
		deleteButton.setEnabled(false);
		}
	//--------------------------------------------------------------------------
	//This function checks the different components
	private boolean isDataValid()
		{
		if(titleField.getText().equals(""))
			{
			JOptionPane.showMessageDialog((JFrame)SwingUtilities.getRoot(
					_parentContentPane), "Title field is empty!\n" +
							"Please enter title of appointment.",
							"Not valid input", JOptionPane.ERROR_MESSAGE);
	
			return false;
			}
		if(dateField.getText().equals(""))
			{
			JOptionPane.showMessageDialog((JFrame)SwingUtilities.getRoot(
					_parentContentPane), "Date field is empty!\n" +
							"Please enter date of appointment.",
							"Not valid input", JOptionPane.ERROR_MESSAGE);
	
			return false;
			}
		//		TODO: Check the format of the date field
		//		if(!titleField.getText().equals(""))
		//			{
		//			JOptionPane.showMessageDialog((JFrame)SwingUtilities.getRoot(
		//					_parentContentPane), "Format of date field is wrong!\n" +
		//					"The correct format is DD-MM-YYYY.",
		//					"Not valid input", JOptionPane.ERROR_MESSAGE);
		//			
		//			return false;
		//			}
		if(startField.getText().equals(""))
			{
			JOptionPane.showMessageDialog((JFrame)SwingUtilities.getRoot(
					_parentContentPane), "Start field is empty!\n" +
							"Please enter start of appointment.",
							"Not valid input", JOptionPane.ERROR_MESSAGE);
	
			return false;
			}
		//		TODO: Check if date has not already been
		//		if(!titleField.getText().equals(""))
		//			{
		//			JOptionPane.showMessageDialog((JFrame)SwingUtilities.getRoot(
		//					_parentContentPane),
		//					"Date is incorrect!\n +
		//					"You can not select a date that has already been."
		//					"The correct format is HH:MM:SS.",
		//					"Not valid input", JOptionPane.ERROR_MESSAGE);
		//			
		//			return false;
		//			}	
		//		TODO: Check the format of the start field
		//		if(!titleField.getText().equals(""))
		//			{
		//			JOptionPane.showMessageDialog((JFrame)SwingUtilities.getRoot(
		//					_parentContentPane), "Format of start field is wrong!\n" +
		//					"The correct format is HH:MM:SS.",
		//					"Not valid input", JOptionPane.ERROR_MESSAGE);
		//			
		//			return false;
		//			}	
		if(endField.getText().equals(""))
			{
			JOptionPane.showMessageDialog((JFrame)SwingUtilities.getRoot(
					_parentContentPane), "End field is empty!\n" +
							"Please enter end of appointment.",
							"Not valid input", JOptionPane.ERROR_MESSAGE);
	
			return false;
			}
		//		TODO: Check the format of the end field
		//		if(!endField.getText().equals(""))
		//			{
		//			JOptionPane.showMessageDialog((JFrame)SwingUtilities.getRoot(
		//					_parentContentPane), "Format of end field is wrong!\n" +
		//					"The correct format is HH:MM:SS.",
		//					"Not valid input", JOptionPane.ERROR_MESSAGE);
		//			
		//			return false;
		//			}
		if(descriptionArea.getText().equals(""))
			{
			JOptionPane.showMessageDialog((JFrame)SwingUtilities.getRoot(
					_parentContentPane), "Description field is empty!\n" +
							"Please enter description of appointment.",
							"Not valid input", JOptionPane.ERROR_MESSAGE);
	
			return false;
			}
		if(locationField.getText().equals(""))
			{
//			JOptionPane.showMessageDialog((JFrame)SwingUtilities.getRoot(
//					_parentContentPane), "Location field is empty!\n" +
//							"Please enter location of appointment.",
//							"Not valid input", JOptionPane.ERROR_MESSAGE);
//	
			locationField.setText("Nowhere");
//			return false;
			}
	
	
		return true;
		}
	//**************************************************************************
	class AddListener implements ActionListener
		{
		public void actionPerformed(ActionEvent arg0)
			{
			//Here we handle code related to adding people to an appointment
			addPeopleMenu = new JPopupMenu();
			JLabel addPeopleMenuLabel = new JLabel("Select people to add to appointment:");
			addPeopleMenu.add(addPeopleMenuLabel);
			addPeopleMenu.addSeparator();			
		
			//Here we receive all the email addresses for the employees
			ArrayList<String> emailAddresses = ApplicationModel.getInstance().getEmployees();
			JMenuItem menuItem;
		
		
			for(int i = 0; i < emailAddresses.size(); i++)
				{
				menuItem = new JMenuItem(emailAddresses.get(i));
				addPeopleMenu.add(menuItem);
				menuItem.addActionListener(new PeopleListener());
				}
		
		
			addPeopleMenu.show(addButton, -290, -135);
			}
		}
	//**************************************************************************
	class BookRoomListener implements ActionListener
		{
		public void actionPerformed(ActionEvent event)
			{
			//If all the relevant data are filled in, then we can proceed and open
			// a window where we can select rooms
			if(!dateField.getText().equals("") && 
					!startField.getText().equals("") &&
					!endField.getText().equals(""))
				{
				BookRoomDialog bookRoomDialog = new BookRoomDialog(
						(JFrame) bookRoomButton.getTopLevelAncestor(), true,
						peopleList.getModel().getSize(), EditView.this, dateField.getText(), startField.getText(), endField.getText());
				bookRoomDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				bookRoomDialog.setVisible(true);
				}
			//All relevant fields are not filled with data. Output error message
			else
				{
				JOptionPane.showMessageDialog(
						(JFrame)SwingUtilities.getRoot(_parentContentPane),
						"All the fields for 'Date', 'Start' and 'End' have to be " +
								"filled!", "Error message", 
								JOptionPane.ERROR_MESSAGE);
				}			
			}
		}
	//**************************************************************************
	class UnbookRoomListener implements ActionListener
		{
		public void actionPerformed(ActionEvent event)
			{
			//We don't really need the if construct, but use it anyway
			//If locationField is not enabled we know that there has been a room
			// reservation
			if(locationField.isEnabled() == false)
				{ 
				//ApplicationModel.getInstance().deleteRoom(locationField.getText());
				locationField.setText("");
				locationField.setEnabled(true);
				bookRoomButton.setEnabled(true);
				unbookRoomButton.setEnabled(false);
		
				JOptionPane.showMessageDialog(
						(JFrame)SwingUtilities.getRoot(_parentContentPane),
						"Room has been unbooked.", "Information message",
						JOptionPane.INFORMATION_MESSAGE);
				}			
			}
		}
	//**************************************************************************
	//Here we handle removal of people in the list with employees 
	class RemoveListener implements ActionListener
		{
		public void actionPerformed(ActionEvent event)
			{
			int index = peopleList.getSelectedIndex();
		
		
			if(index != -1)
				{
				if(peopleListModel.get(index).equals(ApplicationModel.getInstance().username))
					{
					JOptionPane.showMessageDialog(
							(JFrame)SwingUtilities.getRoot(_parentContentPane),
							"You cannot remove yourself from an appointmentent you create.",
							"Warning", JOptionPane.WARNING_MESSAGE);
					}
				else
					{ peopleListModel.remove(index); }
				}
		
			//If there is only 1 element in the list (the user), then we
			//	deactivate the removeButton
			if(peopleListModel.getSize() == 1)
				{ removeButton.setEnabled(false); }
			}
		}
	//**************************************************************************
	//Here we handle selection of emailaddresses. The emailaddress selected
	// goes into the peopleList.
	class PeopleListener implements ActionListener
		{
		public void actionPerformed(ActionEvent event) {
		//We get the selected email
			JMenuItem emailItem 		  = (JMenuItem)event.getSource();
			String selectedEmail 	  = emailItem.getText();
			boolean isEmailSelected   = false;
	
	
		//We need to check if the emailaddress is already in the peopleList
		//NB! Could be handled differently where already chosen
		// emailaddresses would not be available in addPeopleMenu
			for(int i = 0; i < peopleList.getModel().getSize(); i++) {
				String element = (String)peopleList.getModel().getElementAt(i);
		
		
				if(selectedEmail.equals(element))
					{
					JOptionPane.showMessageDialog((JFrame)SwingUtilities.getRoot(
							_parentContentPane),
							"Email address or group is already in list!",
							"Error message", JOptionPane.ERROR_MESSAGE);
					isEmailSelected = true;
		
					break;
					}
				}
	
	
		//Email address is not already selected and we therefore add it to
		// the peopleList. We also activate the remove button
			if(isEmailSelected == false) {
				peopleListModel.addElement(selectedEmail);
				removeButton.setEnabled(true);
				}
			}
		}
	//**************************************************************************
	class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String question = "You wanna delete this shizz, mothertrucker!?";
			String title = "Delete me?";
			
			int i = JOptionPane.showConfirmDialog(JOptionPane.getFrameForComponent(getRootPane()), question, title, JOptionPane.YES_NO_OPTION );
			if(i == 0) {
				ApplicationModel.getInstance().connection.sendAppointmentDeletion(_appointmentId);
				
				ArrayList<Invitation> invitationList = 
						ApplicationModel.getInstance().getInvitationsByAppointment(
								_appointmentId);
		
				for(Invitation invitation : invitationList)
					{
					ApplicationModel.getInstance().connection.sendInvitationDeletion(invitation.getEmployeeEmail(), _appointmentId);
					}
				CardLayout c1 = (CardLayout)(_parentContentPane.getLayout());
				c1.show(_parentContentPane, "Calendar View");
			}
		}
	}
	
	class SaveListener implements ActionListener
		{	
		public void actionPerformed(ActionEvent event)
			{
			//We need to check if the data is valid
			//If the data is valid we save appointment in the database and return
			// to the calendar-view
			if(isDataValid() == true)
				{
				Appointment appointment = new Appointment((int) System.currentTimeMillis());
		
				if(_appointmentId != 0)	{
				appointment.setAppointmentID(_appointmentId);
				}
				//This is a new appointment
		
				//TODO: Save appointment for this user in database and send message
				// to relevant employees (employees added to the people-list)
		
				appointment.setTitle(titleField.getText());
				appointment.setDate(dateField.getText());
				appointment.setStartTime(startField.getText());
				appointment.setEndTime(endField.getText());
				appointment.setDescription(descriptionArea.getText());
		
		
				//If locationField is not enabled then this means that a room has
				// been boooked
				if(locationField.isEnabled() == false)
					{
					appointment.setMeetingRoom(locationField.getText());
					appointment.setLocation(locationField.getText());
					}
				//Else a meetingroom has not been selected and the location is
				// specified manually by the user
				else
					{
						appointment.setMeetingRoom("None");
						appointment.setLocation(locationField.getText());
					}
		
		
				//appointment.setMeetingRoom(locationField.getText());
				appointment.setMeetingLeader(ApplicationModel.getInstance().username);
		
				//ApplicationModel.getInstance().addAppointment(
				//		appointment.getAppointmentID(), appointment);
				ApplicationModel.getInstance().createAppointment(appointment);
		
		
				//We also need to create invitations for all the employees in
				// the peopleList
				for(int i = 0; i < peopleListModel.getSize(); i++)
					{
					//						ApplicationModel.getInstance().addInvitation(
					//								(String)peopleListModel.get(i), _appointmentId, 
					//								new Invitation((String)peopleListModel.get(i),
					//										_appointmentId));
					Invitation ii = new Invitation((String)peopleListModel.get(i),
							appointment.getAppointmentID());
					ii.setAnswer(Answer.PENDING);
					ii.setMessage("Something has happened!");
					ApplicationModel.getInstance().createInvitation(ii);
							
					}
		
		
				JOptionPane.showMessageDialog(
						(JFrame)SwingUtilities.getRoot(_parentContentPane),
						"Appointment has been saved!",
						"Information Message",
						JOptionPane.INFORMATION_MESSAGE);
				CardLayout c1 = (CardLayout)(_parentContentPane.getLayout());
				c1.show(_parentContentPane, "Calendar View");
				}
			//This is an editable appointment and we need to updata data
			//				else
			//					{
			//					//TODO: Not complete. We need to call update functions
			//					
			//					
			//					JOptionPane.showMessageDialog(
			//							(JFrame)SwingUtilities.getRoot(_parentContentPane),
			//							"Appointment has been updated!",
			//							"Information Message",
			//							JOptionPane.INFORMATION_MESSAGE);
			//					
			//					}
			//We go to the calendar view
			}
		}
	//**************************************************************************
	class ChooseDateListener implements ActionListener
		{
		public void actionPerformed(ActionEvent event)
			{
			dateField.setText(new DatePicker((JFrame)SwingUtilities.getRoot(
					_parentContentPane)).setPickedDate());
			}
		}
	}
