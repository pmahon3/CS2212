package ca.uwo.model.item.states;

import ca.uwo.model.Item;
import ca.uwo.utils.ItemResult;
import ca.uwo.utils.ResponseCode;

public class LowStockState implements ItemState {
	
	ItemState next = new OutOfStockState();

	@Override
	public ItemResult deplete(Item item, int quantity) {
		
		if ( (item.getAvailableQuantity() - quantity ) <= 0 ) {
			return next.deplete(item, quantity);
		}
		
		item.deplete(quantity);
		item.notifyViewer();
		return new ItemResult("Low Stock", ResponseCode.Completed);
	}

	@Override
	public ItemResult replenish(Item item, int quantity) {
		if( (quantity + item.getAvailableQuantity()) > 0 ) {
			return new ItemResult("Low Stock", ResponseCode.Completed );
		}
		
		return next.replenish(item, quantity);
	}

}
