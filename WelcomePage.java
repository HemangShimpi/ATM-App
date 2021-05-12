/**
 * File Name: WelcomePage.java
 * @author: Hemang Shimpi
 * @since: April 20th, 2021
 * @version: 1.0
 * Last Modified: May 9th, 2021 
 */

package shimpi.two;

// imported necessary java awt, swing, file, and events
import java.awt.BorderLayout;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class WelcomePage extends JFrame {

	/* automatically declared by Eclipse WindowBuilder
	 * Java awt and swing elements variable declarations and static String fields
	*/
	private JPanel contentPane;
	public JFormattedTextField usernameField;
	public JPasswordField passwordField;
	public static String username;
	public static String password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {              
			public void run() {
				try {
					WelcomePage frame = new WelcomePage();
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
	public WelcomePage() {
		
		// modifying frame functions here
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 358); // location
		contentPane = new JPanel(); // panel
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // border
		setContentPane(contentPane); // contentPane
		contentPane.setLayout(null); // absolute layout 
		setLocationRelativeTo(null); // center frame on window
		
		// JLabel declaration and configurations
		JLabel title_lbl = new JLabel("Welcome to the ATM");
		title_lbl.setFont(new Font("Centaur", Font.BOLD, 35));
		title_lbl.setBounds(76, 33, 325, 49);
		contentPane.add(title_lbl);
		
		// JLabel declaration and configurations
		JLabel lblNewLabel = new JLabel("Enter Username: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(43, 115, 168, 36);
		contentPane.add(lblNewLabel);		
		
		// JLabel declaration and configurations
		JLabel password_lbl = new JLabel("Enter Password: ");
		password_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		password_lbl.setBounds(43, 180, 150, 36);
		contentPane.add(password_lbl);
		
		// JFormattedTextField declaration and configurations
		usernameField = new JFormattedTextField();
		usernameField.setBounds(233, 121, 168, 32);
		contentPane.add(usernameField);
		
		// JPasswordField declaration and configurations
		passwordField = new JPasswordField();
		passwordField.setBounds(233, 185, 168, 34);
		contentPane.add(passwordField);
		
		// JButton declaration and configurations
		JButton signUpBtn = new JButton("Sign Up");
		signUpBtn.addActionListener(new ActionListener() { // button actionListener
			public void actionPerformed(ActionEvent e) {
				
				// using dispose to destroy the window 
				dispose();
				
				// RegisterPage object declaration and run
				RegisterPage register = new RegisterPage();
				register.setVisible(true);
				
			}
		});
		signUpBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		signUpBtn.setBounds(84, 245, 127, 36);
		contentPane.add(signUpBtn);
		
		// JButton declaration and configurations
		JButton login_btn = new JButton("Log In");
		login_btn.addActionListener(new ActionListener() { // button actionListener
			public void actionPerformed(ActionEvent e) {
				
				// setting variables to textfield values
				username = usernameField.getText().toString();
				password = String.valueOf(passwordField.getPassword());
				
				// conditional for checking empty values
				if (username.isEmpty() == true || password.isEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "All entries are mandatory", "Blank Entry error", JOptionPane.ERROR_MESSAGE);
                }
				
				// using interface declaration here to verify username and password
				Validation sqlconnection = new UserVerification();
				String result = sqlconnection.validate(username, password); // subclass method used
				System.out.println(result);		
				System.out.println(sqlconnection.toString()); // subclass method toString used
				System.out.println("---------------------------------");
				
				// try-catch for writing to a file
				try {
					
					// File declaration and modification
					File file = new File("LoginAttemptsLog.txt");
					BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true));
					fileWriter.write(sqlconnection.toString() + "\n" + "---------------------------------");
					fileWriter.close();
					
				} catch (IOException e1) {
					System.out.println("An error occured while writing to file");
				}
				
				// conditional for result variable
				if (result.equals("-----USER LOGIN SUCCESSFUL-----\n")) {
					
					// using dispose to destroy window
					dispose();
					
					// ATM declaration and run
					ATM atm = new ATM();
					atm.setVisible(true);
				}
				
			}
		});
		login_btn.setFont(new Font("Tahoma", Font.BOLD, 15));
		login_btn.setBounds(244, 245, 127, 36);
		contentPane.add(login_btn);
	}
}
