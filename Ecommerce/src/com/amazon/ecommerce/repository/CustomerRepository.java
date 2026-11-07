package com.amazon.ecommerce.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.amazon.ecommerce.model.Customer;

public class CustomerRepository 
{
	private Map<Integer, Customer> customers=new HashMap<>();
	private Set<Integer> customerIds=new HashSet<>();
	
	//save the customers
	public void saveCustomers(Customer customer)
	{
		if(customerIds.contains(customer.getCustomerId()))
		{
			System.out.println("Customer Id already Exists");
			return;
		}
		customers.put(customer.getCustomerId(), customer);
		customerIds.add(customer.getCustomerId());
		
	}
	
	//Finding customers by id
	public Customer customerById(int customerId)
	{
		return customers.get(customerId);
	}
	
	//Get All Customers
	public List<Customer> getAllCustomers()
	{
		return new ArrayList<>(customers.values());
	}
	
	

}
