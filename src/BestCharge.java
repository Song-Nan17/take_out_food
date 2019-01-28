public class BestCharge {
	public static void main(String[] args) {
		final Item[] ITEMS = LoadInfo.loadAllItems();
		printMenu(ITEMS);
	}

	public static void printMenu(Item[] items) {
		String menu = "============== 菜  单 ==============\n" + "菜品\t\t\t\t\t\t\t价格/元\n";
		for (Item k : items) {
			menu += k.getName() + "\t\t\t\t\t\t\t" + String.format("%.2f", k.getPrice()) + "\n";
		}
		System.out.println(menu);
		System.out.print("请点单(输入样例: 菜品*数量，菜品*数量), 输入完成请回车：");
	}
}
