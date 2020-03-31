package ca.uwo.viewer.restock.strategies;

/**
 * This class will create different RestockStrategies depending on the parameter passed to
 * the create method 
 */
public class RestockStrategyFactory {

	public static RestockStrategy create(String strategyChoice) {
		if(strategyChoice.equals("RestockStrategy1")) {
			return new RestockStrategy1();
		}
		else if(strategyChoice.equals("RestockStrategy2")) {
			return new RestockStrategy2();
		}
		else {
			return null;
		}
		
	}
}
