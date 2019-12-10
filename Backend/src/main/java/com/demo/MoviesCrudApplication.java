package com.demo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.sun.xml.bind.v2.runtime.reflect.ListIterator;
import com.demo.entities.Movie;
import com.demo.service.MovieService;


@SpringBootApplication
//@ComponentScan(basePackages="{com.demo}")
public class MoviesCrudApplication implements CommandLineRunner {
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(MoviesCrudApplication.class, args);
	}
	

	
	
	


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
