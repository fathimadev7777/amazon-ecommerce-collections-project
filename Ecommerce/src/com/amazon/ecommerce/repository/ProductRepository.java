package com.amazon.ecommerce.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.amazon.ecommerce.exception.ProductNotFoundException;
import com.amazon.ecommerce.model.Product;

public class ProductRepository
{
	private List<Product> products=new ArrayList<>();
	private Set<Integer> productIds=new HashSet<>();
	
	//saving product
	public void saveProducts(Product product)
	{
		if(productIds.contains(product.getProductId()))
		{
			System.out.println("Product Id Already exists");
			return;
		}
		products.add(product);
		productIds.add(product.getProductId());
	}

	//get all products
	public List<Product> getAllProducts()
	{
		return products;
	}
	
//	//Find product by Id
//	public Product findProductById(int productId)
//	{
//		for(Product product:products)
//		{
//			if(product.getProductId()==productId)
//			{
//				return product;
//			}
//		}
//		return null;
//	}
	
	public Product findProductById(int productId)
	{
	
		Product product=products.stream().filter(y->y.getProductId()==productId).findFirst()
		.orElseThrow(()->new ProductNotFoundException("Product not found Exception"));
		return product;
	}
	
	//Delete Product
	public void deleteProduct(Product product)
	{
		products.remove(product);
	}
		}

