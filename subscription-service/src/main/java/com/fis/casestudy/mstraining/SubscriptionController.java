package com.fis.casestudy.mstraining;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@Validated
public class SubscriptionController {
	Logger logger = LoggerFactory.getLogger(SubscriptionController.class.getName());
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@GetMapping("/subscriptions")
	public List<Subscription> retrieveSubscriptions() {
		List<Subscription> subscribersList = subscriptionService.retrieveSubscriptions();
		return subscribersList ;
	}

	@PostMapping("/subscription")
	@HystrixCommand(fallbackMethod="fallbackForSubscription")
	public Subscription subscription(@RequestParam @NotEmpty String bookname, @RequestParam @NotEmpty String author, @RequestParam @NotEmpty String subscribername) {
		return subscriptionService.subscription(bookname, author, subscribername);
	}
	
	@PostMapping("/unsubscription")
	@HystrixCommand(fallbackMethod="fallbackForUnsubscription")
	public Subscription unsubscription(@RequestParam @NotEmpty String bookname, @RequestParam @NotEmpty String author, @RequestParam @NotEmpty String subscribername) {
		return subscriptionService.unsubscription(bookname, author, subscribername);
	}
	
	public Subscription fallbackForSubscription(@PathVariable String bookname, @PathVariable String author, @PathVariable String subscribername) {
		logger.debug("Book is not available this time, please check later");
		return new Subscription(0L, subscribername, null, null, "Book is not available this time, please check later");
    }
	
	public Subscription fallbackForUnsubscription(@PathVariable String bookname, @PathVariable String author, @PathVariable String subscribername) {
		logger.debug("Either this user is not associated with this book or already unscribed");
		return new Subscription(0L, subscribername, null, null, "Either this user is not associated with this book or already unscribed");
	}
}
