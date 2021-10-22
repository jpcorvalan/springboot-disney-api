
package com.alkemy.disney.controllers;

import com.alkemy.disney.models.Show;
import com.alkemy.disney.models.Character;
import com.alkemy.disney.services.ICharacterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.disney.services.IShowService;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;

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
    public Show findShowById(@PathVariable("id") int id){
        
        try{
            
            Show findedShow = showService.findShowById(id);
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
            
            
            return findedShow;            
            
        } catch (Exception e) {
            
            return null;
            
        }
        
    }
    
    @PostMapping("/create")
    public Show guardarPelicula(@RequestBody Show show){        
        return this.showService.saveShow(show);
    }
    
}
