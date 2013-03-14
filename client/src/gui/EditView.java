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

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EditView extends JPanel/*JFrame*/ {
	private JButton calendarButton;
	private JPanel _parentContentPane;
	private JTextField titleField;
	private JTextField dateField;
	private JTextField startField;
	private JTextField endField;
	private JTextField whereField;

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
		
		JButton saveButton = new JButton("Save");
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.fill = GridBagConstraints.BOTH;
		gbc_saveButton.insets = new Insets(0, 0, 5, 5);
		gbc_saveButton.gridx = 4;
		gbc_saveButton.gridy = 1;
		this.add(saveButton, gbc_saveButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
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
		
		JList peopleList = new JList();
		GridBagConstraints gbc_peopleList = new GridBagConstraints();
		gbc_peopleList.insets = new Insets(0, 0, 5, 5);
		gbc_peopleList.fill = GridBagConstraints.BOTH;
		gbc_peopleList.gridx = 4;
		gbc_peopleList.gridy = 4;
		gbc_peopleList.gridwidth = 2;
		gbc_peopleList.gridheight = 3;
		this.add(peopleList, gbc_peopleList);
		
		JLabel startLabel = new JLabel("Start:");
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
		
		JButton addButton = new JButton("Add");
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.insets = new Insets(0, 0, 5, 5);
		gbc_addButton.gridx = 4;
		gbc_addButton.gridy = 6;
		this.add(addButton, gbc_addButton);
		
		JButton removeButton = new JButton("Remove");
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.insets = new Insets(0, 0, 5, 5);
		gbc_removeButton.gridx = 5;
		gbc_removeButton.gridy = 6;
		this.add(removeButton, gbc_removeButton);
		
		JLabel descriptionLabel = new JLabel("Description:");
		GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
		gbc_descriptionLabel.anchor = GridBagConstraints.EAST;
		gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionLabel.gridx = 1;
		gbc_descriptionLabel.gridy = 7;
		this.add(descriptionLabel, gbc_descriptionLabel);
		
		JTextArea descriptionArea = new JTextArea();
		
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
		
		whereField = new JTextField();
		GridBagConstraints gbc_whereField = new GridBagConstraints();
		gbc_whereField.insets = new Insets(0, 0, 5, 5);
		gbc_whereField.fill = GridBagConstraints.HORIZONTAL;
		gbc_whereField.gridx = 4;
		gbc_whereField.gridy = 9;
		gbc_whereField.gridwidth = 2;
		this.add(whereField, gbc_whereField);
		whereField.setColumns(10);
		
		JButton btnBookRoom = new JButton("Book room");
		btnBookRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnBookRoom = new GridBagConstraints();
		gbc_btnBookRoom.fill = GridBagConstraints.BOTH;
		gbc_btnBookRoom.insets = new Insets(0, 0, 5, 5);
		gbc_btnBookRoom.gridx = 4;
		gbc_btnBookRoom.gridy = 10;
		this.add(btnBookRoom, gbc_btnBookRoom);
		
		JButton btnUnbook = new JButton("Unbook");
		GridBagConstraints gbc_btnUnbook = new GridBagConstraints();
		gbc_btnUnbook.fill = GridBagConstraints.BOTH;
		gbc_btnUnbook.insets = new Insets(0, 0, 5, 5);
		gbc_btnUnbook.gridx = 5;
		gbc_btnUnbook.gridy = 10;
		this.add(btnUnbook, gbc_btnUnbook);
	}

}
