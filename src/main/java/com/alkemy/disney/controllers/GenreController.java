
package com.alkemy.disney.controllers;

import com.alkemy.disney.models.Genre;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.disney.services.IGenreService;

@RestController
@RequestMapping("/genres")
public class GenreController {
    
    @Autowired
    private IGenreService genreService;
    
    
    @GetMapping()
    public List<Genre> obtenerGeneros(){
        return this.genreService.findAllGenres();
    }
    
    @PostMapping("/create")
    public Genre guardarGenero(@RequestBody Genre genre){
        return this.genreService.createGenre(genre);
    }
    
}
