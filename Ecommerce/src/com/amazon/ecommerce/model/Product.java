package com.amazon.ecommerce.model;

public class Product 
{
	private int productId;
	private String productName;
	private String category;
	private double price;
	private int quantity;
	private String brand;
	
	public Product(int productId, String productName, String category, double price, int quantity, String brand)
	{
		super();
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
		this.brand = brand;
	}
	public int getProductId()
	{
		return productId;
	}
	
	public String getProductName()
	{
		return productName;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public String getBrand()
	{
		return brand;
	}

	public void setProductId(int productId) 
	{
		this.productId = productId;
	}
	public void setProductName(String productName) 
	{
		this.productName = productName;
	}
	public void setCategory(String category)
	{
		this.category = category;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	public void setBrand(String brand) 
	{
		this.brand = brand;
	}
	@Override
	public String toString()
	{
		return "Product Id : "+productId+" ,Product Name : "+productName+
				" ,Category : "+category+" ,Price : "+price+" ,Quantity : "
				+quantity+" ,Brand : "+brand;
	}

}
