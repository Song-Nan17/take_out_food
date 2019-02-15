package model;

public class Bill {
	private Order[] orders;
	private Promotion promotion;
	private double originalTotalPrice;
	private double totalPrice;

	public Bill(Order[] orders, Promotion promotion, double originalTotalPrice, double totalPrice) {
		this.orders = orders;
		this.promotion = promotion;
		this.originalTotalPrice = originalTotalPrice;
		this.totalPrice = totalPrice;
	}

	public Order[] getOrders() {
		return this.orders;
	}

	public void setOrders(Order[] orders) {
		this.orders = orders;
	}

	public Promotion getPromotion() {
		return this.promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public double getOriginalTotalPrice() {
		return this.originalTotalPrice;
	}

	public void setOriginalTotalPrice(double originalTotalPrice) {
		this.originalTotalPrice = originalTotalPrice;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
