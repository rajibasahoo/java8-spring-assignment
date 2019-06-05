package com.ing.mortgageservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.ing.mortgageservice.model.MortgageRate;
import com.ing.mortgageservice.model.MortgageResponse;

@Component
public class MortgageService {
	
	private Logger logger = LoggerFactory.getLogger(MortgageService.class);
	
	private List<MortgageRate> mortgageRates;
	
	
	public MortgageResponse mortgageRequestEligibilityCheck(double loanValue, double income, int maturityPeriod, double homeValue) {
		MortgageResponse mortageResponse=new MortgageResponse();
		if(loanValue<=4*income && loanValue<=homeValue) {
			mortageResponse.setFeasible(true);
			mortageResponse.setMonthlyCost(calculateMonthlyCost(loanValue, maturityPeriod));
		}
		else {
			mortageResponse.setFeasible(false);
			logger.info("mortgage request is not feasible");
		}
		return mortageResponse;
	}
	
	public double calculateMonthlyCost(double loanValue, int maturityPeriod) {
		double monthlyCost=loanValue/(maturityPeriod*12);
		return monthlyCost;
	}
	
	@PostConstruct
	public void createMortgageRatesList() {
		mortgageRates =new ArrayList<MortgageRate>();
		mortgageRates.add(new MortgageRate(5,12,new Date()));
		mortgageRates.add(new MortgageRate(10,11,new Date()));
		mortgageRates.add(new MortgageRate(15,10,new Date()));
		mortgageRates.add(new MortgageRate(20,9,new Date()));
		mortgageRates.add(new MortgageRate(25,8,new Date()));
	}
	
	public List<MortgageRate> getMortgageRates(){
		return mortgageRates;
	} 
	
}
