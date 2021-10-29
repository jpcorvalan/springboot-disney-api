
package com.alkemy.disney.controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.alkemy.disney.exceptions.ModelNotFoundException;
import com.alkemy.disney.models.Show;
import com.alkemy.disney.models.Character;
import com.alkemy.disney.services.ICharacterService;
import com.alkemy.disney.services.IShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/movies")
public class ShowController {
    
    @Autowired
    private IShowService showService;
    private ICharacterService characterService;
    
    
    
    /*
    @GetMapping()
    public List<Show> findAllShows(){        
        return ListMapper.showListMapper(this.showService.findAllShows());        
    }
    */
    
    
    @GetMapping()
    public List<Show> findShows(
            @RequestParam(required = false) Integer genre,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String order
    ){
        
        if (genre == null && title == null && order == null){
            return ListMapper.showListMapper(showService.findAllShows());
        } else {
            List<Show> showsByName = (title != null) ? showService.findByTitle(title) : new ArrayList<>();
            List<Show> showsByGenre = (genre != null) ? showService.findShowsByGenre(genre) : new ArrayList<>();
            List<Show> orderedShows = new ArrayList<>();
            
            if(order != null){
                switch(order){
                    case "ASC":
                        orderedShows = ListMapper.showListMapper(showService.orderAllByScoreAsc());
                        return orderedShows;
                    case "DESC":
                        orderedShows = ListMapper.showListMapper(showService.orderAllByScoreDesc());
                        return orderedShows;
                }                
            }
            
            showsByName.addAll(showsByGenre);
            
            return ListMapper.showListMapper(showsByName);
        }
        
    }
    
    
    
    @GetMapping(path = "{id}")
    public ResponseEntity<Show> findShowById(@PathVariable("id") int id){
        
        Show findedShow = showService.findShowById(id);
        
        if(findedShow != null){
            
            findedShow.setCharacters(ListMapper.characterListMapper(findedShow.getCharacters()));
            
            return new ResponseEntity<>(findedShow, HttpStatus.OK);
            
        } else {
            throw new ModelNotFoundException("El show con el id " + id + " no existe");
        }
        
    }
    
    
    
    
    @PostMapping("/create")
    public ResponseEntity<Object> saveShow(@RequestBody @Valid Show show, Errors errors) throws InvalidFormatException{
        try{
            return new ResponseEntity<>(showService.saveShow(show), HttpStatus.CREATED);
        } catch (Exception e){
            HashMap<String, String> mappedErrors = new HashMap<>();
            
            for(FieldError validation : errors.getFieldErrors()){
                mappedErrors.put(validation.getField(), validation.getDefaultMessage());
            }
            
            return new ResponseEntity<>(mappedErrors, HttpStatus.BAD_REQUEST);
        }
    }
    
    
    
    
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Show> deleteShow(@PathVariable("id") Integer id){
        Show showFinded = showService.findShowById(id);
        
        if(showFinded != null){
            
            // Borramos las relaciones que tiene Show con Character y Genre
            showService.deleteCharacterShowRel(showFinded.getIdShow());            
            showService.deleteGenreShowRel(showFinded.getIdShow());
            
            // Finalmente, borramos el show
            showService.deleteShowById(showFinded.getIdShow());
            
            return new ResponseEntity<>(showFinded, HttpStatus.OK);
        } else {
            throw new ModelNotFoundException("El personaje con el id " + id + " no existe");
        }
            
    }
    
    
    
    
    // Si la fecha está escrita en un formato inválido, arrojará una excepción que se manejará con este método
    @ExceptionHandler({InvalidFormatException.class})
    public ResponseEntity<Object> invalidDateFormat(){
        String errorMsg = "El formato de la fecha debe ser AAAA-mm-dd (Año-mes-día)";
        return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
    }
    
    
    
    
    // Si el cuerpo de la petición es inválido, arrojará un mensaje aclarándolo
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> invalidMessage(){
        String errorMsg = "La petición solicitada es inválida";
        return new ResponseEntity<>(errorMsg, HttpStatus.NO_CONTENT);
    }
    
}
