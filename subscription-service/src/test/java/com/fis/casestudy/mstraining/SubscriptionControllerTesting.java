package com.fis.casestudy.mstraining;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SubscriptionControllerTesting{

	@Autowired 
	SubscriptionController controller;
	
	@Test
	@Order(1)
	public void testSubscriptions() {
		List<Subscription> subscription = this.controller.retrieveSubscriptions();
		assertThat(subscription).isNotNull();
	}
	
	@Test
	@Order(2)
	public void testSubscription() {
		Subscription subscription = this.controller.subscription("History of Amazon Valley", "Ross Suarez", "CaseStudy");
		assertThat(subscription).isNotNull();
		assertThat(subscription).hasFieldOrPropertyWithValue("subscriberName", "CaseStudy");
	}
	
	@Test
	@Order(3)
	public void testUnsubscription() {
		Subscription subscription = this.controller.unsubscription("History of Amazon Valley", "Ross Suarez", "CaseStudy");
		assertThat(subscription).isNotNull();
		assertThat(subscription).hasFieldOrPropertyWithValue("subscriberName", "CaseStudy");
	}
}