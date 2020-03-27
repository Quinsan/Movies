package fi.hh.movies;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hh.movies.domain.Category;
import fi.hh.movies.domain.CategoryRepository;
import fi.hh.movies.domain.Movie;
import fi.hh.movies.domain.MovieRepository;
import fi.hh.movies.domain.User;
import fi.hh.movies.domain.UserRepository;

@SpringBootApplication
public class MoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner movieDemo(MovieRepository mrepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			
			crepository.save(new Category("Action"));
			crepository.save(new Category("Drama"));
			crepository.save(new Category("Adventure"));
			
			mrepository.save(new Movie("LOTR Fellowship of the ring", "Peter Jackson", 2001, 5, crepository.findByName("Adventure").get(0)));
			mrepository.save(new Movie("LOTR Two Towers", "Peter Jackson", 2002, 4, crepository.findByName("Adventure").get(0)));
			mrepository.save(new Movie("LOTR The Return of the King", "Peter Jackson", 2003, 5, crepository.findByName("Adventure").get(0)));
			
			//Create users: admin/admin user/user
			User user1 = new User("user" ,"$2y$12$u5jD/jT64NDTV1P6cuD2G.wqksFMsfRFXoQ.9ToLJqP04dU.OEldu", "USER");
			User user2 = new User("admin" ,"$2y$12$RugOKCePvK7F9U8u7sb66uER0tsNJqLAfS35LuJzwLUJZpsekLuNO", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
		};
		
	}

}
