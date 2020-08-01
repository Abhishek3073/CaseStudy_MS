package com.fis.casestudy.mstraining;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="api-gateway-library-service")
@RibbonClient(name="book-service")
public interface BookServiceProxy {
     @GetMapping("/book-service/books")
     public List<Book> retrieveBooks();
     
     @PostMapping("/book-service/subscribebook")
     public String subscribebook(@RequestParam("bookname") String bookName, @RequestParam("author") String author);
     
     @PostMapping("/book-service/unsubscribebook")
     public String unsubscribebook(@RequestParam("bookname") String bookName, @RequestParam("author") String author);
}
