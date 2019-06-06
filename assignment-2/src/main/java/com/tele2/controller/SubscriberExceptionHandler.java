package com.tele2.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class SubscriberExceptionHandler {
	
	@ResponseBody
	@ExceptionHandler(SubscriptionNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	  String employeeNotFoundHandler(SubscriptionNotFoundException ex) {
	    return ex.getMessage();
	  }

}
