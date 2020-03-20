package ca.uwo.model.item.states;

//This class is responsible for creating different states (using Factory design pattern)

public class ItemStateFactory {
	public static ItemState create(int quantity) {
		if (quantity > 10) {
			return new InStockState();
		}
		else if (quantity < 10 && quantity > 0) {
			return new LowStockState();
		}
		else {
			return new OutOfStockState();
		}
	}
}
