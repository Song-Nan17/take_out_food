package service;

import model.Promotion;

public class LoadPromotions {
	public static Promotion loadMoneyOff() {
		Promotion moneyOff = new Promotion("满30减6元", new String[1]);
		return moneyOff;
	}

	public static Promotion loadHalfOff() {
		String[] items = {"ITEM0001", "ITEM0022"};
		Promotion halfOff = new Promotion("指定菜品半价", items);
		return halfOff;
	}
}
