
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
import javax.validation.Valid;

@RestController
@RequestMapping("/movies")
public class ShowController {
    
    @Autowired
    private IShowService showService;
    private ICharacterService characterService;
    
    
    
    
    @GetMapping()
    public List<Show> findAllShows(){
        List<Show> allShows = this.showService.findAllShows();
        List<Show> mappedShows = new ArrayList<>();
        
        for(Show show : allShows){
            Show auxShow = Show.builder()
                    .imageUrl(show.getImageUrl())
                    .title(show.getTitle())
                    .releaseDate(show.getReleaseDate())
                    .build();
            
            mappedShows.add(auxShow);
        }
        
        return mappedShows;
        
    }
    
    
    
    
    @GetMapping(path = "{id}")
    public ResponseEntity<Show> findShowById(@PathVariable("id") int id){
        
        Show findedShow = showService.findShowById(id);
        
        if(findedShow != null){
            
            List<Character> showCharacters = findedShow.getCharacters();
            List<Character> mappedCharacters = new ArrayList<>();
            
            for(Character chara : showCharacters){
                
                Character auxCharacter = Character.builder()
                        .imageUrl(chara.getImageUrl())
                        .name(chara.getName())
                        .build();
                
                mappedCharacters.add(auxCharacter);
                
            }
            
            findedShow.setCharacters(mappedCharacters);
            
            
            return new ResponseEntity<>(findedShow, HttpStatus.OK);
            
        } else {
            throw new ModelNotFoundException("El show con el id " + id + "no existe");
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
