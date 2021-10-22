
package com.alkemy.disney.services;

import com.alkemy.disney.models.Genre;
import java.util.List;


public interface IGenreService {
    
    public List<Genre> findAllGenres();
    
    public Genre findGenre(Genre genre);
    
    public Genre createGenre(Genre genre);
    
    public void deleteGenre(Genre genre);
    
}
