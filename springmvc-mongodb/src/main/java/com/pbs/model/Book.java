package com.pbs.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "book")
public class Book {

	@Transient
    public static final String SEQUENCE_NAME = "books_sequence";
	
	@Id
	private long id;

	@Indexed(unique = true)
	private String name;

	private Integer year;

	private boolean read;

	private LocalDate dateInclusion;

	public Book() {

	}

	public Book(String name, Integer year, boolean read, LocalDate dateInclusion) {
		this.name = name;
		this.year = year;
		this.read = read;
		this.dateInclusion = dateInclusion;
	}

}