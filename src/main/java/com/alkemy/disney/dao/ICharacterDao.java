
package com.alkemy.disney.dao;

import com.alkemy.disney.models.Character;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICharacterDao extends CrudRepository<Character, Integer> {
}
