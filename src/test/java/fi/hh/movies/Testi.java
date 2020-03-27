package fi.hh.movies;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import fi.hh.movies.web.MoviesController;

/**
* Testing that the context is creating your controller
*/
@RunWith(SpringRunner.class)
@SpringBootTest
	public class Testi {
	@Autowired
	private MoviesController controller;
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
		}
	}