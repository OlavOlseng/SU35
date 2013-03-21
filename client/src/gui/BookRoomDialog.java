package gui;

import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import models.ApplicationModel;
import models.Appointment;
import models.RoomManager;
import models.RoomManager.NoAvailableRooms;

public class BookRoomDialog extends JDialog
	{
	private int 					_nPeople;
	private String[]				_roomList;
	private JButton 				cancelButton;
	private JComboBox 			roomComboBox;
	private JPanel					contentPane;
	private EditView  			_editView;
	String date;
	String start;
	String end;
	Appointment temp;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args)
//		{
//		EventQueue.invokeLater(new Runnable()
//			{
//				public void run()
//					{
//					try
//						{
//						BookRoomDialog dialog = new BookRoomDialog();
//						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//						dialog.setVisible(true);
//						} catch (Exception e)
//						{
//						e.printStackTrace();
//						}
//					}
//			});
//		}
//
	/**
	 * Create the dialog.
	 */
	public BookRoomDialog(JFrame frame, boolean modal, int nPeople, EditView editView, String date, String start, String end)
		{
		super(frame, modal);
		this.date = date;
		this.start = start;
		this.end = end;
		contentPane = new JPanel();
		_nPeople		= nPeople;
		_editView   = editView;
		
		
		setTitle("Room booking");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{15, 60, 60, 60, 60, 60, 60, 60, 15};
		gridBagLayout.rowHeights = new int[]{25, 25, 25, 25, 25, 25, 25, 25, 25, 25};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0};
		contentPane.setLayout(gridBagLayout);
		
		
		JButton autoBookButton = new JButton("Auto Book");
		
		if(_nPeople < 1)
			{ autoBookButton.setEnabled(false); 
			autoBookButton.setEnabled(true);
			} 
		
		
		autoBookButton.addActionListener(new AutoBookListener());
		
		JLabel text1Label = new JLabel(
				"Select a room from the dropdown menu, or press AutoBook");
		GridBagConstraints gbc_text1Label = new GridBagConstraints();
		gbc_text1Label.insets = new Insets(0, 0, 5, 5);
		gbc_text1Label.gridx = 2;
		gbc_text1Label.gridy = 1;
		gbc_text1Label.gridwidth = 5;
		
		contentPane.add(text1Label, gbc_text1Label);
		
		
		JLabel text2Label = new JLabel(
				"to have the system select a room of proper size automatically.");
		GridBagConstraints gbc_text2Label = new GridBagConstraints();
		gbc_text2Label.insets = new Insets(0, 0, 5, 5);
		gbc_text2Label.gridx = 2;
		gbc_text2Label.gridy = 2;
		gbc_text2Label.gridwidth = 5;
		contentPane.add(text2Label, gbc_text2Label);
		
		
		JLabel text3Label = new JLabel(
				"To use AutoBook employees must have been added.");
		GridBagConstraints gbc_text3Label = new GridBagConstraints();
		gbc_text3Label.insets = new Insets(0, 0, 5, 5);
		gbc_text3Label.gridx = 2;
		gbc_text3Label.gridy = 3;
		gbc_text3Label.gridwidth = 5;
		contentPane.add(text3Label, gbc_text3Label);
		
		
		temp = new Appointment(0);
		temp.setDate(date);
		temp.setStartTime(start);
		temp.setEndTime(end);
		
		//Here we get all the rooms 
		ArrayList<String> roomList = null;
		try {
			roomList = RoomManager.findSuitableRooms(_nPeople, temp);
		} catch (NoAvailableRooms e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_roomList = new String[roomList.size()];
		_roomList = roomList.toArray(_roomList);
		// _roomList = rooms;
		//_roomList	= new ArrayList<String>(Arrays.asList(rooms));
		
		roomComboBox = new JComboBox(_roomList);
		GridBagConstraints gbc_roomCoboBox = new GridBagConstraints();
		gbc_roomCoboBox.insets = new Insets(0, 0, 5, 5);
		gbc_roomCoboBox.gridx = 2;
		gbc_roomCoboBox.gridy = 5;
		gbc_roomCoboBox.gridwidth = 5;
		contentPane.add(roomComboBox, gbc_roomCoboBox);
		
		GridBagConstraints gbc_autoBookButton = new GridBagConstraints();
		gbc_autoBookButton.insets = new Insets(0, 0, 5, 5);
		gbc_autoBookButton.gridx = 3;
		gbc_autoBookButton.gridy = 7;
		contentPane.add(autoBookButton, gbc_autoBookButton);
		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(new OkListener());
		GridBagConstraints gbc_okButton = new GridBagConstraints();
		gbc_okButton.insets = new Insets(0, 0, 5, 5);
		gbc_okButton.gridx = 4;
		gbc_okButton.gridy = 7;
		contentPane.add(okButton, gbc_okButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelListener());
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
		gbc_cancelButton.gridx = 5;
		gbc_cancelButton.gridy = 7;
		contentPane.add(cancelButton, gbc_cancelButton);
		
		}
	
	class AutoBookListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String room = "";
				try {
					room = RoomManager.pickSuitableRoom(_nPeople, temp);
					int index = 0;
					for (int j = 0; j < _roomList.length; j++) {
						if (room.equals(_roomList[j])) {
							index = j;
							break;
						}
					}
					roomComboBox.setSelectedIndex(index);
					
				} catch (NoAvailableRooms e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	
	//**************************************************************************
	class CancelListener implements ActionListener
		{
		public void actionPerformed(ActionEvent event)
			{ BookRoomDialog.this.dispose(); }
		}
	//**************************************************************************
	class OkListener implements ActionListener
		{
		public void actionPerformed(ActionEvent event)
			{
			//JComboBox selectedRoom = (JComboBox)event.getSource();
			int index = roomComboBox.getSelectedIndex();
			_editView.setLocationField(_roomList[index]);
			_editView.setLocationFieldEnabled(false);
			_editView.setBookRoomButtonEnabled(false);
			_editView.setUnbookRoomButtonEnabled(true);
			
			BookRoomDialog.this.dispose();
			}
		}
	}
