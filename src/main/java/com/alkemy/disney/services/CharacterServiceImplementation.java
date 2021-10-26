package com.alkemy.disney.services;

import com.alkemy.disney.models.Character;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alkemy.disney.dao.ICharacterDao;
import com.alkemy.disney.models.Show;


@Service
public class CharacterServiceImplementation implements ICharacterService{
    
    @Autowired
    private ICharacterDao characterDao;

    @Override
    @Transactional(readOnly = true)
    public List<Character> findAllCharacters() {        
        return (List<Character>) characterDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Character findCharacter(Character character) {
        
        // Este método devuelve un "Optional<>", entonces para no cambiar el tipo de retorno del método
        // aquí, ni en la interface, hacemos uso de "orElse" para aclarar que pasará en caso de no encontrarse el personaje
        return characterDao.findById(character.getIdCharacter()).orElse(null);
    }
    
    @Override
    public Character findCharacterById(int id){
        return characterDao.findById(id).orElse(null);
    }
    
    @Override
    public List<Character> findByNameOrAgeOrWeight(String name, Integer age, Double weight){
        return characterDao.findByNameOrAgeOrWeight(name, age, weight);
    }

    @Override
    @Transactional
    public Character saveCharacter(Character character) {
        return characterDao.save(character);
    }

    @Override
    @Transactional
    public boolean deleteCharacter(int id) {
        try{
            characterDao.deleteById(id);
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    
    
}
