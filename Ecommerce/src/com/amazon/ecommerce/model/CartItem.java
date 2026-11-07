package com.amazon.ecommerce.model;

public class CartItem 
{
	private int productId;
	private String productName;
	private double price;
	private int quantity;
	
	
	public CartItem(int productId, String productName, double price, int quantity) 
	{
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		
	}

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public double getPrice() {
		return price;
	}
	
	public int setquantity(int quantity)
	{
		return this.quantity=quantity;
		
	}

	public int getQuantity() {
		return quantity;
	}
	
	public double getTotalPrice()
	{
		return price*quantity;
	}

	
	
	@Override
	public String toString()
	{
		return "Product Id : "+productId+" Product Name : "+productName+
			" ,Price : "+price+" ,Quantity : "+quantity+" ,Total Price : "
				+getTotalPrice();
	}

}
