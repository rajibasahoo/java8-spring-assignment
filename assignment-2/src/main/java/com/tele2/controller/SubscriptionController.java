package com.tele2.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tele2.model.Subscription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tele2.controller.SubscriptionController;
import com.tele2.dao.SubscriptionRepository;


@RestController
public class SubscriptionController {
	
	private Logger logger = LoggerFactory.getLogger(SubscriptionController.class);
	
	@Autowired
	private SubscriptionRepository repository;
	
	//@HystrixCommand(fallbackMethod = "fallBack",commandKey = "saveapi", groupKey = "saveapi")
	@PostMapping("/save")
	public String saveSubscriber(@RequestBody Subscription subscriber) {
		/*//Wrong
        if (RandomUtils.nextBoolean()) {
            throw new RuntimeException("Failed!");
        }*/
		
		repository.save(subscriber);
		return "Subscriber saved.";
	}
	
	
	@GetMapping("/getAll")
	public List<Subscription> getAllSubscriber() {
		logger.info("inside get all subscriber");
		
		return repository.findAll();
		
	}
	
	@GetMapping("/getOne/{id}")
	public Subscription getOneSubscriberById(@PathVariable int id) {
		logger.info("inside get only respective subscriber");
		
		return repository.findById(id).
				orElseThrow(() -> new SubscriptionNotFoundException(id));
		
	}
	
	
	 @PutMapping("/updateOne/{id}")
	 public Subscription updateSubscriberById(@RequestBody Subscription newSubcription, @PathVariable int id) {

	    return repository.findById(id)
	      .map(subscription -> {
	    	  subscription.setMonthlyPrice(newSubcription.getMonthlyPrice());	        
	        return repository.save(subscription);
	      }).orElseThrow(() -> new SubscriptionNotFoundException(id));
	      
	  }
	
	
	 public String fallBack(Subscription subscriber) {
	        return "Fall Back Service initiated";
	 }

}
