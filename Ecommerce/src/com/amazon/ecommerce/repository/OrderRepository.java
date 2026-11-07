package com.amazon.ecommerce.repository;

import java.util.ArrayList;
import java.util.List;


import com.amazon.ecommerce.model.Order;

public class OrderRepository 
{
	
	private List<Order> orders=new ArrayList<>();
	
	//saving Orders
	public void savingOrders(Order order)
	{
		orders.add(order);
	}
	
	//get order by id
	public Order orderById(int orderId)
	{
	
		for(Order order:orders)
		{
			if(order.getOrderId()==orderId)
			{
				return order;
			}
		}
	
	return null;
	
	}
	
	//viewing all orders
	public List<Order> getAllOrders()
	{
		return orders;
	}

}
