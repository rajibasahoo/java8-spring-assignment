package com.tele2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tele2.model.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer>{

}
