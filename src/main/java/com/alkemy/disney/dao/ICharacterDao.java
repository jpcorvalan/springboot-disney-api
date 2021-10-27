package com.alkemy.disney.dao;

import com.alkemy.disney.models.Character;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICharacterDao extends CrudRepository<Character, Integer> {

    public abstract List<Character> findByNameOrAgeOrWeight(String name, Integer age, Double weight);

    @Query(value = "SELECT characters.id_character, characters.image_url, characters.name, characters.age, characters.weight, characters.history FROM `characters`\n"
            + "JOIN characters_shows\n"
            + "	ON characters.id_character = characters_shows.id_character\n"
            + "JOIN shows\n"
            + "	ON shows.id_show = characters_shows.id_show\n"
            + "WHERE characters_shows.id_show = ?", nativeQuery = true)
    public abstract List<Character> joinCharactersShows(Integer idShow);
}
