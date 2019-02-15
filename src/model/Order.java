package model;

public class Order extends Item {
	private int count;
	private double totalPrice;

	public Order(String id, String name, int count, double unitPrice) {
		super(id, name, unitPrice);
		this.count = count;
		this.totalPrice = this.computeTotalPrice();
	}

	public double computeTotalPrice() {
		return count * getUnitPrice();
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
