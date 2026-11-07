package com.amazon.ecommerce.exception;

public class ProductNotFoundInCartException extends RuntimeException
{
	public ProductNotFoundInCartException(String msg) {
		super(msg);
	}

}
