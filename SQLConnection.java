/**
 * File Name: SQLConnection.java
 * @author: Hemang Shimpi
 * @since: April 25th, 2021
 * @version: 1.0
 * Last Modified: May 9th, 2021 
 */

package shimpi.two;

// importing java sql, date, and swing as needed
import java.util.Date;

import java.sql.*;

import javax.swing.JOptionPane;

public abstract class SQLConnection implements Validation {
	
	// declaring class fields 
	private final int sessionID;
	private final String appName;
	private final String currentTime;
	
	// only one constructor because fields are final and cannot be changed
	public SQLConnection() {
		super();
		sessionID = 98921; // made up session ID 
		Date date = new Date(System.currentTimeMillis());
		currentTime = (date.toString());
		appName = "ATM";
	}
	
	// getters and setters section 
	public int getSessionID() {
		return sessionID;
	}
	
	public String getAppName() {
		return appName;
	}
	
	public String getCurrentTime() {
		return currentTime;
	}
		
	
	// toString method
	@Override
	public String toString() {
		
		String s = ("App Name: " + this.getAppName() + "\n" 
			  + "Session ID: " + this.getSessionID() + "\n"
			  + "Time App Ran: " + this.getCurrentTime() + "\n");
		
		return s;
	}
	
	// abstract methods with no body because not needed in this class
	public abstract String validate(String username, String password);
	public abstract String register(String name, String email, int age, String username, String password);
	
	
	
	
}