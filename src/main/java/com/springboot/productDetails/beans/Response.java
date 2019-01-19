package com.springboot.productDetails.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

	@JsonProperty
	private Products products[];

	public Products[] getProducts() {
		return products;
	}

	public void setProducts(Products products[]) {
		this.products = products;
	}

}