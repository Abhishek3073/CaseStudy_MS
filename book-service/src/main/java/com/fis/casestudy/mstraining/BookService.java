package com.fis.casestudy.mstraining;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> retrieveBooks() {
		List<Book> bookList = bookRepository.findAll();
		return bookList ;
	}
	
	@Transactional
	public String subscribebook(@RequestParam String bookname, @RequestParam String author) {
		List<Book> bookList = bookRepository.findAll();
		
		for (Book book : bookList) {
			if (book.getBookName().equalsIgnoreCase(bookname) && book.getAuthor().equalsIgnoreCase(author)) {
				if(book.getAvailableCopies() > 0) {
					book.setAvailableCopies(book.getAvailableCopies()-1);
					bookRepository.save(book);
				} else {
					throw new RuntimeException("Requested Book is not available this time, please check later");
				}
				break;
			}
		}
		
		return "Book Subscribed Successfully";
	}
	
	@Transactional
	public String unsubscribebook(@RequestParam String bookname, @RequestParam String author) {
		List<Book> bookList = bookRepository.findAll();
		
		for (Book book : bookList) {
			if (book.getBookName().equalsIgnoreCase(bookname) && book.getAuthor().equalsIgnoreCase(author)) {
				if(book.getAvailableCopies() < book.getTotalCopies()) {
					book.setAvailableCopies(book.getAvailableCopies()+1);
					bookRepository.save(book);
				} else {
					throw new RuntimeException("not a valid unscribebook call");
				}
				break;
			}
		}
		
		return "Book UnSubscribed Successfully";
	}
}
