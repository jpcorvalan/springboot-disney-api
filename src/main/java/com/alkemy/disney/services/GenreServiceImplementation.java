package com.alkemy.disney.services;

import com.alkemy.disney.models.Genre;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alkemy.disney.dao.IGenreDao;

@Service
public class GenreServiceImplementation implements IGenreService{
    
    @Autowired
    private IGenreDao generoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAllGenres() {
        return (List<Genre>) generoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Genre findGenre(Genre genre) {
        return generoDao.findById(genre.getIdGenre()).orElse(null);
    }

    @Override
    @Transactional
    public Genre createGenre(Genre genre) {
        return generoDao.save(genre);
    }

    @Override
    @Transactional
    public void deleteGenre(Genre genre) {
        generoDao.delete(genre);
    }
    
    
    
}
