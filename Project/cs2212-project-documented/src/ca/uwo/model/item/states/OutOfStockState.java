package ca.uwo.model.item.states;

import ca.uwo.model.Item;
import ca.uwo.utils.ItemResult;
import ca.uwo.utils.ResponseCode;

public class OutOfStockState implements ItemState {

	@Override
	public ItemResult deplete(Item item, int quantity) {
		if ( quantity == item.getAvailableQuantity()) {
			item.deplete(quantity);
			item.notifyViewer();
			return new ItemResult("Out Of Stock", ResponseCode.Completed);
		}

		item.deplete(item.getAvailableQuantity());
		item.notifyViewer();
		return new ItemResult("Out Of Stock", ResponseCode.Partially_Completed);
	}

	@Override
	public ItemResult replenish(Item item, int quantity) {
		item.replenish(quantity);
		return new ItemResult("Out Of Stock", ResponseCode.Completed);
	}

}
