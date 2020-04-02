package ca.uwo.pricingStrategies.individual;

/**
 * This class will create different IndividualPricingStrategies depending on the parameter passed to
 * the create method 
 */
public class IndividualPricingStrategyFactory {
	public static IndividualPricingStrategy create(String strategyChoice) {
		if(strategyChoice.equals("default")) {
			return new IndividualPricingStrategy1();
		}
		else{
			return new IndividualPricingStrategy2();
		}		
		
	}

}
