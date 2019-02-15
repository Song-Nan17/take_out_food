package service;

import model.Item;
import model.Order;

public class GetOrder {

	public static Order getOrder(String name, int count, Item[] items) {
		Order order = new Order("", name, count, 0.00);
		for (Item item : items) {
			if (name.equals(item.getName())) {
				String id = item.getId();
				double unitPrice = item.getUnitPrice();
				order = new Order(id, name, count, unitPrice);
				break;
			}
		}
		return order;
	}
}
