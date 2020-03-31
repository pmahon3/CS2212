package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class SupplierProxy extends Proxy{

	private static SupplierProxy instance = null;
	
	protected Proxy next;
	
	private SupplierProxy(){
		next = LowQuantityProxy.getInstance();
	}
	
	public static SupplierProxy getInstance() {
		if ( instance == null) instance = new SupplierProxy();
		return instance;
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
