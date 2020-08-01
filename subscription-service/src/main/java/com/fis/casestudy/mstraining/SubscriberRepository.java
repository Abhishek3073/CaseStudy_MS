package com.fis.casestudy.mstraining;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SubscriberRepository extends CrudRepository<Subscription, Long> {
	
	@Override
    List<Subscription> findAll();
	
	List<Subscription> findBySubscriberName(String subscriberName);
}