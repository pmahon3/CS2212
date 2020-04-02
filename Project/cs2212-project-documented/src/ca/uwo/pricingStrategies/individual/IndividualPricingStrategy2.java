package ca.uwo.pricingStrategies.individual;

public class IndividualPricingStrategy2 implements IndividualPricingStrategy {
	public double calculate(int quantity, double price) {
		double discount = quantity * price * 0.10;
		//adds 10% to small orders
		if(quantity < 10) {
			return (quantity * price) + discount;
		}
		//subtracts 10% to large orders
		else if (quantity > 100) {
			return (quantity * price) - discount;
		}
		else {
			return (quantity * price);
		}
	}
}
