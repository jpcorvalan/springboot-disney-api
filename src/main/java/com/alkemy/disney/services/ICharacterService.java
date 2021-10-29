
package com.alkemy.disney.services;

import com.alkemy.disney.models.Character;
import java.util.List;


public interface ICharacterService {
    
    public List<Character> findAllCharacters();
    
    public Character findCharacter(Character character);
    
    public Character findCharacterById(int id);
    
    public List<Character> findByNameOrAgeOrWeight(String name, Integer age, Double weight);
    
    public List<Character> joinCharactersShows(Integer idShow);
    
    public Character saveCharacter(Character character);
    
    public void deleteCharacterInMidTable(Integer id);
    
    public boolean deleteCharacter(int id);
    
}
