package yahtzee;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class JFrame2 extends JFrame {
	
	boolean[] reroll=new boolean[7];
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					
					JFrame2 frame = new JFrame2();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrame2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("1");
		rdbtnNewRadioButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				reroll[1]=!reroll[1];
			}
		});
		rdbtnNewRadioButton.setBounds(6, 60, 57, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("2");
		rdbtnNewRadioButton_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				reroll[2]=!reroll[2];
			}
		});
		rdbtnNewRadioButton_1.setBounds(6, 86, 57, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("3");
		rdbtnNewRadioButton_2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				reroll[3]=!reroll[3];
			}
		});
		rdbtnNewRadioButton_2.setBounds(6, 112, 57, 23);
		contentPane.add(rdbtnNewRadioButton_2);
		
		JLabel lblDiePosition = new JLabel("Die Position");
		lblDiePosition.setBounds(10, 14, 85, 14);
		contentPane.add(lblDiePosition);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("4");
		rdbtnNewRadioButton_3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				reroll[4]=!reroll[4];
			}
		});
		rdbtnNewRadioButton_3.setBounds(6, 138, 57, 23);
		contentPane.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("5");
		rdbtnNewRadioButton_4.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				reroll[5]=!reroll[5];
			}
		});
		rdbtnNewRadioButton_4.setBounds(6, 164, 57, 23);
		contentPane.add(rdbtnNewRadioButton_4);
		
		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("6");
		rdbtnNewRadioButton_5.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				reroll[6]=!reroll[6];
				
			}
		});
		rdbtnNewRadioButton_5.setBounds(6, 190, 57, 23);
		contentPane.add(rdbtnNewRadioButton_5);
		
		JRadioButton radioButton = new JRadioButton("0");
		radioButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				reroll[0]=!reroll[0];
				
			}
		});
		radioButton.setBounds(6, 34, 57, 23);
		contentPane.add(radioButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(138, 18, 179, 208);
		contentPane.add(panel);
		
		
	}
}
