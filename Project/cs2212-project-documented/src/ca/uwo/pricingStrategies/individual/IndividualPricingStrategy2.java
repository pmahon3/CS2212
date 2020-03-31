package ca.uwo.pricingStrategies.individual;

public class IndividualPricingStrategy2 implements IndividualPricingStrategy {
	public double calculate(int quantity, double price) {
		double discount = quantity * price * 0.10;
		return (quantity * price) - discount;
	}
}
