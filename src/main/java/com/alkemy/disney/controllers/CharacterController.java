
package com.alkemy.disney.controllers;

import com.alkemy.disney.exceptions.ModelNotFoundException;
import com.alkemy.disney.models.Character;
import com.alkemy.disney.services.ICharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import java.util.HashMap;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    
    
    
    
    @Autowired
    private ICharacterService characterService;
    
    
    
    
    /*
    @GetMapping("/query")
    public List<Character> innerJoin(){
        List<Character> allQueryCharacters = characterService.joinCharactersShows(idShow);
        
        for(Character chara : allQueryCharacters){
            chara.setShows(ListMapper.showListMapper(chara.getShows()));
        }
        
        return allQueryCharacters;        
    }
    */
    
    
    

    @GetMapping()
    public List<Character> obtenerPersonajes(
            @RequestParam(required = false) String name, 
            @RequestParam(required = false) Double weight, 
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Integer show
    ){
        
        // Si los parámetros solicitados no vienen en el query, se muestran todos los personajes
        if(name == null && weight == null && age == null && show == null){
            return ListMapper.nullifiqueCharactersNonUsedColumns(this.characterService.findAllCharacters());
        } else {
            List<Character> queryCharacters = this.characterService.findByNameOrAgeOrWeight(name, age, weight);
            List<Character> joinShowsTable = this.characterService.joinCharactersShows(show);
            
            for(Character chara : joinShowsTable){
                queryCharacters.add(chara);
            }
            
            // Si hubo querys, pero la búsqueda no devolvió nada, se devuelve el arreglo vacío, caso contrario se realiza el mapeo.
            if(queryCharacters.isEmpty()){                
                return queryCharacters;
            }else{
                return ListMapper.nullifiqueCharactersNonUsedColumns(queryCharacters);
            }
            
        }
        
    }
    
    
    
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<Character> obtenerPersonajePorId(@PathVariable("id") int id){
        
        Character characterFinded = this.characterService.findCharacterById(id);
        
        if(characterFinded != null){            
            
            characterFinded.setShows(ListMapper.nullifiqueShowsNonUsedColumns(characterFinded.getShows()));

            return new ResponseEntity<>(characterFinded, HttpStatus.OK);
            
        } else {            
            throw new ModelNotFoundException("El personaje con el id " + id + " no existe");            
        }
    }
    
    
    
    
    @PostMapping("/create")
    public ResponseEntity<Object> guardarPersonaje(@Valid @RequestBody Character personaje, Errors errors) {
        try{
            return new ResponseEntity<>(this.characterService.saveCharacter(personaje), HttpStatus.CREATED);            
        }catch(Exception e){
            
            // Creamos un HashMap para combinar los campos con error, con el mensaje de error que se obtiene
            HashMap<String, String> mappedErrors = new HashMap<>();
            
            // Insertamos las claves valores dentro del HashMap
            for(FieldError validation : errors.getFieldErrors()){
                mappedErrors.put(validation.getField(), validation.getDefaultMessage());
            }
            
            // Retornamos un Array clave/valor, donde se puede visualizar el campo con error más la razón del error.
            return new ResponseEntity<>(mappedErrors, HttpStatus.BAD_REQUEST);
        }            
    }
    
    
    
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Character> eliminarPersonaje(@PathVariable("id") Integer id){
        Character characterFinded = characterService.findCharacterById(id);
        
        if(characterFinded != null){
            // Primero necesitamos borrar el Character de la tabla intermedia que lo vincula con Show
            characterService.deleteCharacterShowRel(characterFinded.getIdCharacter());

            // Luego, se borra el Character pedido
            characterService.deleteCharacter(characterFinded.getIdCharacter());

            return new ResponseEntity<>(characterFinded, HttpStatus.OK);            
        } else {
            throw new ModelNotFoundException("El personaje con el id " + id + " no existe");
        }
    }
    
    
    
    
    // Si el cuerpo de la petición es inválido, arrojará un mensaje aclarándolo
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> invalidMessage(){
        String errorMsg = "La petición solicitada es inválida";
        return new ResponseEntity<>(errorMsg, HttpStatus.NO_CONTENT);
    }
    
    
}
