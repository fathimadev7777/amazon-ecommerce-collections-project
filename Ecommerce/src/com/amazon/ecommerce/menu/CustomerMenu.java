package com.amazon.ecommerce.menu;

import java.util.List;
import java.util.Scanner;

import com.amazon.ecommerce.exception.CustomerNotFoundException;
import com.amazon.ecommerce.exception.ProductNotFoundException;
import com.amazon.ecommerce.model.CartItem;
import com.amazon.ecommerce.model.Customer;
import com.amazon.ecommerce.model.Order;
import com.amazon.ecommerce.model.Product;
import com.amazon.ecommerce.service.CartService;
import com.amazon.ecommerce.service.CustomerService;
import com.amazon.ecommerce.service.OrderService;
import com.amazon.ecommerce.service.ProductService;

public class CustomerMenu {

	public void customerMenu(Scanner scanner, ProductService productService, CustomerService customerService,
			CartService cartService, OrderService orderService) {

		boolean customerRunning = true;

		int loggedInCustomerId = 0;

		while (customerRunning) {

			System.out.println("\n========== CUSTOMER MENU ==========");

			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. View Products");
			System.out.println("4. Search Product");
			System.out.println("5. Add Product to Cart");
			System.out.println("6. View Cart");
			System.out.println("7. Remove Product from Cart");
			System.out.println("8. Place Order");
			System.out.println("9. View My Orders");
			System.out.println("10. Cancel Order");
			System.out.println("11. Logout");

			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();

			switch (choice) {

			case 1: // Customer registration

				System.out.print("Enter Customer ID: ");

				int customerId = scanner.nextInt();

				scanner.nextLine();

				System.out.print("Enter Name: ");

				String name = scanner.nextLine();

				System.out.print("Enter Email: ");

				String email = scanner.nextLine();

				System.out.print("Enter Mobile: ");

				String mobile = scanner.nextLine();

				System.out.print("Enter Address: ");

				String address = scanner.nextLine();

				Customer customer = new Customer(customerId, name, email, mobile, address);

				customerService.registerCustomer(customer);

				System.out.println("Customer registered successfully ");

				break;

			case 2: // login

				System.out.print("Enter Customer ID: ");

				int loginCustomerId = scanner.nextInt();

				try {

					customerService.getCustomerById(loginCustomerId);

					loggedInCustomerId = loginCustomerId;

					System.out.println("Login successful.");

					System.out.println("Welcome Customer " + loggedInCustomerId);

				} catch (CustomerNotFoundException e) {

					System.out.println(e.getMessage());
				}

				break;

			case 3: // viewing products

				List<Product> customerProducts = productService.getAllProducts();

				if (customerProducts.isEmpty()) {

					System.out.println("No products available.");

				} else {

					for (Product product1 : customerProducts) {

						System.out.println(product1);
					}
				}

				break;

			case 4: // searching product

				System.out.println("1. Search By ID");
				System.out.println("2. Search By Name");
				System.out.println("3. Search By Category");
				System.out.println("4. Search By Brand");
				System.out.println("5. Search By Price Range");
				System.out.println("6. Back");

				System.out.print("Enter your choice: ");

				int customerSearchChoice = scanner.nextInt();

				switch (customerSearchChoice) {

				case 1:

					System.out.print("Enter Product ID: ");

					int searchId = scanner.nextInt();
					try {
						Product foundProduct = productService.searchingProductByID(searchId);
						System.out.println(foundProduct);
					} catch (ProductNotFoundException p) {
						System.out.println(p.getMessage());
					}

					break;
				case 2:
					scanner.nextLine();

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

					scanner.nextLine();

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

					System.out.print("Enter Minimum Price: ");

					double minPrice = scanner.nextDouble();

					System.out.print("Enter Maximum Price: ");

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

			case 5:

				if (loggedInCustomerId == 0) {

					System.out.println("Please login first.");

					break;
				}

				System.out.print("Enter Product ID: ");

				int cartProductId = scanner.nextInt();

				System.out.print("Enter Quantity: ");

				int cartQuantity = scanner.nextInt();

				try {

					cartService.addToCart(cartProductId, loggedInCustomerId, cartQuantity);

				} catch (RuntimeException e) {

					System.out.println(e.getMessage());
				}

				break;

			case 6: // viewing cart

				if (loggedInCustomerId == 0) {

					System.out.println("Please login first.");

					break;
				}

				List<CartItem> cart = cartService.viewingCart(loggedInCustomerId);

				if (cart.isEmpty()) {

					System.out.println("Cart is empty.");

				} else {

					System.out.println("\n========== YOUR CART ==========");

					for (CartItem item : cart) {

						System.out.println(item);
					}

					System.out.println("Total Cart Value: " + cartService.totalCartValue(loggedInCustomerId));
				}

				break;

			case 7: // removing product

				if (loggedInCustomerId == 0) {

					System.out.println("Please login first.");

					break;
				}

				System.out.print("Enter Product ID to remove: ");

				int removeProductId = scanner.nextInt();

				try {

					cartService.removingProductFromCart(removeProductId, loggedInCustomerId);

				} catch (RuntimeException e) {

					System.out.println(e.getMessage());
				}

				break;

			case 8: // placong order

				if (loggedInCustomerId == 0) {

					System.out.println("Please login first.");

					break;
				}

				try {

					orderService.placingOrder(loggedInCustomerId);

				} catch (RuntimeException e) {

					System.out.println(e.getMessage());
				}

				break;

			case 9: // viewing customers orders

				if (loggedInCustomerId == 0) {

					System.out.println("Please login first.");

					break;
				}

				List<Order> myOrders = orderService.viewOrder(loggedInCustomerId);

				if (myOrders.isEmpty()) {

					System.out.println("No orders found.");

				} else {

					System.out.println("\n========== MY ORDERS ==========");

					for (Order order : myOrders) {

						System.out.println(order);
					}
				}

				break;

			case 10: // cancelling order

				if (loggedInCustomerId == 0) {

					System.out.println("Please login first.");

					break;
				}

				System.out.print("Enter Order ID: ");

				int cancelOrderId = scanner.nextInt();

				try {

					orderService.cancellingOrder(loggedInCustomerId, cancelOrderId);

				} catch (RuntimeException e) {

					System.out.println(e.getMessage());
				}

				break;

			case 11:
				loggedInCustomerId = 0;

				System.out.println("Logged out successfully.");

				customerRunning = false;

				break;

			default:

				System.out.println("Invalid choice.");
			}
		}

	}

}
