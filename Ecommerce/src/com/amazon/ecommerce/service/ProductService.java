package com.amazon.ecommerce.service;

import java.util.List;


import java.util.stream.Collectors;

import com.amazon.ecommerce.exception.InvalidQuantityException;

import com.amazon.ecommerce.model.Product;
import com.amazon.ecommerce.repository.ProductRepository;


public class ProductService 
{

	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}
	
	//We are Adding the products
	public void addProduct(Product product)
	{
		productRepository.saveProducts(product);
	}
	
	//Viewing all Products
	public List<Product> getAllProducts()
	{
		return productRepository.getAllProducts();
	}
	
	//Searching the Product BY ProductId
	public Product searchingProductByID(int productId)
	{
		return productRepository.findProductById(productId);
	}
	
	//Searching the Product BY ProductName
	public List<Product> searchingProductByName(String productName)
	{
		return productRepository.getAllProducts().stream().filter(t->t.getProductName().equalsIgnoreCase(productName))
				.collect(Collectors.toList());
	}
	
	//Searching the Product BY Category
	public List<Product> searchingProductByCategory(String category)
	{
		return productRepository.getAllProducts().stream().filter(t->t.getCategory().equalsIgnoreCase(category))
				.collect(Collectors.toList());
	}
	
	//Searching the Product BY Brand
	public List<Product> searchingProductByBrand(String brandName)
	{
		return productRepository.getAllProducts().stream().filter(f->f.getBrand().equalsIgnoreCase(brandName))
				.collect(Collectors.toList());
	}
	
	//Searching the Product BY PriceRange
	public List<Product> searchingProductByPriceRange(double minPrice,double maxPrice)
	{
		return productRepository.getAllProducts().stream().filter(t->t.getPrice()>=minPrice && t.getPrice()<=maxPrice)
				.collect(Collectors.toList());
	}
	
	//Updating Product Price
	public void updatingPrice(int productID,double newPrice)
	{
		Product product=productRepository.findProductById(productID);
		
		if(product!=null)
		{
			product.setPrice(newPrice);
		}
	}
	
	//Updating Product Quantity
	public void updatingQuantity(int productId,int newQuantity)
	{
		if(newQuantity<0)
		{
			throw new InvalidQuantityException("Quanity Cannot be Negative");
		}
		Product product=productRepository.findProductById(productId);
		
		if(product!=null)
		{
			product.setQuantity(newQuantity);
		}
		
	}
	
	//Deleting Product
	public void deletingProduct(int productId)
	{
		Product product=productRepository.findProductById(productId);
		
		if(product!=null)
		{
			productRepository.deleteProduct(product);
		}
	}

}
