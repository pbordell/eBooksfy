package com.pbs.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.pbs.dto.BookDTO;
import com.pbs.model.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

	Book fromDTO(BookDTO source);

	BookDTO toDTO(Book source);

	List<BookDTO> toListDTO(List<Book> source);

}
