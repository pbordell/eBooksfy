package com.pbs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pbs.model.Book;

@Repository
public interface BookMongoRepository extends MongoRepository<Book, Long> {
	
	Book findByName(String name);
	
}