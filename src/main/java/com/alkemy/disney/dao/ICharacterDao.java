
package com.alkemy.disney.dao;

import com.alkemy.disney.models.Character;
import com.alkemy.disney.models.Show;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICharacterDao extends CrudRepository<Character, Integer> {
    public abstract List<Character> findByNameOrAgeOrWeight(String name, Integer age, Double weight);
}
