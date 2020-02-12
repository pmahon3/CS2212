package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class HighQuantityProxy extends Proxy{
	
	protected Proxy next;
	
	//HighQuantityProxy is the last object in the chain of responsibility, so next is set to null
	public HighQuantityProxy() {
		
		next = null;
	}

	//Handles all orders with more than 10 items
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) throws Throwable {
		Facade facade = new Facade();
		facade.placeOrder(orderDetails, buyer);
		
	}

	//HighQuantityProxy will not handle any restock requests
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		
	}

}
