package test;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ButtonNTextEx extends JPanel{
	private JButton gButton;
	private JTextField gTextField;
	
	public ButtonNTextEx(){
		gButton = new JButton("Gangsta");
		gButton.addActionListener(new ButtonAction());
		this.add(gButton);
		
		gTextField = new JTextField("...");
		gTextField.setColumns(20);
		this.add(gTextField);
		
		
	}
	
	private class ButtonAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			gTextField.setText("Gangsta");
			
		}
		
		
	}
	
	public static void main(String[] args){
		JFrame frame =  new JFrame();
		frame.getContentPane().add(new ButtonNTextEx());
		frame.pack();
		frame.setVisible(true);
	}
}
