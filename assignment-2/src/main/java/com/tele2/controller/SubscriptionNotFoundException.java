package com.tele2.controller;

public class SubscriptionNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SubscriptionNotFoundException(int id) {
	    super("Could not find subscriber " + id);
	  }

}
