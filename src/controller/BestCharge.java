package controller;

import model.Bill;
import model.Order;
import service.ProcessOrders;
import tools.Input;
import tools.Print;

public class BestCharge {

	public static void main(String[] args) {
		Print.printMenu();
		Print.printInputHint();
		Order[] orders = ProcessOrders.generateOrders(Input.getInput());
		Bill bill = ProcessOrders.generateBill(orders);
		Print.printBill(bill);
		Print.printThanks();
	}
}
