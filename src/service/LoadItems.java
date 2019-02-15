package service;

import model.Item;

public class LoadItems {
	public static Item[] loadItems() {
		Item[] items = new Item[4];
		items[0] = new Item("ITEM0001", "黄焖鸡", 18.00);
		items[1] = new Item("ITEM0013", "肉夹馍", 6.00);
		items[2] = new Item("ITEM0022", "凉皮", 8.00);
		items[3] = new Item("ITEM0030", "冰峰", 2.00);
		return items;
	}
}
