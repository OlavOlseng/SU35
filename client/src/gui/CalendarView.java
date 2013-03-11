package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;


public class CalendarView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalendarView window = new CalendarView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CalendarView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Superblaster");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{120, 670, 0};
		gridBagLayout.rowHeights = new int[]{50, 540, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnNotifications = new JButton("Notifications");
		btnNotifications.setToolTipText("");
		GridBagConstraints gbc_btnNotifications = new GridBagConstraints();
		gbc_btnNotifications.fill = GridBagConstraints.BOTH;
		gbc_btnNotifications.gridx = 0;
		gbc_btnNotifications.gridy = 0;
		panel.add(btnNotifications, gbc_btnNotifications);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{170, 100, 100, 100, 100, 100, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton btnMe = new JButton("Me");
		GridBagConstraints gbc_btnMe = new GridBagConstraints();
		gbc_btnMe.fill = GridBagConstraints.BOTH;
		gbc_btnMe.insets = new Insets(0, 0, 0, 5);
		gbc_btnMe.gridx = 1;
		gbc_btnMe.gridy = 0;
		panel_1.add(btnMe, gbc_btnMe);
		
		JButton btnNewButton_2 = new JButton("Calendars");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 0;
		panel_1.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnGoto = new JButton("Go to");
		GridBagConstraints gbc_btnGoto = new GridBagConstraints();
		gbc_btnGoto.fill = GridBagConstraints.BOTH;
		gbc_btnGoto.insets = new Insets(0, 0, 0, 5);
		gbc_btnGoto.gridx = 3;
		gbc_btnGoto.gridy = 0;
		panel_1.add(btnGoto, gbc_btnGoto);
		
		JButton btnCreate = new JButton("Create");
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.fill = GridBagConstraints.BOTH;
		gbc_btnCreate.insets = new Insets(0, 0, 0, 5);
		gbc_btnCreate.gridx = 4;
		gbc_btnCreate.gridy = 0;
		panel_1.add(btnCreate, gbc_btnCreate);
		
		JButton btnLogout = new JButton("Logout");
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.fill = GridBagConstraints.BOTH;
		gbc_btnLogout.gridx = 5;
		gbc_btnLogout.gridy = 0;
		panel_1.add(btnLogout, gbc_btnLogout);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		frame.getContentPane().add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{470, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JTextArea txtrInfo = new JTextArea();
		txtrInfo.setEditable(false);
		txtrInfo.setRows(19);
		txtrInfo.setText("Info:");
		GridBagConstraints gbc_txtrInfo = new GridBagConstraints();
		gbc_txtrInfo.anchor = GridBagConstraints.NORTH;
		gbc_txtrInfo.insets = new Insets(0, 0, 5, 0);
		gbc_txtrInfo.fill = GridBagConstraints.BOTH;
		gbc_txtrInfo.gridx = 0;
		gbc_txtrInfo.gridy = 0;
		panel_2.add(txtrInfo, gbc_txtrInfo);
		
		JButton btnNewButton = new JButton("More");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		panel_2.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 2;
		panel_2.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 1;
		frame.getContentPane().add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{80, 80, 80, 80, 80, 80, 80, 80, 0};
		gbl_panel_3.rowHeights = new int[]{30, 50, 80, 80, 80, 80, 80, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JButton button = new JButton("Previous week");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.HORIZONTAL;
		gbc_button.gridwidth = 2;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 0;
		panel_3.add(button, gbc_button);
		
		JLabel label = new JLabel("Week: XX");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 4;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 0;
		panel_3.add(label, gbc_label);
		
		JButton button_1 = new JButton("Next week");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_1.gridwidth = 2;
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 6;
		gbc_button_1.gridy = 0;
		panel_3.add(button_1, gbc_button_1);
		
		JLabel lblMonday = new JLabel("Monday");
		GridBagConstraints gbc_lblMonday = new GridBagConstraints();
		gbc_lblMonday.insets = new Insets(0, 0, 5, 5);
		gbc_lblMonday.gridx = 1;
		gbc_lblMonday.gridy = 1;
		panel_3.add(lblMonday, gbc_lblMonday);
		
		JLabel lblTuesday = new JLabel("Tuesday");
		GridBagConstraints gbc_lblTuesday = new GridBagConstraints();
		gbc_lblTuesday.insets = new Insets(0, 0, 5, 5);
		gbc_lblTuesday.gridx = 2;
		gbc_lblTuesday.gridy = 1;
		panel_3.add(lblTuesday, gbc_lblTuesday);
		
		JLabel lblWednesday = new JLabel("Wednesday");
		GridBagConstraints gbc_lblWednesday = new GridBagConstraints();
		gbc_lblWednesday.insets = new Insets(0, 0, 5, 5);
		gbc_lblWednesday.gridx = 3;
		gbc_lblWednesday.gridy = 1;
		panel_3.add(lblWednesday, gbc_lblWednesday);
		
		JLabel lblThursday = new JLabel("Thursday");
		GridBagConstraints gbc_lblThursday = new GridBagConstraints();
		gbc_lblThursday.insets = new Insets(0, 0, 5, 5);
		gbc_lblThursday.gridx = 4;
		gbc_lblThursday.gridy = 1;
		panel_3.add(lblThursday, gbc_lblThursday);
		
		JLabel lblFriday = new JLabel("Friday");
		GridBagConstraints gbc_lblFriday = new GridBagConstraints();
		gbc_lblFriday.insets = new Insets(0, 0, 5, 5);
		gbc_lblFriday.gridx = 5;
		gbc_lblFriday.gridy = 1;
		panel_3.add(lblFriday, gbc_lblFriday);
		
		JLabel lblSaturday = new JLabel("Saturday");
		GridBagConstraints gbc_lblSaturday = new GridBagConstraints();
		gbc_lblSaturday.insets = new Insets(0, 0, 5, 5);
		gbc_lblSaturday.gridx = 6;
		gbc_lblSaturday.gridy = 1;
		panel_3.add(lblSaturday, gbc_lblSaturday);
		
		JLabel lblSunday = new JLabel("Sunday");
		GridBagConstraints gbc_lblSunday = new GridBagConstraints();
		gbc_lblSunday.insets = new Insets(0, 0, 5, 5);
		gbc_lblSunday.gridx = 7;
		gbc_lblSunday.gridy = 1;
		panel_3.add(lblSunday, gbc_lblSunday);
		
		JLabel label_1 = new JLabel("00:00");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 2;
		panel_3.add(label_1, gbc_label_1);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridwidth = 7;
		gbc_panel_4.gridheight = 5;
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 1;
		gbc_panel_4.gridy = 2;
		panel_3.add(panel_4, gbc_panel_4);
		
		JLabel label_2 = new JLabel("06:00");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 3;
		panel_3.add(label_2, gbc_label_2);
		
		JLabel label_3 = new JLabel("12:00");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 4;
		panel_3.add(label_3, gbc_label_3);
		
		JLabel label_4 = new JLabel("18:00");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 5;
		panel_3.add(label_4, gbc_label_4);
		
		JLabel label_5 = new JLabel("23:59");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.insets = new Insets(0, 0, 0, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 6;
		panel_3.add(label_5, gbc_label_5);
	}

}
