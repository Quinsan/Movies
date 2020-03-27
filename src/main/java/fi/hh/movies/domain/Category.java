package fi.hh.movies.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long categoryid;
	private String name;
	
	//linkataan leffaan, tässä onetomany, koska voi olla useampi. mappedBy linkaa millä nimellä moviessa tämä on, tärkeä, pitää olla sama siis
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	//linkattu entity on nyt lista, kun voi olla useita leffoja
	private List<Movie> movies;
	
	public Category() {
		super();
	}
	
	public Category(String name) {
		super();
		this.name = name;
	}
	
	public long getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
	
	
	

}
