/**
 * File Name: TransferWindow.java
 * @author: Hemang Shimpi
 * @since: April 25th, 2021
 * @version: 1.0
 * Last Modified: May 9th, 2021 
 */

package shimpi.two;

// importing java awt, swing, and java mail api as needed
import java.awt.BorderLayout;



import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JTextField;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;


public class TransferWindow extends JFrame {

	/* automatically declared by Eclipse WindowBuilder
	 * Java awt and swing elements variable declarations 
	*/
	private static final long serialVersionUID = 1L; // added automatically by eclipse
	private JPanel contentPane;
	private JTextField amount_text;
	private JTextField receiver_text;
	private int code;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferWindow frame = new TransferWindow();
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
	public TransferWindow() {
		
		// modifying frame functions here
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 329); // location
		contentPane = new JPanel(); // panel
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // border
		setContentPane(contentPane); // contentPane
		contentPane.setLayout(null); // absolute layout
		setLocationRelativeTo(null); // center frame on window
		
		// JLabel declaration and configurations
		JLabel title_lbl = new JLabel("TRANSFER MONEY");
		title_lbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		title_lbl.setBounds(115, 26, 207, 29);
		contentPane.add(title_lbl);
		
		// JLabel declaration and configurations
		JLabel amount_lbl = new JLabel("ENTER AMOUNT: ");
		amount_lbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		amount_lbl.setBounds(75, 95, 147, 29);
		contentPane.add(amount_lbl);
		
		// JLabel declaration and configurations
		JLabel email_lbl = new JLabel("ENTER RECIEVER'S EMAIL: ");
		email_lbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		email_lbl.setBounds(30, 149, 215, 29);
		contentPane.add(email_lbl);
		
		// JTextField declaration and configurations
		amount_text = new JTextField();
		amount_text.setBounds(244, 99, 141, 23);
		contentPane.add(amount_text);
		amount_text.setColumns(10);
		
		// JTextField declaration and configurations
		receiver_text = new JTextField();
		receiver_text.setBounds(244, 153, 141, 23);
		contentPane.add(receiver_text);
		receiver_text.setColumns(10);
		
		// JButton declaration and configurations
		JButton proceed_btn = new JButton("PROCEED");
		proceed_btn.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {		// button actionListener
				
				// java mail declarations
				Properties properties = new Properties();
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.host", "smtp.gmail.com");
				properties.put("mail.smtp.port", "587");
				
				SecurityManager security = System.getSecurityManager();
								
				String email = "XXXXXXXXXX@gmail.com"; // hidden because of personal information
				String passwd = "XXXXXXXXXX"; // hidden because of personal information
				String toEmail = receiver_text.getText();
				
				
				Session session = Session.getInstance(properties, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(email, passwd);
					}
				});
				
				// try and catch for MessagingException
		        try {
		        	
		        	// sending verification code and verifying 
		        	code = (int) (Math.random()*9999);
		        	Message msg = new MimeMessage(session);
					msg.setFrom(new InternetAddress(email));  
			        msg.addRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));  
			        msg.setSubject("Verify"); 
					msg.setText("Hello, This is a verification email." + "\n" + "Please enter this code in the app when asked: " + code);
					Transport.send(msg);
					int input = Integer.parseInt(JOptionPane.showInputDialog("Please enter verification code sent to email: "));
					
					// conditional to verify code
					if (input == code) {
						
						Information inform = new Information();
						inform.userBalance(WelcomePage.username, WelcomePage.password);
						String balance = inform.balance;
						double newBalance = Math.round(Double.parseDouble(balance));
						int amountText = Integer.parseInt(amount_text.getText());
					
						
						// checking using conditional 
						if (newBalance > amountText) {
						
							double updatedBalance = newBalance - amountText;
							
							WelcomePage welcome = new WelcomePage();
							String name = WelcomePage.username; 
							String user = "XXXXXXXXXXXXXXXXX"; // hidden because of personal information
							String pass = "XXXXXXXXXXXXXXXXX"; // hidden because of personal information
							String sqlQuery = ("update userinformation set balance =" + "'"+updatedBalance+"'" + "where person_name =" + "'"+name+"'");
							String sqlQuery2 = ("update userinformation set balance =" + "'"+amount_text.getText()+"'" + "where person_email =" + "'"+toEmail+"'");
							String url = "jdbc:mysql://localhost:3306/accounts?useSSL=true";
							
							// try and catch for ClassNotFoundException
							try {
								
								Class.forName("com.mysql.cj.jdbc.Driver");
								
							} catch (ClassNotFoundException e1) {
								
								System.out.println("An error occured");
								
							}
							
							// try and catch for SQLException
							try {
								
								Connection connection = DriverManager.getConnection(url, user, pass);
								Statement statement = connection.createStatement();
								statement.executeUpdate(sqlQuery);
								
								Message msg2 = new MimeMessage(session);
								msg2.setFrom(new InternetAddress(email));  
						        msg2.addRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));  
						        msg2.setSubject("Verify"); 
								msg2.setText("Hello, " + WelcomePage.username + " sent you some money!" + "\n" + "Money Transferred: " + amount_text.getText());
								Transport.send(msg2);
								
								JOptionPane.showMessageDialog(null, "MONEY TRANSFER SUCCESSFUL", "TRANSFER SUCCESS", JOptionPane.INFORMATION_MESSAGE);
								JOptionPane.showMessageDialog(null, ("NEW BALANCE: " + updatedBalance), "UPDATED BALANCE", JOptionPane.INFORMATION_MESSAGE);
								dispose();
								ATM atm2 = new ATM();
								atm2.setVisible(true);
														
								connection.close();
								
								
							} catch (SQLException s) {
								
								System.out.println("An error occured");
								System.out.println(s.getMessage());
								
							}
					   
							
												
						} else {
							
							JOptionPane.showMessageDialog(null, ("YOU DON'T HAVE THAT MUCH MONEY IN YOUR ACCOUNT\nYOUR BALANCE: " + balance), "MONEY TRANSFER UNSUCCESSFULL", JOptionPane.ERROR_MESSAGE);
						
						}
					} else {
						JOptionPane.showMessageDialog(null, "MONEY TRANFER UNSUCCESSFULL", "MONEY TRANSFER UNSUCCESSFULL", JOptionPane.ERROR_MESSAGE);
					}
				
					
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
		        
		     
		         
			}
		});
		proceed_btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		proceed_btn.setBounds(235, 209, 106, 35);
		contentPane.add(proceed_btn);
		
		// JButton declaration and configurations
		JButton back_btn = new JButton("GO BACK ");
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 		// button actionListener
				
				// using dispose to destroy window
				dispose();
				
				// ATM declaration and run
				ATM atm = new ATM();
				atm.setVisible(true);
			}
		});
		back_btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		back_btn.setBounds(99, 209, 106, 35);
		contentPane.add(back_btn);
	}
	
	

}
