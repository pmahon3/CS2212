package ca.uwo.client;

import java.io.OutputStream;
import java.io.PrintStream;
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
		WelcomeProxy proxy = WelcomeProxy.getInstance();
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
	
	/**
	 *  authorize authenticates the buyer by username and password, 
	 *  PIN, or in special circumstances by calling an agent. 
	 *  Currently no backend agent representation is implemented and
	 *  PIN authorization just considers the password as PIN. 
	 *  switchStream statements are used to silence the output stream
	 *  originating from the StockManager thread.
	 *  @return true if authentication is valid, false otherwise.
	 */
	public boolean authorize() throws AuthenticationException {
		
		PrintStream sysOut = System.out;
		PrintStream dummy = new PrintStream(new OutputStream() {
			public void write(int b) {}
		});
		
	
		this.switchStream(sysOut, dummy, "How would you like to login:\n"
				+ "Enter 1 for username and password.\n"
				+ "Enter 2 for PIN.\n");

		
		String val = sc.nextLine();
		
		switch( val ) {
		
			case "1":
				
				for ( int i = 3 ; i > 0 ; i-- ) {
					
			
					this.switchStream( sysOut, dummy, Integer.toString(i) + " attempts remaining.\n"
							+ "Please enter your username:");
					String uName = sc.nextLine();
					this.switchStream(sysOut, dummy, "Please enter your password:\n");
					String pwd = sc.nextLine();
				
					
					if ( uName.equals(this.getUserName()) && pwd.equals(this.getPassword())) {
						System.setOut(sysOut);
						return true;
					}
					System.setOut(sysOut);
					System.out.println("Invalid username or password.");
				}
				System.setOut(sysOut);
				throw new AuthenticationException("Invalid username or password. Maximum attempts exceeded.");
				
			case "2":
				
				for ( int i = 3 ; i > 0 ; i-- ) {
					
					this.switchStream(sysOut, dummy, Integer.toString(i) + " attempts remaining.\n"
							+ "Please enter your PIN:");
					String pin = sc.nextLine();
					
					
					
					if (  pin.equals(this.getPassword()) ) {
						System.setOut(sysOut);
						return true;
					}
					
					this.switchStream(sysOut, dummy, "Invalid PIN.");
				}
				System.setOut(sysOut);
				throw new AuthenticationException("Invalid PIN. Maximum attempts exceeded.");
				
			case "agent":
				
				this.switchStream(sysOut, dummy, "Calling agent...");
				System.setOut(sysOut);
				return true;
				
			default:
				
				throw new AuthenticationException("Invalid login option.");
		}
	}
	
	/**
	 * switchStream is used to silence threads running print statements and print lines
	 * for user input. 
	 * @param sysOut System.out print stream.
	 * @param dummy a dummy print stream to silence output from sysOut.
	 * @param str the string to be printed.
	 */
	private void switchStream(PrintStream sysOut, PrintStream dummy, String str){
		System.setOut(sysOut);
		System.out.println(str);
		System.setOut(dummy);
	}
}
