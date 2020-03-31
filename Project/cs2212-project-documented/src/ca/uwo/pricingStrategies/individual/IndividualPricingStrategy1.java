package ca.uwo.pricingStrategies.individual;

public class IndividualPricingStrategy1 implements IndividualPricingStrategy {
	public double calculate(int quantity, double price) {
		return price * quantity;
	}
}
