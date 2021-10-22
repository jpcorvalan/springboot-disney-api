
package com.alkemy.disney.controllers;

import com.alkemy.disney.models.Character;
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

@RestController
@RequestMapping("/characters")
public class CharacterController {
    
    @Autowired
    private ICharacterService characterService;
    
    @GetMapping()
    public List<Character> obtenerPersonajes(){
        return this.characterService.findAllCharacters();
    }
    
    @GetMapping(path = "/{id}")
    public Character obtenerPersonajePorId(@PathVariable("id") int id){
        return this.characterService.findCharacterById(id);
    }
    
    @PostMapping("/crear")
    public Character guardarPersonaje(@RequestBody Character personaje){        
        return this.characterService.saveCharacter(personaje);
    }
    
    
}
