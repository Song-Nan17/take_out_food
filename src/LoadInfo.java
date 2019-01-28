public class LoadInfo {
    public static Item[] loadAllItems() {
        Item[] items = new Item[4];
        items[0] = new Item("ITEM0001", "黄焖鸡", 18.00);
        items[1] = new Item("ITEM0013", "肉夹馍", 6.00);
        items[2] = new Item("ITEM0022", "凉皮", 8.00);
        items[3] = new Item("ITEM0030", "冰峰", 2.00);
        return items;
    }

    public static Promotion[] loadPromotions() {
        Promotion[] promotions = new Promotion[2];
        promotions[0] = new Promotion("满30减6元", new String[1]);
        String[] items = {"ITEM0001", "ITEM0022"};
        promotions[1] = new Promotion("指定菜品半价", items);
        return promotions;
    }
}
