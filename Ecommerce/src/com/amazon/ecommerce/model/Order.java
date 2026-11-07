package com.amazon.ecommerce.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order 
{
	private int orderId;
	private int customerId;
	private List<CartItem> items;
	private double totalAmount;
	private OrderStatus orderStatus;
	private LocalDateTime orderDate;
	
	public Order(int orderId, int customerId, List<CartItem> items, double totalAmount)
	{
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.items = new ArrayList<>(items);
		this.totalAmount = totalAmount;
		this.orderStatus = OrderStatus.PLACED;
		this.orderDate = LocalDateTime.now();
	}

	public int getOrderId() {
		return orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public double getTotalAmount() {
		return totalAmount;
	}
	
	public void setOrderStatus(OrderStatus orderStatus)
	{
		this.orderStatus=orderStatus;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	
	@Override
	public String toString()
	{
		return "OrderID : "+orderId+" ,CustumerID : "+customerId+" ,Items : "+items+
				" ,Total Amount : "+totalAmount+" ,OrderStatus : "+orderStatus+
				" ,Order Date : "+orderDate;
	}
	

}
