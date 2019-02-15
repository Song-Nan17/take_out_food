package service;

import model.Bill;
import model.Order;
import model.Promotion;
import tools.Input;
import tools.Print;

public class ProcessOrders {

	public static Bill generateBill(Order[] orders) {
		double originalTotalPrice = Compute.computeOriginalTotalPrice(orders);
		Promotion promotion = ChoosePromotion.choosePromotion(orders, originalTotalPrice);
		double totalPrice = Compute.computeTotalPrice(originalTotalPrice, promotion);
		Bill bill = new Bill(orders, promotion, originalTotalPrice, totalPrice);
		return bill;
	}

	public static Order[] generateOrders(String input) {
		Order[] orders = GetOrders.getOrders(input);
		if (orders.length == 0) {
			Print.promptInputError();
			generateOrders(Input.getInput());
		}
		return orders;
	}
}
