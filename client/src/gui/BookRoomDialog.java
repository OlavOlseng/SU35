package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class BookRoomDialog extends JDialog
	{
	private int 		_nPeople;
	private JButton 	cancelButton;
	private JPanel		contentPane;
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
	public BookRoomDialog(JFrame frame, boolean modal, int nPeople)
		{
		super(frame, modal);
		
		contentPane = new JPanel();
		nPeople		= nPeople;
		
		setTitle("Room booking");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{15, 60, 60, 60, 60, 60, 60, 60, 15};
		gridBagLayout.rowHeights = new int[]{15, 45, 45, 45, 45, 45, 45, 15,};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		contentPane.setLayout(gridBagLayout);
		
		
		JLabel informationLabel = new JLabel(
				"Select a room from the dropdown menu");//,\n" +
//				"or press AutoBook to have the system select\n" +
//				"a room of proper size automatically");
		GridBagConstraints gbc_informationLabel = new GridBagConstraints();
		gbc_informationLabel.insets = new Insets(0, 0, 5, 5);
		gbc_informationLabel.gridx = 2;
		gbc_informationLabel.gridy = 2;
		gbc_informationLabel.gridwidth = 6;
		gbc_informationLabel.gridheight = 3;
		contentPane.add(informationLabel, gbc_informationLabel);
		
		
		JButton autoBookButton = new JButton("Auto Book");
		
		if(_nPeople == 0)
			{ autoBookButton.setEnabled(false); }
		
		GridBagConstraints gbc_autoBookButton = new GridBagConstraints();
		gbc_autoBookButton.insets = new Insets(0, 0, 5, 5);
		gbc_autoBookButton.gridx = 3;
		gbc_autoBookButton.gridy = 6;
		contentPane.add(autoBookButton, gbc_autoBookButton);
		
		JButton okButton = new JButton("Ok");
		GridBagConstraints gbc_okButton = new GridBagConstraints();
		gbc_okButton.insets = new Insets(0, 0, 5, 5);
		gbc_okButton.gridx = 4;
		gbc_okButton.gridy = 6;
		contentPane.add(okButton, gbc_okButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelListener());
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
		gbc_cancelButton.gridx = 5;
		gbc_cancelButton.gridy = 6;
		contentPane.add(cancelButton, gbc_cancelButton);
		
		}
	//**************************************************************************
	class CancelListener implements ActionListener
		{
		public void actionPerformed(ActionEvent event)
			{ BookRoomDialog.this.dispose(); }
		}
	}
