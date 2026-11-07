package com.amazon.ecommerce.menu;

import java.util.List;
import java.util.Scanner;

import com.amazon.ecommerce.model.Customer;
import com.amazon.ecommerce.model.Order;
import com.amazon.ecommerce.model.Product;
import com.amazon.ecommerce.service.CustomerService;
import com.amazon.ecommerce.service.OrderService;
import com.amazon.ecommerce.service.ProductService;

public class AdminMenu 
{
	public void adminMenu(Scanner scanner, ProductService productService, CustomerService customerService,
			OrderService orderService) {

		boolean adminRunning = true;

		while (adminRunning) {

			System.out.println("\n------ ADMIN MENU ------");
			System.out.println("1. Add Product");
			System.out.println("2. View Products");
			System.out.println("3. Search Product");
			System.out.println("4. Update Product");
			System.out.println("5. Delete Product");
			System.out.println("6. View Customers");
			System.out.println("7. View All Orders");
			System.out.println("8. Exit");
			System.out.print("Enter your choice : ");

			int choice = scanner.nextInt();

			switch (choice) {

			case 1:

				System.out.print("Enter Product ID: ");
				int productId = scanner.nextInt();

				scanner.nextLine(); //consuming left overline

				System.out.print("Enter Product Name: ");
				String productName = scanner.nextLine();

				System.out.print("Enter Category: ");
				String category = scanner.nextLine();

				System.out.print("Enter Price: ");
				double price = scanner.nextDouble();

				System.out.print("Enter Quantity: ");
				int quantity = scanner.nextInt();

				scanner.nextLine();

				System.out.print("Enter Brand: ");
				String brand = scanner.nextLine();

				Product product = new Product(productId, productName, category, price, quantity, brand);

				productService.addProduct(product);

				System.out.println("Product added successfully !!!");

				break;

			case 2:

				List<Product> products = productService.getAllProducts();

				if (products.isEmpty()) {

					System.out.println("No products available.");

				} else {

					for (Product product1 : products) {
						System.out.println(product1);
					}
				}

				break;

			case 3:

				System.out.println("1. Search By ID");
				System.out.println("2. Search By Name");
				System.out.println("3. Search By Category");
				System.out.println("4. Search By Brand");
				System.out.println("5. Search By Price Range");
				System.out.println("6. Back");
				
				System.out.print("Enter your choice: ");
				int searchChoice = scanner.nextInt();

				switch (searchChoice) {

				case 1:

					System.out.print("Enter Product ID: ");
					int searchId = scanner.nextInt();

					Product foundProduct = productService.searchingProductByID(searchId);

					if (foundProduct == null) {
						System.out.println("Product not found.");
					} else {
						System.out.println(foundProduct);
					}

					break;

				case 2:

					scanner.nextLine(); //consumming left overline

					System.out.print("Enter Product Name: ");
					String searchName = scanner.nextLine();

					List<Product> productsByName = productService.searchingProductByName(searchName);

					if (productsByName.isEmpty()) {
						System.out.println("Product not found.");
					} else {

						for (Product productByName : productsByName) {
							System.out.println(productByName);
						}
					}

					break;

				case 3:
					
					//consumming left overline
					scanner.nextLine();

					System.out.print("Enter Category: ");
					String searchCategory = scanner.nextLine();

					List<Product> productsByCategory = productService.searchingProductByCategory(searchCategory);

					if (productsByCategory.isEmpty()) {
						System.out.println("Product not found.");
					} else {

						for (Product productByCategory : productsByCategory) {

							System.out.println(productByCategory);
						}
					}

					break;

				case 4:

					scanner.nextLine(); //consumming left overline

					System.out.print("Enter Brand: ");
					String searchBrand = scanner.nextLine();

					List<Product> productsByBrand = productService.searchingProductByBrand(searchBrand);

					if (productsByBrand.isEmpty()) {
						System.out.println("Product not found.");
					} else {

						for (Product productByBrand : productsByBrand) {

							System.out.println(productByBrand);
						}
					}

					break;

				case 5:
					
					System.out.print("Enter your Minimum Price: ");
					double minPrice = scanner.nextDouble();

					System.out.print("Enter your  Maximum Price: ");
					double maxPrice = scanner.nextDouble();

					List<Product> productsByPrice = productService.searchingProductByPriceRange(minPrice, maxPrice);

					if (productsByPrice.isEmpty()) {
						System.out.println("Product not found.");
					} else {

						for (Product productByPrice : productsByPrice) {

							System.out.println(productByPrice);
						}
					}

					break;

				case 6:
					break;

				default:
					System.out.println("Invalid search choice.");
				}

				break;

			case 4:

				System.out.println("1. Update Price");
				System.out.println("2. Update Quantity");
				System.out.println("3. Back");

				System.out.print("Enter your choice: ");
				int updateChoice = scanner.nextInt();

				switch (updateChoice) {

				case 1:

					System.out.print("Enter Product ID: ");
					int priceProductId = scanner.nextInt();

					System.out.print("Enter New Price: ");
					double newPrice = scanner.nextDouble();

					productService.updatingPrice(priceProductId, newPrice);

					System.out.println("Product price updated successfully.");

					break;

				case 2:

					System.out.print("Enter Product ID: ");
					int quantityProductId = scanner.nextInt();

					System.out.print("Enter New Quantity: ");
					int newQuantity = scanner.nextInt();
					
					productService.updatingQuantity(quantityProductId, newQuantity);

					System.out.println("Product quantity updated successfully.");

					break;

				case 3:
					break;

				default:
					System.out.println("Invalid update choice.");
				}

				break;
			case 5:
				
				System.out.print("Enter Product ID: ");
				int deleteProductId = scanner.nextInt();

				productService.deletingProduct(deleteProductId);

				System.out.println("Product deleted successfully !!!");

				break;
			case 6:
				List<Customer> customers = customerService.viewingCustomers();

				if (customers.isEmpty()) 
				{
					System.out.println("No customers registered.");
				} 
				else
				{
					for (Customer customer : customers) 
					{
						System.out.println(customer);
					}
				}
				break;

			case 7:
				List<Order> orders = orderService.displayingAllOrders();

				if (orders.isEmpty()) {
					System.out.println("No orders available.");
				} else {
					for (Order order : orders) {
						System.out.println(order);
					}
				}

				break;

			case 8:
				adminRunning = false;
				break;

			default:
				System.out.println("Invalid choice.");
			}
		}
	}

	
}
