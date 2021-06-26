package com.pbs.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbs.dto.BookDTO;
import com.pbs.mapper.BookMapper;
import com.pbs.model.Book;
import com.pbs.service.BookMongoService;

@RestController
@RequestMapping("BookManager")
public class BookMongoController {

	@Autowired
	private BookMongoService bookMongoService;

	@Autowired
	private BookMapper bookMapper;

	// -------------------Retrieve All Books-------------------------------------------------------- 

	@GetMapping(value = "/books/")
	public ResponseEntity<List<BookDTO>> listAllBooks(Pageable pageable) {
		Page<Book> books = bookMongoService.findAllBooks(pageable);
		if (books.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<BookDTO>>(bookMapper.toListDTO(books.getContent()), HttpStatus.OK);
	}

	// -------------------Retrieve Single Book--------------------------------------------------------

	@GetMapping(value = "/book/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<BookDTO> getBook(@PathVariable("id") long id) {
		BookDTO bookDTO = bookMapper.toDTO(bookMongoService.findById(id));
		if (bookDTO == null) {
			return new ResponseEntity<BookDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<BookDTO>(bookDTO, HttpStatus.OK);
	}

	// -------------------Create a Book--------------------------------------------------------

	@PostMapping(value = "/book/")
	public ResponseEntity<BookDTO> createBook(@RequestBody @Valid BookDTO bookDTO) {

		if (bookMongoService.isBookExist(bookMapper.fromDTO(bookDTO))) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>(bookMapper.toDTO(bookMongoService.insertBook(bookMapper.fromDTO(bookDTO))),
				HttpStatus.CREATED);
	}

	// ------------------- Update a Book --------------------------------------------------------

	@PutMapping(value = "/book/{id}")
	public ResponseEntity<BookDTO> updateBook(@PathVariable("id") long id, @RequestBody @Valid BookDTO bookDTO) {

		if (bookMongoService.findById(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(bookMapper.toDTO(bookMongoService.updateBook(bookMapper.fromDTO(bookDTO))),
				HttpStatus.OK);
	}

	// ------------------- Delete a Book --------------------------------------------------------

	@DeleteMapping(value = "/book/{id}")
	public ResponseEntity deleteBook(@PathVariable("id") long id) {
		if (bookMongoService.findById(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		bookMongoService.deleteBookById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}