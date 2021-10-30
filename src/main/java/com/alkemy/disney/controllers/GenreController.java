
package com.alkemy.disney.controllers;

import java.util.List;
import java.util.HashMap;
import javax.validation.Valid;
import com.alkemy.disney.models.Genre;
import com.alkemy.disney.services.IGenreService;
import com.alkemy.disney.exceptions.ModelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/genres")
public class GenreController {
    
    @Autowired
    private IGenreService genreService;
    
    
    
    
    @GetMapping()
    public List<Genre> obtenerGeneros(){
        List<Genre> allGenres = this.genreService.findAllGenres();
        
        for(Genre genre : allGenres){
            genre.setAsociatedShows(ListMapper.nullifiqueShowsNonUsedColumns(genre.getAsociatedShows()));
        }
        
        return allGenres;
    }
    
    
    
    
    @GetMapping(path = "/{id}")
    public Genre obtenerGeneroPorId(@PathVariable Integer id){
        return genreService.findGenreById(id);
    }
    
    
    
    
    @PostMapping("/create")
    public ResponseEntity<Object> guardarGenero(@RequestBody @Valid Genre genre, Errors errors){
        try{
            return new ResponseEntity<>(this.genreService.createGenre(genre), HttpStatus.CREATED);
        } catch (Exception e){
            HashMap<String, String> mappedErrors = new HashMap<>();
            
            for(FieldError validation : errors.getFieldErrors()){
                mappedErrors.put(validation.getField(), validation.getDefaultMessage());
            }
            
            return new ResponseEntity<>(mappedErrors, HttpStatus.BAD_REQUEST);
        }
    }
    
    
    
    
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Genre> eliminarGenero(@PathVariable("id") Integer id){
        Genre genreFinded = genreService.findGenreById(id);
        
        if(genreFinded != null){
            // Borramos primero la relación que tiene con los Show
            genreService.deleteGenreShowRel(genreFinded.getIdGenre());
            
            // Luego borramos el Género como tal
            genreService.deleteGenreById(genreFinded.getIdGenre());
            
            return new ResponseEntity<>(genreFinded, HttpStatus.OK);
        } else {
            throw new ModelNotFoundException("El género con el id " + id + " no existe");
        }
    }
    
    
    
    
    // Si el cuerpo de la petición es inválido, arrojará un mensaje aclarándolo
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> invalidMessage(){
        String errorMsg = "La petición solicitada es inválida";
        return new ResponseEntity<>(errorMsg, HttpStatus.NO_CONTENT);
    }
    
}
