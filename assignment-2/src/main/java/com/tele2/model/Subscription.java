package com.tele2.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Subscription {
	@Id	
	private int id;
	private String name;
	@OneToOne( cascade = CascadeType.ALL)	
	private Amount monthlyPrice;
	private Date lastUpdate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public Amount getMonthlyPrice() {
		return monthlyPrice;
	}
	public void setMonthlyPrice(Amount monthlyPrice) {
		this.monthlyPrice = monthlyPrice;
	}
	public Subscription(int id, String name, Date lastUpdate,Amount monthlyPrice) {
		super();
		this.id = id;
		this.name = name;
		this.lastUpdate =lastUpdate;
		this.monthlyPrice.setSubscription(this); 
	}
	
	public Subscription() {
		//super();
		
	}
	

}
