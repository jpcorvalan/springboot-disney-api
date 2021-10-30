package com.alkemy.disney.dao;

import com.alkemy.disney.models.Genre;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IGenreDao extends CrudRepository<Genre, Integer> {

    @Query(value = "DELETE FROM `genres_asociated_shows` \n"
            + "WHERE `genres_asociated_shows`.`genre_id_genre` = ?", nativeQuery = true)
    @Modifying
    @Transactional
    public abstract void deleteGenreShowRel(Integer id);

}
