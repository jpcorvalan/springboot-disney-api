package com.alkemy.disney.controllers;

import java.util.List;
import java.util.ArrayList;
import com.alkemy.disney.models.Character;
import com.alkemy.disney.models.Show;

public class ListMapper {

    public static List<Character> characterListMapper(List<Character> characterList) {
        
        List<Character> mappedCharacters = new ArrayList<>();
        
        // Se crean nuevos Character con los atributos requeridos y se insertan en un nuevo Array
        for (Character chara : characterList) {
            Character auxCharacter = Character.builder()
                    .imageUrl(chara.getImageUrl())
                    .name(chara.getName())
                    .build();

            mappedCharacters.add(auxCharacter);
        }
        
        return mappedCharacters;
    }
    
    
    
    public static List<Show> showListMapper(List<Show> showList){
        List<Show> mappedShows = new ArrayList<>();
        
        for (Show show : showList){
            Show auxShow = Show.builder()
                    .imageUrl(show.getImageUrl())
                    .title(show.getTitle())
                    .releaseDate(show.getReleaseDate())
                    .build();
            
            mappedShows.add(auxShow);
        }
        
        return mappedShows;
    }

}
