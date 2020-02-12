package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;

public class LowQuantityProxy extends Proxy{
	
	protected Proxy next;
	
	public LowQuantityProxy() {
		
		next = new HighQuantityProxy();
		
	}

	/*
	 * LowQuantityProxy handles orders that have less than 10 items
	 * Otherwise, placeOrder request is passes to HighQuantityProxy
	 */
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) throws Throwable {
		if(NUMOFITEMS < 10) {
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

	
}
