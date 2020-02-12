package ca.uwo.proxies;

import java.util.Map;
import java.util.Scanner;

import javax.naming.AuthenticationException;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

/**
 * @author kkontog, ktsiouni, mgrigori
 * This is one concrete implementation of {@link ca.uwo.proxies.Proxy} base class, it is the first proxy
 * the {@link ca.uwo.client.Client} will encounter. If the request of client is not issued by this class, 
 * it is forwarded to the {@link ca.uwo.proxies.SupplierProxy}, then {@link ca.uwo.proxies.LowQuantityProxy}, 
 * lastly {@link ca.uwo.proxies.HighQuantityProxy}. The link between those proxies implements Chain of Responsibility 
 * design pattern.
 */
public class WelcomeProxy extends Proxy {
	
	protected Proxy next;
	private Scanner sc = new Scanner(System.in);
	
	/* (non-Javadoc)
	 * constructor for WelcomeProxy class.
	 */
	
	public WelcomeProxy() {
		next = new SupplierProxy();
	}

	/* (non-Javadoc)
	 * @see ca.uwo.frontend.interfaces.FacadeCommands#placeOrder(java.util.Map, ca.uwo.client.Buyer)
	 */
	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) throws Throwable{
		
		if (this.authorize(buyer)) {
			sc.close();
			next.placeOrder(orderDetails, buyer);
		}
		else {
			System.out.println("Order rejected.");
			System.out.println("Would you like to contact an agent?");
			System.out.println("Enter 1 if yes.");
			System.out.println("Enter 2 if no.");
			String agent = sc.nextLine();
			
			switch(agent) {
			
				case "1":
					
					System.out.println("Calling agent...");
					
				case "2":
					
					System.out.println("Thank you. Please contact if you require further assistance.");
					
				default:
					
					
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see ca.uwo.frontend.interfaces.FacadeCommands#restock(java.util.Map, ca.uwo.client.Supplier)
	 */
	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		Facade facade = new Facade();
		facade.restock(restockDetails, supplier);
	}
	
	
	/* (non-Javadoc)
	 * 
	 */
	private boolean authorize(Buyer buyer) throws AuthenticationException {
		
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
			
					if ( uName.equals(buyer.getUserName()) && pwd.equals(buyer.getPassword())) return true;
					System.out.println("Invalid username or password.");
				}
				throw new AuthenticationException("Invalid username or password. Maximum attempts exceeded.");
				
			case "2":
				
				for ( int i = 3 ; i > 0 ; i-- ) {
					
					System.out.println( Integer.toString(i) + " attempts remaining." );
					
					System.out.print("Please enter your PIN:");
					String pin = sc.nextLine();
					
					
					
					if (  pin.equals(buyer.getPassword()) ) return true;
					
					System.out.println("Invalid PIN.");
				}
				throw new AuthenticationException("Invalid PIN. Maximum attempts exceeded.");
				
			default:
				
				this.authorize(buyer);
		}
		
		throw new AuthenticationException();
		
	}
}
