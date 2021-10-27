package com.alkemy.disney.dao;

import com.alkemy.disney.models.Show;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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

}
