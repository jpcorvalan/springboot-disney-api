
package com.alkemy.disney.services;

import com.alkemy.disney.models.Character;
import java.util.List;


public interface ICharacterService {
    
    public List<Character> findAllCharacters();
    
    public Character findCharacter(Character character);
    
    public Character findCharacterById(int id);
    
    public Character saveCharacter(Character character);
    
    public boolean deleteCharacter(int id);
    
}
