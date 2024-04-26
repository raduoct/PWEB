package com.example.GoingPlaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoingPlacesApplication {

	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(GoingPlacesApplication.class, args);
	}

}
