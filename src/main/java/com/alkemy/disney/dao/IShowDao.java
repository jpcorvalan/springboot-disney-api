package com.alkemy.disney.dao;

import com.alkemy.disney.models.Show;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IShowDao extends CrudRepository<Show, Integer> {

    @Query(value = "SELECT * FROM `shows`\n"
            + "JOIN genres_asociated_shows\n"
            + "	ON genres_asociated_shows.asociated_shows_id_show = shows.id_show\n"
            + "JOIN genres\n"
            + "	ON genres.id_genre = genres_asociated_shows.genre_id_genre\n"
            + "WHERE genres.id_genre = ?", nativeQuery = true)
    public abstract List<Show> findShowsByGenre(Integer idGenre);

    @Query(value = "SELECT * FROM `shows`\n"
            + "ORDER BY score ASC", nativeQuery = true)
    public abstract List<Show> orderAllByScoreAsc();

    @Query(value = "SELECT * FROM `shows`\n"
            + "ORDER BY score DESC", nativeQuery = true)
    public abstract List<Show> orderAllByScoreDesc();

    public abstract List<Show> findByTitle(String title);

    @Query(value = "DELETE FROM `characters_shows` \n"
            + "WHERE `characters_shows`.`id_show` = ?", nativeQuery = true)
    @Modifying
    @Transactional
    public abstract void deleteCharacterShowRel(Integer id);

    @Query(value = "DELETE FROM `genres_asociated_shows` \n"
            + "WHERE `genres_asociated_shows`.`asociated_shows_id_show` = ?", nativeQuery = true)
    @Modifying
    @Transactional
    public abstract void deleteGenreShowRel(Integer id);


}
