package com.alkemy.disney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.alkemy.models")
public class DisneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisneyApiApplication.class, args);
	}

}
