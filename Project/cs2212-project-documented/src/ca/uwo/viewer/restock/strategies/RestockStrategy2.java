package ca.uwo.viewer.restock.strategies;

public class RestockStrategy2 implements RestockStrategy{
	public int calculateQuantity(String itemName, int quantity, double price) {
		if (itemName.equals("apples")){
			return 100;
		}
		else {
			return 25;
		}
	}
}
