import com.sun.deploy.util.StringUtils;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BestCharge {
	public static void main(String[] args) {
		String[] arr = {"asd", "asd", null};
		String str = String.join("，", arr);
		System.out.println(str);
		final Item[] ITEMS = LoadInfo.loadAllItems();
		while (true) {
			printMenu(ITEMS);
			Scanner in = new Scanner(System.in);
			Order[] orders = getOrders(in.nextLine(), ITEMS);
			System.out.println(orders.length);
			for (Order k : orders) {
				System.out.println(k);
			}
			if (orders.length == 0) {
				System.out.println("订单无效");
				continue;
			}
//			double totalPriceWithNoPromotion = computeTotalPriceWithNoPromotion(orders);
//			System.out.println(totalPriceWithNoPromotion);
//			test
//			System.out.println(orders.length);
//			for (Order k : orders) {
//				if (k == null) {
//					continue;
//				}
//				System.out.println("id:" + k.getId() + ",name:" + k.getName() + ",quantity:" + k.getQuantity() + ", unitPrice:" + k.getUnitPrice() + ", totalPrice:" + k.getTotalPrice());
//			}
		}
	}

	public static void printMenu(Item[] items) {
		String menu = "============== 菜  单 ==============\n" + "菜品\t\t\t\t\t\t\t价格/元\n";
		for (Item k : items) {
			menu += k.getName() + "\t\t\t\t\t\t\t" + String.format("%.2f", k.getPrice()) + "\n";
		}
		System.out.println(menu);
		System.out.print("请点单(输入样例: 菜品*数量，菜品*数量), 输入完成请回车：");
	}

	public static Order[] getOrders(String input, Item[] items) {
		String[] inputs = input.split("，");
		int orderNum = 0;
		Order[] orders = new Order[inputs.length];
		for (String str : inputs) {
			String[] arr = str.split("\\*");
			String name = arr[0];
			if (isInItems(name, items)) {
				int quantity = arr.length > 1 && isInteger(arr[1]) ? Integer.parseInt(arr[1]) : 1;
				try {
					orders[orderNum] = getOrder(name, quantity, items);
					orderNum++;
//					System.out.println("orderNum:" + orderNum + ",数组长度：" + orders.length);
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

	public static Order getOrder(String name, int quantity, Item[] items) {
		Order order = new Order("", name, quantity, 0.00);
		for (Item item : items) {
			if (name.equals(item.getName())) {
				String id = item.getId();
				double unitPrice = item.getPrice();
				order = new Order(id, name, quantity, unitPrice);
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
		return moneyOff;
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
//	public static Promotion computeHalfOff(Order[] orders, )

}

