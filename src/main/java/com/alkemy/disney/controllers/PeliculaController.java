
package com.alkemy.disney.controllers;

import com.alkemy.disney.models.Pelicula;
import com.alkemy.disney.services.IPeliculaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class PeliculaController {
    
    @Autowired
    private IPeliculaService peliculaService;
    
    @GetMapping()
    public List<Pelicula> obtenerPeliculas(){
        return this.peliculaService.obtenerPeliculas();
    }
    
    @PostMapping("/crear")
    public Pelicula guardarPelicula(@RequestBody Pelicula pelicula){
        return this.peliculaService.guardarPelicula(pelicula);
    }
    
}
