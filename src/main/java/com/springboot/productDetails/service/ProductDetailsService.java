package com.springboot.productDetails.service;

import com.springboot.productDetails.beans.Response;

public interface ProductDetailsService {
	
	public Response getProducts(String labelType) throws Exception;
}
