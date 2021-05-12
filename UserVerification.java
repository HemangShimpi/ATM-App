/**
 * File Name: UserVerification.java
 * @author: Hemang Shimpi
 * @since: April 25th, 2021
 * @version: 1.0
 * Last Modified: May 9th, 2021 
 */


package shimpi.two;

// imported java sql and swing as needed
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class UserVerification extends SQLConnection {
	
	
	// abstract method modified
	@Override
	public String validate(String username, String password) {
		
		// used replaceAll to remove all whitespace
		username = username.replaceAll("\\s", "");
		password = password.replaceAll("\\s", "");
	
		String userVerified = "";
		
		String user = "XXXXXXXXXXX"; // hidden because of personal information
		String pass = "XXXXXXXXXXX"; // hidden because of personal information 
		String sqlQuery = ("select * from userinformation where person_username='"+username+"' and person_password='"+password+"'");
		String url = "jdbc:mysql://localhost:3306/accounts?useSSL=true";
		
		// try and catch for ClassNotFoundException
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
		// try and catch for SQLException
		try {
			
			// SQL connection and statements
			Connection connection = DriverManager.getConnection(url, user, pass);
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sqlQuery);
			
			
			if (result.next() == true) {
				JOptionPane.showMessageDialog(null, "LOGIN SUCCESSFUL", "LOGIN SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				userVerified = "-----USER LOGIN SUCCESSFUL-----\n";
			} else {
				JOptionPane.showMessageDialog(null, "INVALID USERNAME AND PASSWORD", "LOGIN FAIL", JOptionPane.ERROR_MESSAGE);
				userVerified = "-----USER LOGIN UNSUCCESSFUL-----\n";
				
			}
			
			connection.close();
			
			
		} catch (SQLException s) {
			
			s.printStackTrace();
			
		}
		
		return userVerified;
	}

	// abstract method left blank returning null as it is not needed to be modified in this class
	@Override
	public String register(String name, String email, int age, String username, String password) {
		
		return null;
	}

	

	

	
}