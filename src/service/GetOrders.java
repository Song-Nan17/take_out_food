package service;

import model.Item;
import model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static service.GetOrder.getOrder;

public class GetOrders {

	public static Order[] getOrders(String input) {
		Item[] items = LoadItems.loadItems();
		String[] inputs = input.split("，");
		List<Order> orderList = new ArrayList();
		for (String str : inputs) {
			String[] strArr = str.split("\\*");
			String name = strArr[0];
			if (isInItems(name, items)) {
				int count = strArr.length > 1 && isInteger(strArr[1]) ? Integer.parseInt(strArr[1]) : 1;
				orderList.add(getOrder(name, count, items));
			}
		}
		Order[] orders = new Order[orderList.size()];
		orderList.toArray(orders);
		return orders;
	}

	public static boolean isInItems(String name, Item[] items) {
		boolean isInItems = false;
		for (Item item : items) {
			if (name.equals(item.getName())) {
				isInItems = true;
				break;
			}
		}
		return isInItems;
	}

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
}
