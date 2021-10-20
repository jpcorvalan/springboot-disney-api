
package com.alkemy.disney.controllers;

import com.alkemy.disney.models.Genero;
import com.alkemy.disney.services.IGeneroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generos")
public class GeneroController {
    
    @Autowired
    private IGeneroService generoService;
    
    
    @GetMapping()
    public List<Genero> obtenerGeneros(){
        return this.generoService.obtenerGeneros();
    }
    
    @PostMapping("/crear")
    public Genero guardarGenero(@RequestBody Genero genero){
        return this.generoService.crearGenero(genero);
    }
    
}
