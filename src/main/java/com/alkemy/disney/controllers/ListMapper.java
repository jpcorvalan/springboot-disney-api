package com.alkemy.disney.controllers;

import java.util.List;
import com.alkemy.disney.models.Character;
import com.alkemy.disney.models.Show;

public class ListMapper {

    public static List<Character> nullifiqueCharactersNonUsedColumns(List<Character> charactersList){
        charactersList.forEach((chara) -> {
            chara.setIdCharacter(null);
            chara.setAge(null);
            chara.setWeight(null);
            chara.setHistory(null);
            chara.setShows(null);
        });
        
        return charactersList;
    }
    
    
    
    
    public static List<Show> nullifiqueShowsNonUsedColumns(List<Show> showList){
        showList.forEach((show) -> {
            show.setIdShow(null);
            show.setScore(null);
            show.setCharacters(null);
        });
        
        return showList;
    }

}
