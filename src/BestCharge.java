import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BestCharge {
	public static void main(String[] args) {

		final Item[] ITEMS = LoadInfo.loadAllItems();
		while (true) {
			printMenu(ITEMS);
			Scanner in = new Scanner(System.in);
			Order[] orders = getOrders(in.nextLine(), ITEMS);
			if (orders.length == 0) {
				System.out.println("订餐无效，请重新点餐：");
				continue;
			}
			double totalPriceWithNoPromotion = computeTotalPriceWithNoPromotion(orders);
			Promotion promotion = choosePromotion(orders, totalPriceWithNoPromotion);
			double totalPrice = computeTotalPrice(totalPriceWithNoPromotion, promotion);
			printBill(orders, promotion, totalPrice);
			System.out.println("订餐成功！\n可继续点餐：\n");
		}
	}

	public static void printMenu(Item[] items) {
		String menu = "============== 菜  单 ==============\n" + "菜品\t\t\t\t\t\t\t价格/元\n";
		for (Item k : items) {
			menu += k.getName() + "\t\t\t\t\t\t\t" + String.format("%.2f", k.getPrice())+"\n";
		}
		menu += "===================================";
		System.out.println(menu);
		System.out.print("请点餐(输入格式: 菜品*数量，菜品*数量), 输入完成请回车：");
	}

	public static Order[] getOrders(String input, Item[] items) {
		String[] inputs = input.split("，");
		int orderNum = 0;
		Order[] orders = new Order[inputs.length];
		for (String str : inputs) {
			String[] arr = str.split("\\*");
			String name = arr[0];
			if (isInItems(name, items)) {
				int count = arr.length > 1 && isInteger(arr[1]) ? Integer.parseInt(arr[1]) : 1;
				try {
					orders[orderNum] = getOrder(name, count, items);
					orderNum++;
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("getOrders中数组orders越界异常：" + e);
				}
			}
		}
		Order[] realOrders = deleteNull(orders);
		return realOrders;
	}

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
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

	public static Order getOrder(String name, int count, Item[] items) {
		Order order = new Order("", name, count, 0.00);
		for (Item item : items) {
			if (name.equals(item.getName())) {
				String id = item.getId();
				double unitPrice = item.getPrice();
				order = new Order(id, name, count, unitPrice);
				break;
			}
		}
		return order;
	}

	public static Order[] deleteNull(Order[] orders) {
		int isNotNull = 0;
		for (Order order : orders) {
			if (order != null) {
				isNotNull++;
			}
		}
		Order[] realOrders = new Order[isNotNull];
//		if (isNotNull != 0) {
		for (int i = 0, j = 0; i < orders.length; i++) {
			if (orders[i] != null) {
				realOrders[j] = orders[i];
				j++;
			}
		}
//		}
		return realOrders;
	}

	public static double computeTotalPriceWithNoPromotion(Order[] orders) {
		double totalPrice = 0.00;
		for (Order order : orders) {
			totalPrice += order.getTotalPrice();
		}
		return totalPrice;
	}

	public static Promotion choosePromotion(Order[] orders, double totalPriceWithNoPromotion) {
		Promotion[] promotions = LoadInfo.loadPromotions();
		Promotion moneyOff = computeMoneyOff(totalPriceWithNoPromotion, promotions[0]);
		Promotion halfOff = computeHalfOff(orders, promotions[1]);
		return comparePromotions(moneyOff, halfOff);
	}

	public static Promotion computeMoneyOff(double totalPriceWithNoPromotion, Promotion promotion) {
		Promotion moneyOff = promotion;
		double discount = 0;
		if (totalPriceWithNoPromotion >= 30) {
			discount = 6;
		}
		moneyOff.setDiscount(discount);
		return moneyOff;
	}

	public static Promotion computeHalfOff(Order[] orders, Promotion promotion) {
		Promotion halfOff = promotion;
		List<String> stringList = new ArrayList();
		double discount = 0;
		for (Order order : orders) {
			if (Arrays.asList(promotion.getItems()).contains(order.getId())) {
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

	public static Promotion comparePromotions(Promotion moneyOff, Promotion halfOff) {
		Promotion promotion = moneyOff;
		if (moneyOff.getDiscount() < halfOff.getDiscount()) {
			promotion = halfOff;
		}
		return promotion;
	}

	public static double computeTotalPrice(double totalPriceWithNoPromotion, Promotion promotion) {
		return totalPriceWithNoPromotion - promotion.getDiscount();
	}

	public static void printBill(Order[] orders, Promotion promotion, double totalPrice) {
		String itemsList = generateItemsList(orders);
		String promotionList = generatePromotionList(promotion);
		String bill = "============= 订餐明细 =============\n" + itemsList + "\n" + promotionList;
		bill += "\n" + "总计：" + String.format("%.2f", totalPrice) + "元";
		bill += "\n===================================";
		System.out.println("\n" + bill);

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
			promotionList += "-----------------------------------";
		}
		return promotionList;
	}
}

