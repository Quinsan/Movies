package fi.hh.movies.domain;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
			//Luo CRUD toiminnallisuuden Movie entitylle, tämä on interface, ei class
}
