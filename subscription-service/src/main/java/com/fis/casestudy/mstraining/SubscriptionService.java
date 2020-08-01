package com.fis.casestudy.mstraining;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class SubscriptionService {
	
	@Autowired
	private BookServiceProxy proxy;
	
	@Autowired
	private SubscriberRepository subscriberRepository;
	
	public List<Subscription> retrieveSubscriptions() {
		List<Subscription> subscribersList = subscriberRepository.findAll();
		return subscribersList ;
	}
	
	@Transactional
	public Subscription subscription(@RequestParam String bookname, @RequestParam String author, @RequestParam String subscribername) {
		List<Book> response = proxy.retrieveBooks();
		Subscription savedSubscription = null;
		
		for (Book book : response) {
			if (book.getBookName().equalsIgnoreCase(bookname) && book.getAuthor().equalsIgnoreCase(author)) {
				if(book.getAvailableCopies() > 0) {
					try {
						proxy.subscribebook(bookname, author);
					}catch(Exception ex) {
						throw new RuntimeException("Book is not available this time, please check later");
					}
					
					Subscription subscription = new Subscription();
					subscription.setSubscriberName(subscribername);
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					subscription.setDateSubscibed(sdf.format(new Date()));
					subscription.setBookId(book.getBookId());
					savedSubscription = subscriberRepository.save(subscription);
				} else {
					throw new RuntimeException("Book is not available this time, please check later");
				}
				break;
			}
		}
		
		return savedSubscription;
	}
	
	@Transactional
	public Subscription unsubscription(@RequestParam String bookname, @RequestParam String author, @RequestParam String subscribername) {
		List<Book> booksResponse = proxy.retrieveBooks();
		Subscription updatedSubscription = null;
		
		List<Subscription> subscriptionsList = subscriberRepository.findBySubscriberName(subscribername);
		for(Subscription subscription : subscriptionsList) {
			for (Book book : booksResponse) {
				if(book.getAvailableCopies() < book.getTotalCopies() && subscription.getBookId().equalsIgnoreCase(book.getBookId()) && subscription.getSubscriberName().equalsIgnoreCase(subscribername)) {
					try {
						proxy.unsubscribebook(bookname, author);
					}catch(Exception ex) {
						throw new RuntimeException("not a valid unscribebook call");
					}
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					subscription.setDateReturned(sdf.format(new Date()));
					updatedSubscription = subscriberRepository.save(subscription);
				}else {
					throw new RuntimeException("Either this user is not associated with this book or already unscribed");
				}
				break;
			}
		}
		 
		return updatedSubscription;
	}
}
