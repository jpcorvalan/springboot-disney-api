
package com.alkemy.disney.controllers;

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
    public Character obtenerPersonajePorId(@PathVariable("id") int id){
        
        try{
            Character characterFinded = this.characterService.findCharacterById(id);

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

            return characterFinded;
            
        } catch(Exception e) {
            
            return null;
            
        }
    }
    
    @PostMapping("/crear")
    public Character guardarPersonaje(@RequestBody Character personaje){        
        return this.characterService.saveCharacter(personaje);
    }
    
    
}
