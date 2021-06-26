package com.pbs.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class BookDTO {

	@JsonIgnore
	private long id;

	@NotEmpty
	private String name;

	private Integer year;

	private boolean read;

	private LocalDateTime dateInclusion;

	public BookDTO() {

	}

	public BookDTO(long id, String name, Integer year, boolean read) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.read = read;
	}

}