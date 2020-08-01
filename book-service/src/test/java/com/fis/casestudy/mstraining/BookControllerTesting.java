package com.fis.casestudy.mstraining;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookControllerTesting{

	@Autowired 
	BookController controller;
	
	@Test
	@Order(1)
	public void testBooks() {
		List<Book> books = this.controller.retrieveBooks();
		assertThat(books).isNotNull();
	}
	
	@Test
	@Order(2)
	public void testSubscribeBook() {
		String book = this.controller.subscribebook("History of Amazon Valley", "Ross Suarez");
		assertThat(book).isNotNull();
	}
	
	@Test
	@Order(3)
	public void testUnsubscribeBook() {
		String book = this.controller.unsubscribebook("History of Amazon Valley", "Ross Suarez");
		assertThat(book).isNotNull();
	}
}