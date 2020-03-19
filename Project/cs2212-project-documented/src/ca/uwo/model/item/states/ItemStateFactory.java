package ca.uwo.model.item.states;

//This class is responsible for creating different states (using Factory design pattern)

public class ItemStateFactory {
	public static ItemState create(String type) {
		switch(type) {
		case "In Stock":
			return new inStockState();
		"Low Stock":
			return new lowStockState();
		"Out of Stock":
			return new outOfStockState();
		}
	}
}
