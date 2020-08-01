package com.fis.casestudy.mstraining;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@GetMapping("/subscriptions")
	public List<Subscription> retrieveSubscriptions() {
		List<Subscription> subscribersList = subscriptionService.retrieveSubscriptions();
		return subscribersList ;
	}

	@PostMapping("/subscription")
	public Subscription subscription(@RequestParam String bookname, @RequestParam String author, @RequestParam String subscribername) {
		Subscription subscription = subscriptionService.subscription(bookname, author, subscribername);
		return subscription;
	}
	
	@PostMapping("/unsubscription")
	public Subscription unsubscription(@RequestParam String bookname, @RequestParam String author, @RequestParam String subscribername) {
		Subscription subscription = subscriptionService.unsubscription(bookname, author, subscribername);
		return subscription;
	}
}