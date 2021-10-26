package com.alkemy.disney.controllers;

import java.util.List;
import java.util.ArrayList;
import com.alkemy.disney.models.Character;

public class ListMapper {

    public static List<Character> characterListMapper(List<Character> characterList) {
        
        List<Character> mappedCharacters = new ArrayList<>();
        
        // Se crean nuevos Character con los atributos requeridos y se insertan en un nuevo Array
        for (Character chara : characterList) {
            com.alkemy.disney.models.Character auxCharacter = com.alkemy.disney.models.Character.builder()
                    .imageUrl(chara.getImageUrl())
                    .name(chara.getName())
                    .build();

            mappedCharacters.add(auxCharacter);
        }
        
        return mappedCharacters;
    }

}
