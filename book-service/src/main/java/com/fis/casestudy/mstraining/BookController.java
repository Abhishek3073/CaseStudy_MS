package com.fis.casestudy.mstraining;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	public List<Book> retrieveBooks() {
		List<Book> bookList = bookService.retrieveBooks();
		return bookList ;
	}
	
	@PostMapping("/subscribebook")
	public String subscribebook(@RequestParam String bookname, @RequestParam String author) {
		return bookService.subscribebook(bookname, author);	
	}
	
	@PostMapping("/unsubscribebook")
	public String unsubscribebook(@RequestParam String bookname, @RequestParam String author) {
		return bookService.unsubscribebook(bookname, author);
	}
}