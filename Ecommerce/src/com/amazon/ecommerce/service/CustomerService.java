package com.amazon.ecommerce.service;

import java.util.List;

import com.amazon.ecommerce.exception.CustomerNotFoundException;
import com.amazon.ecommerce.model.Customer;
import com.amazon.ecommerce.repository.CustomerRepository;

public class CustomerService 
{
	private CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	//Registering the Customer
	public void registerCustomer(Customer customer)
	{
		customerRepository.saveCustomers(customer);
	}

	//Viewing all Customer
	public List<Customer> viewingCustomers()
	{
		return customerRepository.getAllCustomers();
	}
	
	//Searching Customer By Id
	public Customer getCustomerById(int customerId)
	{
		Customer customer=customerRepository.customerById(customerId);
		if(customer==null)
		{
			throw new CustomerNotFoundException("Customer with ID " + customerId + " does not exist");
		}
		return customer;
		
	}
	
	//Updating Customer Address
	public void updatingCustomerAddress(int customerId,String customerAddres)
	{
		Customer customer=customerRepository.customerById(customerId);
		if (customer == null)
		{
            throw new CustomerNotFoundException(
                    "Customer not found with ID: " + customerId
            );
        }
		if(customer!=null)
		{
			customer.setAddress(customerAddres);
		}
	}
	
}
