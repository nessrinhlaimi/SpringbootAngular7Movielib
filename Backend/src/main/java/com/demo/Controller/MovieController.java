package com.demo.Controller;

import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entities.Movie;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repositories.MovieRepository;
import com.demo.service.MovieService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/v1")
public class MovieController {

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	MovieService movieService;

	@GetMapping("/movies")
	public List<Movie> getAllMovie() {
		
	  return   movieService.getAllMovies();
}
	
	@GetMapping("/movies/{title}")
	public Movie getMoviesBytitle(@PathVariable String title) {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Movie>> typeReference = new TypeReference<List<Movie>>() {
		};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/movies.json");
		Movie mv = new Movie();
		try {
			List<Movie> movies = mapper.readValue(inputStream,typeReference);

	     movies.stream().forEach(movie -> {    
	    	  if (movie.getTitle().equals(title)) {
	    		
	    		  mv.setTitle(movie.getTitle());
	    		  mv.setDirector(movie.getDirector());
	    		  mv.setType(movie.getType());
	    		  mv.setReleaseDate(movie.getReleaseDate());
	    		
	    	  }
	    	 
	      });
	     return mv;
	     
	     
		} catch (IOException e) {
			System.out.println("Unable to save users: " + e.getMessage());
			
		}
		return null;
		
       
	}
	
	@PostMapping( "/movies/create"    
)
public Movie createMovie( @RequestBody  Movie movie) {
		
		System.out.println(movie);
		
		return  movieService.addMovie(movie);		 		 
		
}
	
	@DeleteMapping("/movies/{title}")
	public ResponseEntity<Void> deleteMovieByTitle(@PathVariable String title){
   List<Movie> movies = movieService.getAllMovies();
		
		movieService.deleteMovieByTitle(title);
		
		List<Movie> moviesafterdelete = movieService.getAllMovies();
	
	return new ResponseEntity<>(HttpStatus.ACCEPTED);
}

	@PutMapping("/movies/{title}")
	public ResponseEntity<Void> updateMovie(@PathVariable String title, @RequestBody Movie updatedMovie) {
		 movieService.updateMovieByTitle(title, updatedMovie);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}


}

