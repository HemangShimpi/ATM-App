/**
 * File Name: UserRegistration.java
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

public class UserRegistration extends SQLConnection {


	// abstract method left blank returning null because no need of this method in this class
	@Override
	public String validate(String username, String password) {
		return null;
	}

	// abstract method modified here
	@Override
	public String register(String name, String email, int age, String username, String password) {
		
		// used replaceAll method to remove all whitespace
		name = name.replaceAll("\\s", "");
		email = email.replaceAll("\\s", "");
		username = username.replaceAll("\\s", "");
		password = password.replaceAll("\\s", "");	
	
		String userRegistered = "";
		
		String user = "XXXXXXXX"; // hidden because of personal information
		String pass = "XXXXXXXX"; // hidden because of personal information
		String sqlQuery = ("insert into userinformation " +  "values ('"+name+"', '"+email+"', '"+age+"', '"+username+"', '"+password+"', balance)");
		String url = "jdbc:mysql://localhost:3306/accounts?useSSL=true";
		
		// try and catch for ClassNotFoundException
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("An error occured");
			
		}
		
		// try and catch for SQLException
		try {
			
			// SQL connection and statements
			Connection connection = DriverManager.getConnection(url, user, pass);
			Statement statement = connection.createStatement();
			statement.executeUpdate(sqlQuery);
			
			JOptionPane.showMessageDialog(null, "REGISTRATION SUCCESSFUL", "REGISTRATION SUCCESS", JOptionPane.INFORMATION_MESSAGE);
			userRegistered = "-----USER REGISTRATION SUCCESSFUL-----\n";
			
			
			connection.close();
			
			
		} catch (SQLException s) {
			
			System.out.println("An error occured");
			System.out.println(s.getMessage());
			
		}
		
		return userRegistered;
	};
	
}