package fi.hh.movies;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import fi.hh.movies.domain.Category;
import fi.hh.movies.domain.Movie;
import fi.hh.movies.domain.MovieRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
	public class TokaTesti {
		@Autowired
		private MovieRepository repository;
		
		@Test
		public void createNewMovie() {
		Movie movie = new Movie("Leffa", "Ohjaaja", 2000, 2 , new Category("Action"));
		repository.save(movie);
		assertThat(movie.getId()).isNotNull();
		}
		
		@Test
		public void newMovieName() {
		Movie movie = new Movie("Leffa", "Ohjaaja", 2000, 2 , new Category("Action"));
		repository.save(movie);
		assertThat(movie.getId()).isNotNull();
		assertThat(movie.getTitle()).startsWith("L").endsWith("a");
		}
	}
