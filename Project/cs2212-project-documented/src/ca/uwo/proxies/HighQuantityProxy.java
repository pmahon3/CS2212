package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;

public class HighQuantityProxy extends Proxy{
	
	protected Proxy next;
	
	public HighQuantityProxy() {
		
		next = null;
	}

	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) throws Throwable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		// TODO Auto-generated method stub
		
	}

}
