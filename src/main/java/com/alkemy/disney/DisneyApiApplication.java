package com.alkemy.disney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;


/*
    Se agregó la anotación @EntityScan("com.alkemy.models") con la carpeta donde están los modelos
    debido que, al ejecutar la aplicación, no se mapeaban las tablas en la base de datos
*/

@SpringBootApplication
//@EntityScan("com.alkemy.models")
public class DisneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisneyApiApplication.class, args);
	}

}
