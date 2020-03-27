package fi.hh.movies.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	//jotta saadaan haettua kategorioita nimella findbyname
	List<Category> findByName(String name);

}
