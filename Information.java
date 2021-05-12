/**
 * File Name: Information.java
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

public class Information {
	
	// public variable to be accessible even outside the class
	public String balance;
	
	// no constructor because if used, the balance becomes null every time a new object is created
	
	// balance method
	public void userBalance(String username, String password) {
		
		String name = username;
		String passwd = password;
						
		String user = "XXXXXXXXXXX"; // hidden because of personal information
		String pass = "XXXXXXXXXXX"; // hidden because of personal information
		String sqlQuery = ("select balance from userinformation where person_username='"+name+"' and person_password='"+passwd+"'");
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
			
			while (result.next()) {
				try {
					if (result.getString("balance").toString()!=null) {
						balance = result.getString("balance").toString();
					}
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "No money in account\nPlease use the deposit option to deposit money", "NO MONEY IN ACCOUNT", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			
			connection.close();
			
			
		} catch (SQLException s) {
			
			s.printStackTrace();
			
		}
		
		
	}
	
	
	
	
	 
}