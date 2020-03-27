package fi.hh.movies.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.hh.movies.domain.CategoryRepository;
import fi.hh.movies.domain.Movie;
import fi.hh.movies.domain.MovieRepository;

@Controller

public class MoviesController {
	
	@Autowired	
	private MovieRepository movieRepository;
	
	@Autowired	
	private CategoryRepository categoryRepository;
	
	//sisäänkirjaussivu, osoitteessa /login palautetaan login.html
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	//kaikki juurikutsut osoitetaan loginsivulle,  tässä kyllä apit löytyy esim api/movies, ei pääse /api
	 @RequestMapping("/*") public String redirect() {
		 return "index"; 
		 }
	 
	
	//Rest palvelun kautta listaa kaikki leffat, kun mennään osoite/movies
	  @RequestMapping(value="/movies", method = RequestMethod.GET)
	  public @ResponseBody List<Movie> movieListRest() { return (List<Movie>)
			  movieRepository.findAll();
	  }
	
	
	//kun mennään osoite/movielist, palautettaan lista leffoista, käytetään repositoryä, jossa CRUD ominaisuudet olemassa, palautetaan näkymä movieList thymeleaf
	@RequestMapping("/movielist")
	public String movieList(Model model) {
		model.addAttribute("movies", movieRepository.findAll());
		return "movielist";
	}
	
	//kun mennään osoite/addMovie tais siis painetaan add, tehdään tyhjä Movieclassi (object movie formissa) jota käytetään ja johon lisätään tiedot
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping("/addMovie")
	public String addMovie(Model model) {
				
		model.addAttribute("movie", new Movie());
		//haetaan kategoriarepositoryn avulla kaikki kategoriat tähän ja lähetetään add templatelle, pitää olla templatella saman niminen categories
		model.addAttribute("categories", categoryRepository.findAll());
		return "addMovie";
	}
	
	//painettaessa submit lisätessä uutta leffaa kuunnellaan formin actionia, tallennetaan uusi leffa ja palautetaan listasivulle
	//TEhdään myös validointi, jos on erroria, palautetaan lomakkeelle errorien kanssa, pitää antaa myös ketegoria, muuten menee tyhjänä
	@PostMapping("saveMovie")
	public String saveMovie(@Valid Movie movie, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", categoryRepository.findAll());
			return "addMovie";
		}
		
        movieRepository.save(movie);
        return "redirect:/movielist";
		
	}
		
	//kun painetaan delete nappia, tulee sen leffan id tähän, joka tallennetaan MovieId, ja poistetaan se id repositoryCRUD, lopuksi redirect listaan
	//tämä varmistaa, että vain ADMIN roolilla voi ajaa poista
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteMovie(@PathVariable("id") Long MovieId) {
		movieRepository.deleteById(MovieId);
        return "redirect:../movielist";
		
	}

}
