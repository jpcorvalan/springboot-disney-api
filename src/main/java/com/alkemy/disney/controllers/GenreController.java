
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
import java.util.HashMap;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    
    
    
    
    // Si el cuerpo de la petición es inválido, arrojará un mensaje aclarándolo
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> invalidMessage(){
        String errorMsg = "La petición solicitada es inválida";
        return new ResponseEntity<>(errorMsg, HttpStatus.NO_CONTENT);
    }
    
}
