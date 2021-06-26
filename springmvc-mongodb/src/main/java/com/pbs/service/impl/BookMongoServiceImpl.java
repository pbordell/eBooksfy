package com.pbs.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pbs.model.Book;
import com.pbs.repository.BookMongoRepository;
import com.pbs.service.BookMongoService;

@Service("bookMongoService")
public class BookMongoServiceImpl implements BookMongoService {

	@Autowired
	private BookMongoRepository bookMongoRepository;

	public Book findByName(String name) {
		return bookMongoRepository.findByName(name);
	}

	@Override
	public Page<Book> findAllBooks(Pageable pageable) {
		return bookMongoRepository.findAll(pageable);
	}

	@Override
	public Book findById(long id) {
		return bookMongoRepository.findById(id).get();
	}

	@Override
	public boolean isBookExist(Book book) {
		return findByName(book.getName().trim()) != null;
	}

	@Override
	public Book insertBook(Book book) {
		book.setDateInclusion(LocalDate.now());
		return bookMongoRepository.insert(book);
	}
	
	@Override
	public Book updateBook(Book book) {
		return bookMongoRepository.save(book);
		
	}

	@Override
	public void deleteBookById(long id) {
		bookMongoRepository.deleteById(id);
	}

}