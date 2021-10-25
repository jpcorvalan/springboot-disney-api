
package com.alkemy.disney.controllers;

import com.alkemy.disney.exceptions.ModelNotFoundException;
import com.alkemy.disney.models.Character;
import com.alkemy.disney.models.Show;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.disney.services.ICharacterService;
import com.alkemy.disney.services.IShowService;
import java.util.HashMap;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    
    @Autowired
    private ICharacterService characterService;
    private IShowService showService;
    
    
    
    
    @GetMapping()
    public List<Character> obtenerPersonajes(){
        List<Character> allCharacters = this.characterService.findAllCharacters();
        List<Character> mappedCharacters = new ArrayList<>();
        
        for (Character chara : allCharacters) {
            Character auxCharacter = Character.builder()
                    .imageUrl(chara.getImageUrl())
                    .name(chara.getName())
                    .build();
            
            mappedCharacters.add(auxCharacter);
        }
        
        return mappedCharacters;
        
    }
    
    
    
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<Character> obtenerPersonajePorId(@PathVariable("id") int id){
        
        Character characterFinded = this.characterService.findCharacterById(id);
        
        if(characterFinded != null){

            List<Show> characterShows = characterFinded.getShows();
            List<Show> mappedShows = new ArrayList<>();

            for(Show show : characterShows){
                Show auxShow = Show.builder()
                        .imageUrl(show.getImageUrl())
                        .title(show.getTitle())
                        .releaseDate(show.getReleaseDate())
                        .build();

                mappedShows.add(auxShow);
            }

            characterFinded.setShows(mappedShows);

            return new ResponseEntity<>(characterFinded, HttpStatus.OK);
            
        } else {            
            throw new ModelNotFoundException("El personaje con el id " + id + " no existe");            
        }
    }
    
    
    
    
    @PostMapping("/crear")
    public ResponseEntity<Object> guardarPersonaje(@Valid @RequestBody Character personaje, Errors errors) {
        try{
            return new ResponseEntity<>(this.characterService.saveCharacter(personaje), HttpStatus.CREATED);            
        }catch(Exception e){            
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
