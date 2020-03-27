package fi.hh.movies.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username); //tämä taas että voidaan tehdä kysely usernamella
}
