package ca.uwo.client;

import java.util.Map;
import java.util.Scanner;

import javax.naming.AuthenticationException;

import ca.uwo.proxies.WelcomeProxy;
import ca.uwo.utils.Invoice;

/**
 * @author kkontog, ktsiouni, mgrigori
 * One concrete implementation of {@link Client} class, who places orders on the stock.
 */
public class Buyer extends Client {
	
	private Scanner sc = new Scanner(System.in);
	private String userName;

	private String password;
	
	/**
	 * Constructor for Buyer class.
	 * 
	 * @param userName	the buyer's user name
	 * @param password	the buyer's password
	 */
	public Buyer(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	/**
	 * Buyer places the order.
	 * @param orderDetails  the name and quantity of each item in the order.
	 */
	public void buy(Map<String, Integer> orderDetails) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("My name is :" + this.userName + " and I'm buying : ");
		System.out.println(orderDetails);
		WelcomeProxy proxy = new WelcomeProxy();
		try {
			proxy.placeOrder(orderDetails, this);
		}
		catch (Throwable e){
			System.out.println(e);
		}
	}
	
	/**
	 * Buyer pays the order according to the invoice.
	 * @param invoice the invoice to pay.
	 */
	public void pay(Invoice invoice) {
		System.out.println(invoice.toString());
	}
	
	/**
	 * @return the user name of the Buyer.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the password of the Buyer.
	 */
	private String getPassword() {
		return password;
	}
	
	/* (non-Javadoc)
	 *  authorize authenticates the buyer by username and password, 
	 *  PIN, or in special circumstances by calling an agent. 
	 *  Currently no backend agent representation is implemented.
	 *  @return true if authentication is valid, false otherwise.
	 */
	public boolean authorize() throws AuthenticationException {
		
		System.out.println("How would you like to login:");
		System.out.println("Enter 1 for username and password.");
		System.out.println("Enter 2 for PIN.");
		
		String val = sc.nextLine();
		
		switch( val ) {
		
			case "1":
				
				for ( int i = 3 ; i > 0 ; i-- ) {
					
					System.out.println( Integer.toString(i) + " attempts remaining." );
					
					System.out.print("Please enter your username:");
					String uName = sc.nextLine();
					System.out.print("Please enter your password:");
					String pwd = sc.nextLine();
			
					if ( uName.equals(this.getUserName()) && pwd.equals(this.getPassword())) return true;
					System.out.println("Invalid username or password.");
				}
				throw new AuthenticationException("Invalid username or password. Maximum attempts exceeded.");
				
			case "2":
				
				for ( int i = 3 ; i > 0 ; i-- ) {
					
					System.out.println( Integer.toString(i) + " attempts remaining." );
					
					System.out.print("Please enter your PIN:");
					String pin = sc.nextLine();
					
					
					
					if (  pin.equals(this.getPassword()) ) return true;
					
					System.out.println("Invalid PIN.");
				}
				throw new AuthenticationException("Invalid PIN. Maximum attempts exceeded.");
				
			case "agent":
				
				System.out.println("Calling agent...");
				return true;
				
			default:
				
				this.authorize();
		}
		
		throw new AuthenticationException();
	}

}
