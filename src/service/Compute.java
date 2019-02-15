package service;

import model.Order;
import model.Promotion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Compute {

	public static double computeOriginalTotalPrice(Order[] orders) {
		double totalPrice = 0.00;
		for (Order order : orders) {
			totalPrice += order.getTotalPrice();
		}
		return totalPrice;
	}

	public static Promotion computeMoneyOff(double originalTotalPrice, Promotion moneyOff) {
		double discount = 0;
		if (originalTotalPrice >= 30) {
			discount = 6;
		}
		moneyOff.setDiscount(discount);
		return moneyOff;
	}

	public static Promotion computeHalfOff(Order[] orders, Promotion halfOff) {
		List<String> stringList = new ArrayList();
		double discount = 0;
		for (Order order : orders) {
			if (Arrays.asList(halfOff.getItems()).contains(order.getId())) {
				discount += order.getTotalPrice() * 0.5;
				stringList.add(order.getName());
			}
		}
		halfOff.setDiscount(discount);
		String[] discountItems = new String[stringList.size()];
		stringList.toArray(discountItems);
		halfOff.setDiscountItems(discountItems);
		return halfOff;
	}

	public static double computeTotalPrice(double originalTotalPrice, Promotion promotion) {
		return originalTotalPrice - promotion.getDiscount();
	}
}
