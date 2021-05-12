/**
 * File Name: DepositWindow.java
 * @author: Hemang Shimpi
 * @since: April 25th, 2021
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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class DepositWindow extends JFrame {
	
	/* automatically declared by Eclipse WindowBuilder
	 * Java awt and swing elements variable declarations 
	*/
	
	private JPanel contentPane;
	private JTextField balance_text;
	private JTextField amount_text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepositWindow frame = new DepositWindow();
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
	public DepositWindow() {
		
		// modifying frame functions here
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 328); // location
		contentPane = new JPanel(); // panel
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // border
		setContentPane(contentPane); // contentPane
		contentPane.setLayout(null); // absolute layout
		setLocationRelativeTo(null); // center frame on window
		
		// JLabel declaration and configurations
		JLabel title_lbl = new JLabel("DEPOSIT MONEY");
		title_lbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		title_lbl.setBounds(118, 26, 181, 29);
		contentPane.add(title_lbl);
		
		// JLabel declaration and configurations
		JLabel balance_lbl = new JLabel("YOUR BALANCE: ");
		balance_lbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		balance_lbl.setBounds(68, 103, 133, 29);
		contentPane.add(balance_lbl);
		
		// JLabel declaration and configurations
		JLabel amount_lbl = new JLabel("ENTER AMOUNT: ");
		amount_lbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		amount_lbl.setBounds(68, 164, 137, 28);
		contentPane.add(amount_lbl);
		
		// JTextField declaration and configurations
		balance_text = new JTextField();
		balance_text.setBounds(213, 103, 110, 29);
		contentPane.add(balance_text);
		balance_text.setColumns(10);
		
		// JTextField declaration and configurations
		amount_text = new JTextField();
		amount_text.setBounds(215, 163, 108, 29);
		contentPane.add(amount_text);
		amount_text.setColumns(10);
		
		// Information declaration and run
		Information information = new Information();
		information.userBalance(WelcomePage.username, WelcomePage.password); // class method used with static fields parameters
		String balance = String.valueOf(information.balance);
		balance_text.setText(balance);
		balance_text.setEditable(false);
		
		// JButton declaration and configurations
		JButton proceed_btn = new JButton("PROCEED");
		proceed_btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {		// button actionListener
				
				// using wrapper classes to declare variables here
				Integer amount = Integer.parseInt(amount_text.getText());
				Double newBalance = Double.parseDouble(balance);
				
				// conditional here for amount
				if (amount >= 1) {
					
					double updatedBalance = newBalance + amount;
					WelcomePage welcome = new WelcomePage();
					String name = WelcomePage.username; // used WelcomePage static field here
					String user = "XXXXXXXXXXX"; // hidden because of personal information
					String pass = "XXXXXXXXXXX"; // hidden because of personal information
					String sqlQuery = ("update userinformation set balance =" + "'"+updatedBalance+"'" + "where person_name =" + "'"+name+"'");
					String url = "jdbc:mysql://localhost:3306/accounts?useSSL=true";
					
					// try and catch for ClassNotFoundException 
					try {
						
						Class.forName("com.mysql.cj.jdbc.Driver");
						
					} catch (ClassNotFoundException e1) {
						
						System.out.println("An error occured");
						
					}
					
					// try and catch for SQLException here
					try {
						
						// SQL connection and statements
						Connection connection = DriverManager.getConnection(url, user, pass);
						Statement statement = connection.createStatement();
						statement.executeUpdate(sqlQuery);
						
						// java swing message dialog
						JOptionPane.showMessageDialog(null, "DEPOSIT SUCCESSFUL", "DEPOSIT SUCCESS", JOptionPane.INFORMATION_MESSAGE);
						JOptionPane.showMessageDialog(null, ("NEW BALANCE: " + updatedBalance), "UPDATED BALANCE", JOptionPane.INFORMATION_MESSAGE);
						
						// using dispose to destroy window
						dispose();
						
						// ATM declaration and run
						ATM atm2 = new ATM();
						atm2.setVisible(true);
												
						connection.close();
						
						
					} catch (SQLException s) {
						
						System.out.println("An error occured");
						System.out.println(s.getMessage());
						
					} 
				} else {
					
					// java swing message dialog
					JOptionPane.showMessageDialog(null, "AMOUNT ENTERED IS BELOW 1\nMINIMUM VALUE IS 1", "NEGATIVE OR ZERO AMOUNT ENTERED", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		proceed_btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		proceed_btn.setBounds(213, 222, 110, 29);
		contentPane.add(proceed_btn);
		
		// JButton declaration and configurations
		JButton back_btn = new JButton("GO BACK");
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.setVisible(true);
			}
		});
		back_btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		back_btn.setBounds(91, 222, 110, 29);
		contentPane.add(back_btn);
	}

}
