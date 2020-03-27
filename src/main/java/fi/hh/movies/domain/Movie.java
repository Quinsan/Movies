package fi.hh.movies.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Movie {
	
	//Tämän piti olla IDENTITY, ei toiminut muuten
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//VAlidointia, messaget on mitä error messageita näyttää
	@Size(min=1, message="Please fill in this field")
	private String title, director;
	@Max(value=2020, message="Year can't be in the future")
	private int year;
	@Min(value=1, message="Must be between 1 and 5")
	@Max(value=5, message="Must be between 1 and 5")
	private int rating;
	
	//manytoone tässä luokassa, koska owner, ja linkataan joincolulla databasessa mikä on foreign key jolla yhistetään
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="categoryid")
	Category category;
	
	public Movie() {
		super();
	}

	public Movie(String title, String director, int year, int rating) {
		super();
		this.title = title;
		this.director = director;
		this.year = year;
		this.rating = rating;
	}
	
	public Movie(String title, String director, int year, int rating, Category category) {
		super();
		this.title = title;
		this.director = director;
		this.year = year;
		this.rating = rating;
		this.category = category;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", year=" + year + ", rating="
				+ rating + "]";
	}

}
