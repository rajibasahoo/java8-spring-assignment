package com.ing.mortgageservice.model;

public class MortgageResponse {
	private boolean isFeasible;
	private double monthlyCost;

	public MortgageResponse() {
		// TODO Auto-generated constructor stub
	} 
	public MortgageResponse(boolean isFeasible,double monthlyCost) {
		this.isFeasible=isFeasible;
		this.monthlyCost=monthlyCost;
	}
	
	public boolean isFeasible() {
		return isFeasible;
	}

	public void setFeasible(boolean isFeasible) {
		this.isFeasible = isFeasible;
	}

	public double getMonthlyCost() {
		return monthlyCost;
	}

	public void setMonthlyCost(double monthlyCost) {
		this.monthlyCost = monthlyCost;
	}
}
