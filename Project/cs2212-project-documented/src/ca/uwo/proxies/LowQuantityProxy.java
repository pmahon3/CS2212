package ca.uwo.proxies;

import java.util.Map;

import javax.naming.AuthenticationException;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class LowQuantityProxy extends Proxy{
	
	private static LowQuantityProxy instance;
	
	protected Proxy next;
	
	private LowQuantityProxy() {
		
		next = HighQuantityProxy.getInstance();
		
	}
	
	public static LowQuantityProxy getInstance() {
		if ( instance == null ) instance = new LowQuantityProxy();
		return instance;
	}

	/*
	 * LowQuantityProxy handles orders that have less than 10 items
	 * Otherwise, placeOrder request is passes to HighQuantityProxy
	 */
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) throws Throwable {
		
		int numItems = this.numItems(orderDetails);
		
		if(numItems < 10) {
			
			try {
				buyer.authorize();
			}
			catch( AuthenticationException e) {
				System.out.println(e);
				return;
			}
			Facade facade = new Facade();
			facade.placeOrder(orderDetails, buyer);
		}
		else {
			next.placeOrder(orderDetails, buyer);
		}
		
	}

	//LowQuantityProxy will not handle any restock requests
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		
	}
	
	private int numItems(Map<String, Integer> orderDetails) {
		Object[] arr = orderDetails.values().toArray();
		int total = 0;
		for ( int i = 0 ; i < arr.length ; i++ ) {
			total = total + (int)arr[i];
		}
		
		return total;
	}
	
}
