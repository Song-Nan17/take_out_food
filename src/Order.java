public class Order {
	private String id, name;
	private int count;
	private double unitPrice, totalPrice;

	Order(String id, String name, int count, double unitPrice) {
		this.id = id;
		this.name = name;
		this.count = count;
		this.unitPrice = unitPrice;
		this.totalPrice = computeTotalPrice();
	}

	public double computeTotalPrice() {
		return count * unitPrice;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCount() {
		return count;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
}
