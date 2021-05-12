/**
 * File Name: Validation.java
 * @author: Hemang Shimpi
 * @since: April 25th, 2021
 * @version: 1.0
 * Last Modified: May 9th, 2021 
 */

package shimpi.two;

public interface Validation {
	
	// abstract methods 
	public abstract String validate(String username, String password);
	public abstract String register(String name, String email, int age, String username, String password);

}
