package com.pbs.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BookDTO {
	
	private String id;

	@NotEmpty
	private String name;

	private Integer year;

	private boolean read;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateInclusion;

	public BookDTO() {

	}

	public BookDTO(String name, Integer year, boolean read) {
		this.name = name;
		this.year = year;
		this.read = read;
	}

}