package com.pbs;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pbs.model.Book;
import com.pbs.repository.BookMongoRepository;

@SpringBootApplication(scanBasePackages = { "com.pbs" })
public class EBooksfyApplication implements CommandLineRunner {

	@Autowired
	BookMongoRepository bookMongoRepository;

	public static void main(String[] args) {
		SpringApplication.run(EBooksfyApplication.class, args);
	}

	@Override
	public void run(String... args) {
		bookMongoRepository.deleteAll();

		final Book book = new Book("Libro1", 2021, true, LocalDateTime.now());
		final Book book2 = new Book("Libro2", 2021, true, LocalDateTime.now());

		bookMongoRepository.save(book);
		bookMongoRepository.save(book2);

	}

}
