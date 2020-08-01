package com.fis.casestudy.mstraining;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
	
	@Override
    List<Book> findAll();
}