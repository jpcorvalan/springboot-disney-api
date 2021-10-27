package com.alkemy.disney.services;

import java.util.List;
import com.alkemy.disney.models.Show;

public interface IShowService {  
    
    public List<Show> findAllShows();
    
    public Show findShow(Show show);
    
    public Show findShowById(int id);
    
    public List<Show> findShowsByGenre(Integer idGenre);
    
    public List<Show> orderAllByScoreAsc();
    
    public List<Show> orderAllByScoreDesc();
    
    public List<Show> findByTitle(String title);
    
    public Show saveShow(Show show);
    
    public void deleteShow(Show show);
    
}
