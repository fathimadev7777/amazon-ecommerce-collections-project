package com.amazon.ecommerce.service;

import com.amazon.ecommerce.model.Product;
import com.amazon.ecommerce.exception.InsufficientStockException;
import com.amazon.ecommerce.exception.InvalidQuantityException;
import com.amazon.ecommerce.exception.ProductNotFoundException;
import com.amazon.ecommerce.exception.ProductNotFoundInCartException;
import com.amazon.ecommerce.model.CartItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CartService 
{
	private Map<Integer, List<CartItem>> customerCarts=new HashMap<>();
	
	private ProductService productService;

	public CartService( ProductService productService)
	{
		super();
		this.productService = productService;
	}
	
	//Adding product to cart
	public void addToCart(int productId,int customerId,int quantity)
	{
		Product product=productService.searchingProductByID(productId);
		
		if(product==null)
		{
			 throw new ProductNotFoundException("Product not found with ID: " + productId);
		}
		if(quantity<=0)
		{
			throw new InvalidQuantityException("Quantity must be greater than zero");
		}
		
		if(quantity>product.getQuantity())
		{
			throw new InsufficientStockException("Insufficient Stock");
		}
		
		List<CartItem> cart=customerCarts.get(customerId);
		
		if(cart==null)
		{
			cart=new ArrayList<>();
			customerCarts.put(customerId, cart);
		}
		
		//simply we assuming there is product not exist in cart
		CartItem existingItem=null;
		
		for(CartItem item:cart)
		{
			if(item.getProductId()==productId)
			{
				//then we will refer the item to existingItem
				existingItem=item;
				break;
			}
		}
		
		if(existingItem!=null)
		{
			int newQuatity=existingItem.getQuantity()+quantity;
			
			if(newQuatity>product.getQuantity())
			{
				throw new InsufficientStockException("Insufficient Stock");
			}
			
			existingItem.setquantity(newQuatity);
		}
		else
		{
		CartItem cartItem=new CartItem(product.getProductId(),product.getProductName()
					,product.getPrice(),quantity);
		
		cart.add(cartItem);
		
		}
		
		System.out.println("Product Added to cart Successfully");
	}
	
	//Removing the product from the cart
	public void removingProductFromCart(int productId,int customerId)
	{
		List<CartItem> cart=customerCarts.get(customerId);
		
		if(cart==null)
		{
			throw new ProductNotFoundInCartException("Product Not Found");
		}
		
		if(cart.isEmpty())
		{
			System.out.println("Cart is Empty");
			return;
		}
		
		//we are creating this variable to store the item we find
		//Initially We don't know still which item needs to be removed
		CartItem itemToRemove=null;
		
		for(CartItem item:cart)
		{
			if(item.getProductId()==productId)
			{
				itemToRemove=item; //Remember which item to remove
				break; //Stop searching(basically it exists from loop)
			}
		}
		
		if(itemToRemove!=null)
		{
			cart.remove(itemToRemove);
			System.out.println("Product removed From Cart");
		}
		else
		{
			throw new ProductNotFoundException("Product Not Found");
		}
		
	}
	
	//viewing the cart
	public List<CartItem> viewingCart(int customerId)
	{
		List<CartItem> cart=customerCarts.get(customerId);
		
		if(cart==null)
		{
			return new ArrayList<>();
		}
		return cart;
	}
	
	//Increasing product quantity
	public void increasingProductQuantity(int customerId,int productId)
	{
		List<CartItem> cart=customerCarts.get(customerId);
		
		if(cart==null)
		{
			System.out.println("Cart Does not Exists");
			return;
		}
		
		if(cart.isEmpty())
		{
			System.out.println("Cart is Empty");
			return;
		}
		
		for(CartItem item:cart)
		{
			if(item.getProductId()==productId)
			{
				Product product=productService.searchingProductByID(productId);
				if(item.getQuantity()<product.getQuantity())
				{
					item.setquantity(item.getQuantity()+1);
					System.out.println("Quantity incresed");
				}
				else
				{
					System.out.println("No more Stock Availible");
				}
				return;
			}
		}
		System.out.println("Product not found in cart");
	}
	
	//Decreasing the product Quatity
	public void decreasingtheQuantity(int customerId,int productId)
	{
		List<CartItem> cart=customerCarts.get(customerId);
		
		if(cart==null)
		{
			System.out.println("Cart Does not Exists");
			return;
		}
		
		if(cart.isEmpty())
		{
			System.out.println("Cart is Empty");
			return;
		}
		
		Iterator<CartItem> itr=cart.iterator();
		
		while(itr.hasNext())
		{
			CartItem cartItem=itr.next();
			if(cartItem.getProductId()==productId)
			{
			if(cartItem.getQuantity()>1)
			{
				cartItem.setquantity(cartItem.getQuantity()-1);
				System.out.println("Quantity decreased Successfully ");
			}
			else
			{
				itr.remove();
			System.out.println("item is removed");
			}
			return;
			}
			
		}
		System.out.println("Product Not found in cart");
	}
	
	//Total Cart Value
	public double totalCartValue(int customerId)
	{
		List<CartItem> cart=customerCarts.get(customerId);
		
		if(cart==null || cart.isEmpty())
		{
			return 0;
		}
		
		//mapToDouble will convert the stream elements to doubleStreams
		return cart.stream().mapToDouble(CartItem::getTotalPrice).sum();
	}
	
	//Clearing the Cart
	public void clearingCart(int customerId)
	{
		List<CartItem> cart=customerCarts.get(customerId);
		
		if(cart==null)
		{
			System.out.println("Cart does not exists");
		}
		else
		{
			cart.clear();
		}
	}
	
}
