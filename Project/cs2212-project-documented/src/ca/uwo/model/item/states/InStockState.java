package ca.uwo.model.item.states;

import ca.uwo.model.Item;
import ca.uwo.utils.ItemResult;
import ca.uwo.utils.ResponseCode;

import java.lang.Math;

public class InStockState implements ItemState {
	
	ItemState next = new LowStockState();

	@Override
	public ItemResult deplete(Item item, int quantity) {
		
		if ( ( item.getAvailableQuantity() - quantity < 10 )) {
			return next.deplete(item, quantity);
		}
		
		this.deplete(item, quantity);
		item.notifyViewer();
		return new ItemResult("In Stock", ResponseCode.Completed);
	}

	@Override
	public ItemResult replenish(Item item, int quantity) {
		
		if( (quantity + item.getAvailableQuantity()) > 10) {
			return new ItemResult("In Stock", ResponseCode.Completed);
		}
		
		return next.replenish(item, quantity);
	}

}
