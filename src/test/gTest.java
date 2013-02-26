package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

public class gTest {

	public static void main(String argv[]) {
		gTest test = new gTest();
	}

	Random rand;
	JFrame frame;
	JButton bjuton;

	public gTest() {
		
		rand = new Random();
		bjuton = new JButton("DET FUNKER");

		frame = new JFrame("DET FUNKER");
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.add(bjuton, BorderLayout.CENTER);
		frame.setVisible(true);
		
		bjuton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				funky();
			}
		});
	}

	

	public void funky() {
		bjuton.setBackground(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
	}
}

