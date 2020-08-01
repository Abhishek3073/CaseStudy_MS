package com.fis.casestudy.mstraining;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

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
	@HystrixCommand(fallbackMethod="fallbackForSubscribeBook")
	public String subscribebook(@RequestParam String bookname, @RequestParam String author) {
		return bookService.subscribebook(bookname, author);	
	}
	
	@PostMapping("/unsubscribebook")
	@HystrixCommand(fallbackMethod="fallbackForUnSubscribeBook")
	public String unsubscribebook(@RequestParam String bookname, @RequestParam String author) {
		return bookService.unsubscribebook(bookname, author);
	}
	
	public String fallbackForSubscribeBook(@PathVariable String bookname, @PathVariable String author) {
        return "Book = " + bookname + ", written by author = " + author + " is not available right now, please check later";
    }
	
	public String fallbackForUnSubscribeBook(@PathVariable String bookname, @PathVariable String author) {
        return "Book = " + bookname + ", written by author = " + author + " is already unscriberd by all subscribers";
    }
}
