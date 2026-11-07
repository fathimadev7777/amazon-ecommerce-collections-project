package com.amazon.ecommerce.model;

public class Customer
{
	private int customerId;
	private String name;
	private String email;
	private String mobileNo;
	private String address;
	
	public Customer(int customerId, String name, String email, String mobileNo, String address) 
	{
		super();
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		this.address = address;
	}
	
	public int getCustomerId() 
	{
		return customerId;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getMobileNo() 
	{
		return mobileNo;
	}
	
	public String getAddress() 
	{
		return address;
	}
	
	

	public void setCustomerId(int customerId) 
	{
		this.customerId = customerId;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public void setMobileNo(String mobileNo) 
	{
		this.mobileNo = mobileNo;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	@Override
	public String toString()
	{
		return "Customer Id : "+customerId+" ,Customer Name : "+name+
			" ,Email : "+email+" ,Mobile No : "+mobileNo+" ,Address : "+address;
	}

}
