package entities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.OrderStatus;

public class Order {

	private Date 			moment;
	private OrderStatus 	status;
	private Client 			client;
	private List<OrderItem> orderList = new ArrayList<>(); 
	
	public Order() {
		
	}
	
	public Order(Date moment, OrderStatus status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}

	public Date getMoment() {
		return moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public Client getClient() {
		return client;
	}

	public List<OrderItem> getOrderList() {
		List<OrderItem> orderListCopy = orderList;
		return orderListCopy;
	}

	
	public void addItem(OrderItem order) {
		orderList.add(order);
	}
	
	public void removeItem(OrderItem order) {
		orderList.remove(order);
	}
	
	public double total() {
		double total = 0;
		for (OrderItem order : orderList) {
			total += order.subTotal();
		}
		return total;
	}
}
