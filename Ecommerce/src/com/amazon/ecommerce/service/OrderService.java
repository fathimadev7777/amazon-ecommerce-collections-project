package com.amazon.ecommerce.service;

import com.amazon.ecommerce.model.Product;
import com.amazon.ecommerce.model.Order;
import com.amazon.ecommerce.model.OrderStatus;
import com.amazon.ecommerce.exception.CustomerNotFoundException;
import com.amazon.ecommerce.exception.InsufficientStockException;
import com.amazon.ecommerce.exception.InvalidOrderException;
import com.amazon.ecommerce.exception.ProductNotFoundException;
import com.amazon.ecommerce.model.CartItem;
import com.amazon.ecommerce.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class OrderService 
{

	private OrderRepository orderRepository;
	
	private ProductService productService;
	private CustomerService customerService;
	private CartService cartService;
	
	public OrderService(OrderRepository orderRepository,ProductService productService, CustomerService customerService, CartService cartService) 
	{
		super();
		this.orderRepository=orderRepository;
		this.productService = productService;
		this.customerService = customerService;
		this.cartService = cartService;
	}
	
	private int nextOrderId=1001;
	
	//Placing Order
	public void placingOrder(int customerId)
	{
		if(customerService.getCustomerById(customerId)==null)
		{
			throw new CustomerNotFoundException("Customer Not Found");
		}
		
		List<CartItem> cart=cartService.viewingCart(customerId);
		
		if(cart==null)
		{
			System.out.println("Cart does not exist");
			return;
		}
		
		if(cart.isEmpty())
		{
			System.out.println("Cart is empty");
			return;
		}
		
		//Check whether all products exist
        //    and whether current stock is sufficient
		for(CartItem item:cart)
		{
			Product product=productService.searchingProductByID(item.getProductId());
			
			if(product==null)
			{
				 throw new ProductNotFoundException(
					        "Product Not Found : " + item.getProductId());
			}
			if(item.getQuantity()>product.getQuantity())
			{
				throw new InsufficientStockException("Insufficient Stock : "+product.getProductName());
			}
		}
		double totalAmount=cartService.totalCartValue(customerId);
		
		// Making a separate copy of cart items
		List<CartItem> orderItems=new ArrayList<>(cart);
		
		//Creating order
		Order order=new Order(nextOrderId, customerId, orderItems, totalAmount);
		
		order.setOrderStatus(OrderStatus.PLACED);
		
		//reducing product Stock
		for(CartItem item:cart)
		{
			Product product=productService.searchingProductByID(item.getProductId());
			
			int remainingStock=product.getQuantity()-item.getQuantity();
			
			product.setQuantity(remainingStock);
		}
		
		//saving order
		orderRepository.savingOrders(order);
		
		//Displaying orders
		System.out.println("Order placed Successfully");
		System.out.println("Order Id : "+order.getOrderId());
		System.out.println("Total Amount : "+order.getTotalAmount());
		
		//generating next orderId
		nextOrderId++;
		
		//Clearing customers cart
		cartService.clearingCart(customerId);
	}
	
	//viewing all orders
	public List<Order> displayingAllOrders()
	{
		return orderRepository.getAllOrders();
	}
	
	//View order of particular customer
	public List<Order> viewOrder(int customerId)
	{
		return orderRepository.getAllOrders().stream()
				.filter(y->y.getCustomerId()==customerId).collect(Collectors.toList());
	}
	
	//Finding the order of  orderId
	public Order getOrderByOrderId(int orderId)
	{
		Order order=orderRepository.orderById(orderId);
		
		if(order==null)
		{
			throw new InvalidOrderException("Order Not Found Exception");
		}
		return order;
	}
	
	//cancel Order
	public void cancellingOrder(int customerId,int orderId)
	{
		
		Order order=getOrderByOrderId(orderId);
		
		
		//Checking whether order belongs to customer
		if(order.getCustomerId()!=customerId)
		{
			System.out.println("This order does not belongs to you");
			return;
		}
		
		// Checking whether already cancelled
		if(order.getOrderStatus()==OrderStatus.CANCELLED)
		{
			System.out.println("Order Successfully CANCELLED");
			return;
		}
		
		//Restoring Product Stock
		for(CartItem item:order.getItems())
		{
			Product product=productService.searchingProductByID(item.getProductId());
			
			if(product!=null)
			{
				int restoredQuantity=product.getQuantity()+item.getQuantity();
				product.setQuantity(restoredQuantity);
			}
		}
		
		order.setOrderStatus(OrderStatus.CANCELLED);
		System.out.println("Order cancelled Syccessfully");
	}
	
	//updating the order with new Status if user needs
	public void updateStatus(int orderId,OrderStatus newStatus)
	{
		Order order=getOrderByOrderId(orderId);
		
		
		if(newStatus==null)
		{
			System.out.println("Invalid Order Status ");
			return;
		}
		
		order.setOrderStatus(newStatus);
		System.out.println("Order Status updated Sucessfully");
	}

}
