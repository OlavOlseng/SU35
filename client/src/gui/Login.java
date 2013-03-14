package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;


public class Login extends JPanel/*JFrame*/ {
	
	private JPanel _parentContentPane;
	private JTextField emailAddress;
	private JPasswordField password;
	private JLabel welcomeLabel;
	private JButton logInButton;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public Login(JPanel parentContentPane) {
		_parentContentPane = parentContentPane;
//		setTitle("Superblaster");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(50, 50, 800, 600);
//		contentPane = new JPanel();
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setBackground(new Color(153, 190, 255));
//		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		//gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0};
		//gbl_contentPane
		gbl_contentPane.columnWidths = new int[]{230, 120, 220, 230};
		gbl_contentPane.rowHeights = new int[]{50, 200, 50, 40, 40, 50, 120, 50};
		//gbl_contentPane.columnWeights = new double[]{Double.MIN_VALUE};
		//gbl_contentPane.rowWeights = new double[]{Double.MIN_VALUE};
		this.setLayout(gbl_contentPane);

		welcomeLabel = new JLabel("Welcome");
		welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		GridBagConstraints gbc_welcomeLabel = new GridBagConstraints();
		gbc_welcomeLabel.gridwidth = 5;
		gbc_welcomeLabel.insets = new Insets(0, 0, 5, 0);
		gbc_welcomeLabel.gridx = 0;
		gbc_welcomeLabel.gridy = 1;
		//gbc_welcomeLabel.gridwidth = 3;
		//gbc_welcomeLabel.gridheight = 3;
		this.add(welcomeLabel, gbc_welcomeLabel);

		JLabel emailAddressLabel = new JLabel("Email address:");
		emailAddressLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_emailAddressLabel = new GridBagConstraints();
		gbc_emailAddressLabel.anchor = GridBagConstraints.WEST;
		gbc_emailAddressLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailAddressLabel.gridx = 1;
		gbc_emailAddressLabel.gridy = 3;
		this.add(emailAddressLabel, gbc_emailAddressLabel);

		emailAddress = new JTextField();
		emailAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_emailAddress = new GridBagConstraints();
		gbc_emailAddress.insets = new Insets(0, 0, 5, 5);
		gbc_emailAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailAddress.gridx = 2;
		gbc_emailAddress.gridy = 3;
		this.add(emailAddress, gbc_emailAddress);
		emailAddress.setColumns(20);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.WEST;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 1;
		gbc_passwordLabel.gridy = 4;
		this.add(passwordLabel, gbc_passwordLabel);
		
		logInButton = new JButton("Log in");
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//NB! Unfinished.
				//We have to check if email and password is correct
//				if (email and password exist in database) {
					CardLayout c1 = (CardLayout)(_parentContentPane.getLayout());
					c1.show(_parentContentPane, "Calendar View");
//				}
//				else {
//					JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(getRootPane()),
//						    "Login failed!" + "\n" +"Please re-enter your email and password, or contact system administrator.",
//						    "Login failed!",
//						    JOptionPane.ERROR_MESSAGE);
////				}
			}
		});

		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_password = new GridBagConstraints();
		gbc_password.insets = new Insets(0, 0, 5, 5);
		gbc_password.fill = GridBagConstraints.HORIZONTAL;
		gbc_password.gridx = 2;
		gbc_password.gridy = 4;
		this.add(password, gbc_password);
		password.setColumns(20);
		logInButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_logInButton = new GridBagConstraints();
		gbc_logInButton.fill = GridBagConstraints.BOTH;
		gbc_logInButton.gridwidth = 2;
		gbc_logInButton.insets = new Insets(0, 0, 5, 0);
		gbc_logInButton.gridx = 1;
		gbc_logInButton.gridy = 6;
		this.add(logInButton, gbc_logInButton);
	}
}
