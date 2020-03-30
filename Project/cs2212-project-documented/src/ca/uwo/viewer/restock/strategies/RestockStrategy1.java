package ca.uwo.viewer.restock.strategies;

public class RestockStrategy1 implements RestockStrategy{
	public int calculateQuantity(String itemName, int quantity, double price) {
		return 50;
	}
}
