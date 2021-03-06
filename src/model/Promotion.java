package model;

public class Promotion {
	private String type;
	private String[] items;
	private String[] discountItems;
	private double discount;

	public Promotion(String type, String[] items) {
		this.type = type;
		this.items = items;
	}

	public String getType() {
		return type;
	}

	public String[] getItems() {
		return items;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscountItems(String[] discountItems) {
		this.discountItems = discountItems;
	}

	public String[] getDiscountItems() {
		return discountItems;
	}
}
