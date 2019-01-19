package com.springboot.productDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.productDetails.beans.Response;
import com.springboot.productDetails.service.ProductDetailsService;

@RestController
@RequestMapping("/")
public class ProductDetailsController {

	@Autowired
	ProductDetailsService productDetailsService;

	@RequestMapping("/v1/products")
	public Response listAllProducts(
			@RequestParam(value = "labelType", required = false) String labelType)
			throws Exception {
		return productDetailsService.getProducts(labelType);
	}

}
