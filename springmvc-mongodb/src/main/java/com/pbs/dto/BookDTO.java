package com.pbs.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BookDTO {

	@NotEmpty
	private String name;

	private Integer year;

	private boolean read;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dateInclusion;

	public BookDTO() {

	}

	public BookDTO(String name, Integer year, boolean read) {
		this.name = name;
		this.year = year;
		this.read = read;
	}

}