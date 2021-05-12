/**
 * File Name: RegisterPage.java
 * @author: Hemang Shimpi
 * @since: April 21th, 2021
 * @version: 1.0
 * Last Modified: May 9th, 2021 
 */

package shimpi.two;

//imported necessary java awt, swing, file, and events
import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterPage extends JFrame {

	/* automatically declared by Eclipse WindowBuilder
	 * Java awt and swing elements variable declarations 
	*/
	private JPanel contentPane;
	private JTextField name_text;
	private JTextField username_text;
	private JTextField password_text;
	private JTextField email_text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPage frame = new RegisterPage();
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
	public RegisterPage() {
		
		// modifying frame functions here
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 442); // location
		contentPane = new JPanel(); // panel
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // border
		setContentPane(contentPane); // contentPane
		contentPane.setLayout(null); // absolute layout
		setLocationRelativeTo(null); // center frame on window
		
		// JLabel declaration and configurations
		JLabel title_lbl = new JLabel("REGISTER");
		title_lbl.setFont(new Font("Tahoma", Font.BOLD, 24));
		title_lbl.setBounds(144, 56, 131, 29);
		contentPane.add(title_lbl);
		
		// JLabel declaration and configurations
		JLabel name_lbl = new JLabel("Enter Name: ");
		name_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		name_lbl.setBounds(47, 115, 131, 32);
		contentPane.add(name_lbl);
		
		// JLabel declaration and configurations
		JLabel username_lbl = new JLabel("Set Username: ");
		username_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		username_lbl.setBounds(47, 232, 142, 33);
		contentPane.add(username_lbl);
		
		// JLabel declaration and configurations
		JLabel password_lbl = new JLabel("Set Password: ");
		password_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		password_lbl.setBounds(47, 276, 131, 30);
		contentPane.add(password_lbl);
		
		// JLabel declaration and configurations
		JLabel age_lbl = new JLabel("Enter Age:");
		age_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		age_lbl.setBounds(47, 195, 131, 26);
		contentPane.add(age_lbl);
		
		// JTextField declaration and configurations
		name_text = new JTextField();
		name_text.setBounds(224, 121, 131, 29);
		contentPane.add(name_text);
		name_text.setColumns(10);
		
		// JTextField declaration and configurations
		username_text = new JTextField();
		username_text.setBounds(224, 237, 131, 30);
		contentPane.add(username_text);
		username_text.setColumns(10);
		
		// JSpinner declaration and configurations
		JSpinner age_spinner = new JSpinner(new SpinnerNumberModel(1,1,100,1)); // setting min and max values so age > 1
		age_spinner.setBounds(224, 198, 131, 29);
		age_spinner.setEditor(new JSpinner.DefaultEditor(age_spinner));
		contentPane.add(age_spinner);
		
		// JTextField declaration and configurations
		password_text = new JTextField();
		password_text.setBounds(224, 280, 131, 30);
		contentPane.add(password_text);
		password_text.setColumns(10);
		
		// JButton declaration and configurations
		JButton register_btn = new JButton("PROCEED");
		register_btn.addActionListener(new ActionListener() { // button actionListener
			public void actionPerformed(ActionEvent e) {
				
				// setting variables to textField and spinner values
				String name = name_text.getText().toString();
				int age = (int) age_spinner.getValue();
				String username = username_text.getText().toString();
                String password = password_text.getText().toString();
                String email = email_text.getText().toString();


                // regex key for email validation
                String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

                // conditional for email and age verification
                if (name.isEmpty() == true || username.isEmpty() == true || password.isEmpty() == true || email.isEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "All entries are mandatory", "Blank Entry error", JOptionPane.ERROR_MESSAGE);
                } else if ((int) age_spinner.getValue() < 18) {
                    JOptionPane.showMessageDialog(null, "Sorry, you are not old enough to use the ATM\nProgram closing...", "Error", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        System.out.println("Program closed because person using the ATM isn't old enough (age < 18)");
                        Thread.sleep(500);
                        System.exit(0);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                } else if (!email.matches(regex)) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid email address", "Invalid Email Entered", JOptionPane.ERROR_MESSAGE);
                }
                
                // using interface declaration here to register new user
                Validation register = new UserRegistration();
                String result = register.register(name, email, age, username, password); // subclass method used
				System.out.println(result);
				System.out.println(register.toString()); // subclass method used
				System.out.println("----------------------------------------");
				
				// conditional for result variable
				if (result.equals("-----USER REGISTRATION SUCCESSFUL-----\n")) {
					
					// using dispose to destroy window
					dispose();
					
					// WelcomePage declaration and run
					WelcomePage welcomeAgain = new WelcomePage();
					welcomeAgain.setVisible(true);
				}
				
			}
		});
		register_btn.setFont(new Font("Tahoma", Font.BOLD, 15));
		register_btn.setBounds(224, 337, 116, 32);
		contentPane.add(register_btn);
		
		// JButton declaration and configurations
		JButton back_btn = new JButton("GO BACK");
		back_btn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { // button actionListener
				
				// using dispose to destroy window
				dispose();
				
				// WelcomePage declaration and run
				WelcomePage welcome = new WelcomePage();
				welcome.setVisible(true);
			}
		});
		back_btn.setFont(new Font("Tahoma", Font.BOLD, 15));
		back_btn.setBounds(73, 337, 116, 32);
		contentPane.add(back_btn); 
		
		// JLabel declaration and configurations
		JLabel email_lbl = new JLabel("Enter Email: ");
		email_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		email_lbl.setBounds(47, 158, 131, 26);
		contentPane.add(email_lbl);
		
		// JTextField declaration and configurations
		email_text = new JTextField();
		email_text.setColumns(10);
		email_text.setBounds(224, 157, 131, 30);
		contentPane.add(email_text);
	}
}
