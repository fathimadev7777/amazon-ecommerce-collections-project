package com.amazon.ecommerce.main;


import com.amazon.ecommerce.repository.CustomerRepository;
import com.amazon.ecommerce.repository.OrderRepository;
import com.amazon.ecommerce.repository.ProductRepository;
import com.amazon.ecommerce.service.CartService;
import com.amazon.ecommerce.service.CustomerService;
import com.amazon.ecommerce.service.OrderService;
import com.amazon.ecommerce.service.ProductService;
import com.amazon.ecommerce.menu.AdminMenu;
import com.amazon.ecommerce.menu.CustomerMenu;

import java.util.Scanner;



public class EcommerceApplicationMain
{
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		// Repositories
		ProductRepository productRepository = new ProductRepository();
		CustomerRepository customerRepository = new CustomerRepository();
		OrderRepository orderRepository = new OrderRepository();

		// Services
		ProductService productService = new ProductService(productRepository);

		CustomerService customerService = new CustomerService(customerRepository);

		CartService cartService = new CartService(productService);

		OrderService orderService = new OrderService(orderRepository, productService, customerService, cartService);

		AdminMenu adminMenu = new AdminMenu();
		CustomerMenu customerMenu = new CustomerMenu();

		boolean running = true;

		while (running) {

			
			System.out.println("     AMAZON E-COMMERCE SYSTEM     ");
			
			System.out.println("1. Admin");
			System.out.println("2. Customer");
			System.out.println("3. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();

			switch (choice) {

			case 1:
				adminMenu.adminMenu(scanner, productService, customerService, orderService);
				break;

			case 2:
				customerMenu.customerMenu(
			            scanner,
			            productService,
			            customerService,
			            cartService,
			            orderService
			    );

			    break;

			case 3:
				running = false;
				System.out.println("Thank you for Visiting");
				break;

			default:
				System.out.println("Invalid choice.");
			}
		}

		scanner.close();
	}
	

}
