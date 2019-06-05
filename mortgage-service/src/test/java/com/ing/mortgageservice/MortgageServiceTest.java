package com.ing.mortgageservice;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ing.mortgageservice.model.MortgageResponse;
import com.ing.mortgageservice.service.MortgageService;


public class MortgageServiceTest {

	@Test
	public void testMortgageRequestEligibilityCheckForFeasibleLoan() {
		double loanValue=400000.00;
		double income=100000.00;
		int maturityPeriod=10;
		double homeValue=400000.00;
		double delta=0.01;
	    MortgageService mortgageService=new MortgageService();
		double monthlyCost=mortgageService.calculateMonthlyCost(loanValue, maturityPeriod);
		MortgageResponse mortgageResponse=mortgageService.mortgageRequestEligibilityCheck(loanValue, income, maturityPeriod, homeValue);
		assertEquals(true, mortgageResponse.isFeasible());
		assertEquals(monthlyCost, mortgageResponse.getMonthlyCost(), delta);
	}
	
	@Test
	public void testMortgageRequestEligibilityCheckWhenLoanValueIsMoreThanFourTimesOfIncome() {
		double loanValue=500000.00;
		double income=100000.00;
		int maturityPeriod=10;
		double homeValue=500000.00;
		double delta=0.01;
	    MortgageService mortgageService=new MortgageService();
		double monthlyCost=0.0;
		MortgageResponse mortgageResponse=mortgageService.mortgageRequestEligibilityCheck(loanValue, income, maturityPeriod, homeValue);
		assertEquals(false, mortgageResponse.isFeasible());
		assertEquals(monthlyCost, mortgageResponse.getMonthlyCost(), delta);
	}
	
	@Test
	public void testMortgageRequestEligibilityCheckWhenLoanValueIsGreaterThanHomeValue() {
		double loanValue=400000.00;
		double income=100000.00;
		int maturityPeriod=10;
		double homeValue=300000.00;
		double delta=0.01;
	    MortgageService mortgageService=new MortgageService();
		double monthlyCost=0.0;
		MortgageResponse mortgageResponse=mortgageService.mortgageRequestEligibilityCheck(loanValue, income, maturityPeriod, homeValue);
		assertEquals(false, mortgageResponse.isFeasible());
		assertEquals(monthlyCost, mortgageResponse.getMonthlyCost(), delta);
	}

}
