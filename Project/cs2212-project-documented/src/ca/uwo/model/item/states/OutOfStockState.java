package ca.uwo.model.item.states;

import ca.uwo.model.Item;
import ca.uwo.utils.ItemResult;
import ca.uwo.utils.ResponseCode;

public class OutOfStockState implements ItemState {

	@Override
	/**
	 * deplete the item.
	 * 
	 * @param quantity
	 *            the quantity of item to deplete.
	 * @return execution result of the deplete action.
	 */
	public ItemResult deplete(Item item, int quantity) {
		
		
		int availableQuantity = item.getAvailableQuantity();
		ItemResult result;
		
		/*If the order is larger than the available quantity partially complete the order...
		* if the order is equal to the available quantity complete the order...
		* if the order is less than the available quantity complete the order.
		*/
		if( availableQuantity < quantity ) {
			availableQuantity = 0;
			result = new ItemResult("OUT OF STOCK", ResponseCode.Partially_Completed);
		}
		else if( availableQuantity == quantity) {
			availableQuantity = 0;
			result = new ItemResult("OUT OF STOCK", ResponseCode.Completed);
		}
		else {
			availableQuantity -= quantity;
			result = new ItemResult("AVAILABLE", ResponseCode.Completed);
		}
		item.setAvailableQuantity(availableQuantity);
		return result;
		
	}

	@Override
	/**
	 * replenish the item.
	 * 
	 * @param quantity
	 *            the quantity of item to replenish.
	 * @return execution result of the replenish action.
	 */
	public ItemResult replenish(Item item, int quantity) {
		
		/* If the restocked quantity is greater than 0, return "AVAIlABLE"...
		 * else return OUT OF STOCK.
		 */
		int availableQuantity = item.getAvailableQuantity() + quantity;
		ItemResult result;
		
		if (availableQuantity > 0) result = new ItemResult("AVAILABLE", ResponseCode.Completed);
		else result = new ItemResult("OUT OF STOCK", ResponseCode.Completed);
		
		item.setAvailableQuantity(availableQuantity);
		return result;
	}


}
