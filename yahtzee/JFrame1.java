package yahtzee;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class JFrame1 extends JFrame {
	private final JLabel lblConfiguration = new JLabel("Configuration");
	public int x;
	public int y;
	private final ButtonGroup DIP = new ButtonGroup();
	private final ButtonGroup Faces = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame1 frame = new JFrame1();
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
	public JFrame1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("number of dice");
		lblNewLabel.setBounds(33, 51, 85, 25);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(160, 52, 190, 40);
		getContentPane().add(panel);
		
		JRadioButton radioButton = new JRadioButton("5");
		radioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				 if (e.getStateChange() == ItemEvent.SELECTED) 
				        x=5;
				    
			}
		});
		DIP.add(radioButton);
		panel.add(radioButton);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("6");
		rdbtnNewRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				 if (e.getStateChange() == ItemEvent.SELECTED) 
				        x=6;
			}
		});
		DIP.add(rdbtnNewRadioButton);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("7");
		radioButton_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				 if (e.getStateChange() == ItemEvent.SELECTED) {
				        x=7;
				    }
			}
		});
		DIP.add(radioButton_1);
		panel.add(radioButton_1);
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(29, 198, 89, 23);
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				System.out.print(x);
				System.out.println(y);
				Die d= new Die();
				JFrame2 frame = new JFrame2();
				frame.setVisible(true);
				dispose();
				
			}
		});
		getContentPane().add(btnSubmit);
		
		JLabel lblNewLabel_1 = new JLabel("number of faces");
		lblNewLabel_1.setBounds(33, 139, 85, 25);
		getContentPane().add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(160, 127, 190, 58);
		getContentPane().add(panel_1);
		
		JRadioButton radioButton_2 = new JRadioButton("6");
		panel_1.add(radioButton_2);
		radioButton_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				 if (e.getStateChange() == ItemEvent.SELECTED) {
				       y=6;
				    }
			}
		});
		Faces.add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("8");
		panel_1.add(radioButton_3);
		radioButton_3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				 if (e.getStateChange() == ItemEvent.SELECTED) {
				       y=8;
				    }
			}
		});
		Faces.add(radioButton_3);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("12");
		panel_1.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				 if (e.getStateChange() == ItemEvent.SELECTED) {
				        y=12;
				    }
			}
		});
		Faces.add(rdbtnNewRadioButton_1);
	}
	public int[] returnxy(){
		int[] t= new int[2];
		System.out.println("in returnxy");
		t[0]=x;
		t[1]=y;
		
		return t;
	}
	
	
}
