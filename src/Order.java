public class Order {
	private String id, name;
	private int quantity;
	private double unitPrice, totalPrice;

	Order(String id, String name, int quantity, double unitPrice) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.totalPrice = computeTotalPrice();
	}

	public double computeTotalPrice() {
		return quantity * unitPrice;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
}
