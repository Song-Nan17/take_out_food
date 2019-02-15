package tools;

import model.Bill;
import model.Item;
import model.Order;
import model.Promotion;
import service.LoadItems;

public class Print {

	public static void printMenu() {
		Item[] items = LoadItems.loadItems();
		int length = 15;
		String menu = "============== 菜  单 ==============\n";
		menu += "菜品" + Format.getBlanks(length - "菜单".length()) + "价格/元\n";
		for (Item k : items) {
			String blanks = Format.getBlanks(length - k.getName().length());
			menu += k.getName() + blanks + String.format("%.2f", k.getUnitPrice()) + "\n";
		}
		menu += "===================================";
		System.out.println(menu);
	}

	public static void printInputHint() {
		System.out.print("请点餐(输入格式: 菜品*数量，菜品*数量), 输入完成请回车：");
	}

	public static void promptInputError() {
		System.out.println("订餐无效，请重新点餐↓");
		System.out.print("输入格式: 菜品*数量，菜品*数量, 输入完成请回车：");
	}
	public static void printBill(Bill bill) {
		String itemsList = generateItemsList(bill.getOrders());
		String promotionList = generatePromotionList(bill.getPromotion());
		String billingDetails = "============= 订餐明细 =============\n" + itemsList + "\n" + promotionList;
		billingDetails += "\n" + "总计：" + String.format("%.2f", bill.getTotalPrice()) + "元";
		billingDetails += "\n===================================";
		System.out.println("\n" + billingDetails);
	}

	public static String generateItemsList(Order[] orders) {
		String[] itemsList = new String[orders.length];
		for (int i = 0; i < orders.length; i++) {
			itemsList[i] = orders[i].getName() + " x " + orders[i].getCount() + " = " + String.format("%.2f", orders[i].getTotalPrice()) + " 元";
		}
		return String.join("\n", itemsList);
	}

	public static String generatePromotionList(Promotion promotion) {
		String promotionList = "-----------------------------------";
		if (promotion.getDiscount() != 0) {
			promotionList += "\n使用优惠：";
			promotionList += "\n" + promotion.getType();
			promotionList += promotion.getType().equals("满30减6元") ? "，" : "(" + String.join("，", promotion.getDiscountItems()) + ")，";
			promotionList += "省" + String.format("%.2f", promotion.getDiscount()) + "元";
			promotionList += "\n-----------------------------------";
		}
		return promotionList;
	}

	public static void printThanks() {
		System.out.println("感谢惠顾，欢迎再来！");
	}
}
