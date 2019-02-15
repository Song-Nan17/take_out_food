package service;

import model.Order;
import model.Promotion;

public class ChoosePromotion {

	public static Promotion choosePromotion(Order[] orders, double originalTotalPrice) {
		Promotion moneyOff = Compute.computeMoneyOff(originalTotalPrice, LoadPromotions.loadMoneyOff());
		Promotion halfOff = Compute.computeHalfOff(orders, LoadPromotions.loadHalfOff());
		return comparePromotions(moneyOff, halfOff);
	}

	public static Promotion comparePromotions(Promotion moneyOff, Promotion halfOff) {
		Promotion promotion = moneyOff;
		if (moneyOff.getDiscount() < halfOff.getDiscount()) {
			promotion = halfOff;
		}
		return promotion;
	}
}
