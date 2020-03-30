package ca.uwo.pricingStrategies.individual;

/**
 * This class will create different IndividualPricingStrategies depending on the parameter passed to
 * the create method 
 */
public class IndividualPricingStrategyFactory {
	public static IndividualPricingStrategy create(String strategyChoice) {
		if(strategyChoice.equals("IndividualPricingStrategy1")) {
			return new IndividualPricingStrategy1();
		}
		else if(strategyChoice.equals("IndividualPricingStrategy2")) {
			return new IndividualPricingStrategy2();
		}
		else {
			return null;
		}
		
		
	}

}
