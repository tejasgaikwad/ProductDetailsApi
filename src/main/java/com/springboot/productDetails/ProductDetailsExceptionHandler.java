package com.springboot.productDetails;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.productDetails.beans.ErrorDetails;


@ControllerAdvice(basePackages = "com.springboot")
@RestController
public class ProductDetailsExceptionHandler  {
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorDetails> handleException() {
		ErrorDetails errorDetails = new ErrorDetails(new Date(),
				"Internal Error Occured. Please contact adminstrator.",
				"");
		return new ResponseEntity<>(errorDetails,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
