package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;

public class SupplierProxy extends Proxy{
	
	protected Proxy next;
	
	public SupplierProxy(){
		next = new LowQuantityProxy();
	}

	//get the next object in the chain to handle a placeOrder request
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) throws Throwable {
		next.placeOrder(orderDetails, buyer);
		
	}

	//SupplierProxy can handle restock requests
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		Facade facade = new Facade();
		facade.restock(restockDetails, supplier);
		
	}

}
