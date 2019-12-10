package com.demo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Movie {

	@Column(name = "id")
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "title")
	@NotNull
	private String title;

	@Column(name = "director")
	@NotNull
	private String director;

	@Column(name = "type")
	@NotNull
	private String type;

	@Column(name = "release_date")
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "dd/mm/yyyy")
	@Temporal(TemporalType.DATE)
	private Date releaseDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movie( @NotNull String title, @NotNull String director, @NotNull String type,
			@NotNull Date releaseDate) {
		super();
		this.title = title;
		this.director = director;
		this.type = type;
		this.releaseDate = releaseDate;
	}

	public static Movie of(String title, String director, String type, Date releaseDate) {
		// TODO Auto-generated method stub
		return null;
	}

	}

