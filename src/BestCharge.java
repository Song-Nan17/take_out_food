import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BestCharge {
	public static void main(String[] args) {
		final Item[] ITEMS = LoadInfo.loadAllItems();
		while (true) {
			printMenu(ITEMS);
			Scanner in = new Scanner(System.in);
			Order[] orders = getOrders(in.nextLine(), ITEMS);
//			test
			System.out.println(orders.length);
			for(Order k :orders) {
				if(k==null) {
					continue;
				}
				System.out.println("id:"+k.getId() +",name:" +k.getName() +",quantity:" +k.getQuantity() +", unitPrice:" +k.getUnitPrice()+", totalPrice:" +k.getTotalPrice());
			}
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
					System.out.println("orderNum:" +orderNum +",数组长度："+orders.length);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("getOrders中数组orders越界异常：" + e);
				}
			}
		}
		return orders;
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
		Order order = new Order("",name,quantity,0.00);
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

}

