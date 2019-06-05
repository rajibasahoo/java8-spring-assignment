package com.ing.mortgageservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ing.mortgageservice.exception.MortgageServiceInputException;
import com.ing.mortgageservice.model.MortgageRate;
import com.ing.mortgageservice.model.MortgageRequest;
import com.ing.mortgageservice.model.MortgageResponse;
import com.ing.mortgageservice.service.MortgageService;


@RestController
public class MortgageController {

	private Logger logger = LoggerFactory.getLogger(MortgageController.class);
	
    @Autowired 
    MortgageService mortgageService;
	
    @GetMapping(value = "/api/interest-rates")
    public List<MortgageRate> interestRates() {
    	logger.info("Interest Rates service invoked");
        return mortgageService.getMortgageRates();
    }
    
    @PostMapping(value = "/api/mortgage-check")
    public MortgageResponse mortgageCheck(@RequestBody MortgageRequest request) {
    	logger.info("mortgage check service invoked");
    	double loanValue=request.getLoanValue();
    	double income=request.getIncome();
    	double homeValue=request.getHomeValue();
    	int maturityPeriod=request.getMaturityPeriod();
    	if(loanValue<=0)
    		logAndThrowMortgageServiceInputException("Loan Value can't be less than or equal to zero");
    	if(homeValue<=0)
    		logAndThrowMortgageServiceInputException("Home Value can't be less than or equal to zero");
    	if(maturityPeriod<=0)
    		logAndThrowMortgageServiceInputException("Maturity Period can't be less than or equal to zero");
    	if(income<=0)
    		logAndThrowMortgageServiceInputException("Income can't be less than or equal to zero");
    	return mortgageService.mortgageRequestEligibilityCheck(loanValue, income, maturityPeriod, homeValue);
    }
    
    private void logAndThrowMortgageServiceInputException(String inputExceptionMessage) {
        logger.error(inputExceptionMessage);
        throw new MortgageServiceInputException(inputExceptionMessage);
    }
}