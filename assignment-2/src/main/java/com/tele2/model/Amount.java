package com.tele2.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("subscription")
@Entity
public class Amount {
	@Id
	BigDecimal value;
	
	@OneToOne( mappedBy = "monthlyPrice")           
	private Subscription subscription;
	
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public Subscription getSubscription() {
		return subscription;
	}
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
	
	public Amount() {
		//super();
		
	}
}
