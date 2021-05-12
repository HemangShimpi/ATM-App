/**
 * File Name: ATM.java
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
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ATM extends JFrame {

	/* automatically declared by Eclipse WindowBuilder
	 * Java awt and swing elements variable declarations 
	*/
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATM frame = new ATM();
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
	public ATM() {
		
		// modifying frame functions here
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 350); // location
		contentPane = new JPanel(); // panel
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // border
		setContentPane(contentPane); // contentPane 
		contentPane.setLayout(null); // absolute layout
		setLocationRelativeTo(null); // center frame on window
		
		// JLabel declaration and configurations
		JLabel title_lbl = new JLabel("CHOOSE ONE OF THE OPTIONS");
		title_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		title_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		title_lbl.setBounds(79, 50, 299, 44);
		contentPane.add(title_lbl);
		
		// JButton declaration and configurations
		JButton withdraw_btn = new JButton("WITHDRAW");
		withdraw_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 		// button actionListener
				
				// using dispose to destroy window
				dispose();
				
				// WithdrawWindow declaration and run 
				WithdrawWindow withdrawWindow = new WithdrawWindow();
				withdrawWindow.setVisible(true);
			}
		});
		withdraw_btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		withdraw_btn.setBounds(85, 119, 126, 44);
		contentPane.add(withdraw_btn);
		
		// JButton declaration and configurations
		JButton deposit_btn = new JButton("DEPOSIT");
		deposit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 		// button actionListener
				
				// using dispose to destroy window
				dispose();
				
				// DepositWindow declaration and run 
				DepositWindow depositWindow = new DepositWindow();
				depositWindow.setVisible(true);
				
			}
		});
		deposit_btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deposit_btn.setBounds(233, 119, 133, 44);
		contentPane.add(deposit_btn);
		
		// JButton declaration and configurations
		JButton transfer_btn = new JButton("TRANSFER");
		transfer_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		// button actionListener
				
				// using dispose to destroy window
				dispose();
				
				// TransferWindow declaration and run 
				TransferWindow transferWindow = new TransferWindow();
				transferWindow.setVisible(true);
			}
		});
		transfer_btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		transfer_btn.setBounds(83, 184, 128, 44);
		contentPane.add(transfer_btn);
		
		// JButton declaration and configurations
		JButton balance_btn = new JButton("BALANCE");
		balance_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		// button actionListener
				
				// Information declaration and run 
				Information inform = new Information();
				inform.userBalance(WelcomePage.username, WelcomePage.password); // class method with static fields' parameters
				
				// variable declaration for class field
				String balance = inform.balance;
				
				// java swing message dialog
				JOptionPane.showMessageDialog(null, ("YOUR BALANCE IS: " + balance), "BALANCE", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		balance_btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		balance_btn.setBounds(233, 184, 133, 44);
		contentPane.add(balance_btn);
		
		// JButton declaration and configurations
		JButton back_btn = new JButton("GO BACK");
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		// button actionListener
				
				// using dispose to destroy window
				dispose();
				
				// WelcomePage declaration and run 
				WelcomePage welcomeAgain = new WelcomePage();
				welcomeAgain.setVisible(true);
			}
		});
		back_btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		back_btn.setBounds(168, 249, 110, 34);
		contentPane.add(back_btn);
	}
}
