package com.pbs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pbs.model.Book;

public interface BookMongoService {

	Book findByName(String name);

	Page<Book> findAllBooks(Pageable pageable);

	Book findById(long id);

	boolean isBookExist(Book book);

	Book saveOrUpdateBook(Book book);

	void deleteBookById(long id);

}