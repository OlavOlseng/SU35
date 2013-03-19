package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class GotoDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtWeek;
	private JTextField txtDate;
    private JButton btnGo1 = null;
    private JButton btnGo2 = null;
    private JButton cancelButton = null;
    private int answer;
    private String week;
    private String date;
    
    public String getWeek() {
    	return week;
	}

	public String getDate() {
		return date;
	}

	public int getAnswer() {
    	return this.answer;
    }

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
////			GotoDialog dialog = new GotoDialog();
////			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
////			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public GotoDialog(JFrame frame, Boolean modal) {
		super(frame, modal);
		setTitle("Go to:");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{71, 53, 180, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{73, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblWeek = new JLabel("Week:");
			GridBagConstraints gbc_lblWeek = new GridBagConstraints();
			gbc_lblWeek.insets = new Insets(0, 0, 5, 5);
			gbc_lblWeek.anchor = GridBagConstraints.EAST;
			gbc_lblWeek.gridx = 1;
			gbc_lblWeek.gridy = 1;
			contentPanel.add(lblWeek, gbc_lblWeek);
		}
		{
			txtWeek = new JTextField();
			GridBagConstraints gbc_txtWeek = new GridBagConstraints();
			gbc_txtWeek.insets = new Insets(0, 0, 5, 5);
			gbc_txtWeek.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWeek.gridx = 2;
			gbc_txtWeek.gridy = 1;
			contentPanel.add(txtWeek, gbc_txtWeek);
			txtWeek.setColumns(10);
		}
		{
			btnGo1 = new JButton("Go!");
			btnGo1.addActionListener(this);
			GridBagConstraints gbc_btnGo1 = new GridBagConstraints();
			gbc_btnGo1.insets = new Insets(0, 0, 5, 0);
			gbc_btnGo1.gridx = 3;
			gbc_btnGo1.gridy = 1;
			contentPanel.add(btnGo1, gbc_btnGo1);
		}
		{
			JLabel lblDate = new JLabel("Date: YYYY-MM-DD");
			GridBagConstraints gbc_lblDate = new GridBagConstraints();
			gbc_lblDate.insets = new Insets(0, 0, 0, 5);
			gbc_lblDate.anchor = GridBagConstraints.EAST;
			gbc_lblDate.gridx = 1;
			gbc_lblDate.gridy = 2;
			contentPanel.add(lblDate, gbc_lblDate);
		}
		{
			txtDate = new JTextField();
			GridBagConstraints gbc_txtDate = new GridBagConstraints();
			gbc_txtDate.insets = new Insets(0, 0, 0, 5);
			gbc_txtDate.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtDate.gridx = 2;
			gbc_txtDate.gridy = 2;
			contentPanel.add(txtDate, gbc_txtDate);
			txtDate.setColumns(10);
		}
		{
			btnGo2 = new JButton("Go!");
			btnGo2.addActionListener(this);
			GridBagConstraints gbc_btnGo2 = new GridBagConstraints();
			gbc_btnGo2.gridx = 3;
			gbc_btnGo2.gridy = 2;
			contentPanel.add(btnGo2, gbc_btnGo2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Abort");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
        if(btnGo1 == e.getSource()) {
            answer = 1;
            //System.out.println(answer);
            week = txtWeek.getText();
            this.dispose();
        }
        else if(btnGo2 == e.getSource()) {
            answer = 2;
            //System.out.println(answer);
            date = txtDate.getText();
            this.dispose();
        }
        else if(cancelButton == e.getSource()) {
        	this.dispose();
        }
    }
}