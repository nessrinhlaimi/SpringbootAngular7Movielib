package com.demo.service;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.entities.Movie;
import com.demo.repositories.MovieRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;

@Service
public class MovieService {
	private MovieRepository movieRepository;
	private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

	ObjectMapper objectMapper = new ObjectMapper();

	private List<Movie> movies = new ArrayList<Movie>();

	File json = new File("src/main/resources/json/movies.json");

	public MovieService() {
		super();
		
	}

	public ArrayNode getMoviesArrayNode() {
		ArrayNode root = null;
		try {
			root = (ArrayNode) objectMapper.readTree(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return root;
	}
	
	public List<Movie> getAllMovies() {

		List<Movie> movieList = null;
		try {

			movieList = objectMapper.readValue(json, new TypeReference<List<Movie>>() {
			});
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movieList;
	}
	


	public Movie addMovie(Movie movie) {
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);

			TypeReference<List<Movie>> typeReference = new TypeReference<List<Movie>>() {
			};
// 
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/movies.json"); 
			List<Movie> movies;
			FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/json/movies.json"); 
			try {
				try {
					movies = mapper.readValue(inputStream, typeReference);
				} catch (JsonMappingException e) {
					movies = new ArrayList<Movie>();
				}

				Movie moviee = new Movie( movie.getTitle(), movie.getDirector(), movie.getType(),
						movie.getReleaseDate());
				

				movies.add(moviee);
				mapper.writeValue(fileOutputStream, movies);
			 
				fileOutputStream.flush();
			

				System.out.println("Users Saved!"); 

				
			} catch (IOException e) {
				System.out.println("Unable to save users: " + e.getMessage());  
			}
			finally{
				fileOutputStream.flush();
				fileOutputStream.close();

				 
			}
			return movie;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;

	}


	public void deleteMovieByTitle(String title) {
		ArrayNode moviesNode = getMoviesArrayNode();

		for (int i = 0; i < moviesNode.size(); i++) {
			if (moviesNode.get(i).get("title").asText().equals(title)) {
				moviesNode.remove(i);
				break;
			}
		}

		try {
			objectMapper.writeValue(new File("src/main/resources/json/movies.json"), moviesNode);
			System.out.println("Users suuprim!");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Unable to save users: " + e1.getMessage());
			e1.printStackTrace();
		}
	}

	public void updateMovieByTitle(String title, Movie updatedMovie) {
	Movie mv = new Movie();
	
	ObjectMapper mapper = new ObjectMapper();
	mapper.enable(SerializationFeature.INDENT_OUTPUT);

	try {
      List<Movie> moviesFromJsonFile = new ObjectMapper().readValue( new File( "src/main/resources/json/movies.json" ), new TypeReference<List<Movie>>() {});
      moviesFromJsonFile.stream().forEach(movie -> {
    	  if (movie.getTitle().equals(title)) {
    		  movie.setDirector(updatedMovie.getDirector());
    		  movie.setTitle(updatedMovie.getTitle());
    		  movie.setReleaseDate(updatedMovie.getReleaseDate());
    		  movie.setType(updatedMovie.getType());
    	  }
      });
		FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/json/movies.json"); 

      mapper.writeValue(fileOutputStream, moviesFromJsonFile);
  	fileOutputStream.close();
  	System.out.println("movies update!"); 
	} catch(Exception e) {
		System.out.println("!"); 
		e.printStackTrace();
	}
	}
	 public Iterable<Movie> save(List<Movie> movies) {
	        return movieRepository.saveAll(movies);
	    }
	 
	 public Movie getMoviesBytitle(String title) {
			ObjectMapper mapper = new ObjectMapper();
			

			Movie mv = new Movie();
			
	         try {
		      List<Movie> moviesFromJsonFile = new ObjectMapper().readValue( new File( "src/main/resources/json/movies.json" ), new TypeReference<List<Movie>>() {});
		      moviesFromJsonFile.stream().forEach(movie -> {
		    	  if (movie.getTitle().equals(title)) {
		    		  mv.setDirector(movie.getDirector());
		    		  mv.setTitle(movie.getTitle());
		    		  mv.setReleaseDate(movie.getReleaseDate());
		    		  mv.setType(movie.getType());
		    	  }
		    	 
		      });
		      System.out.println("Users Saved!"); 
		     
			} catch (IOException e) {
				System.out.println("Unable to save users: " + e.getMessage());
				
			}
	         return mv;	
		}

}
