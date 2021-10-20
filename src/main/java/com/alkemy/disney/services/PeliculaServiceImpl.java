
package com.alkemy.disney.services;

import com.alkemy.disney.dao.IPeliculaDao;
import com.alkemy.disney.models.Pelicula;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PeliculaServiceImpl implements IPeliculaService{
    
    @Autowired
    private IPeliculaDao peliculaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Pelicula> obtenerPeliculas() {
        return (List<Pelicula>) peliculaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Pelicula encontrarPelicula(Pelicula pelicula) {
        return peliculaDao.findById(pelicula.getId()).orElse(null);
    }

    @Override
    @Transactional
    public void guardarPelicula(Pelicula pelicula) {
        peliculaDao.save(pelicula);
    }

    @Override
    @Transactional
    public void eliminarPelicula(Pelicula pelicula) {
        peliculaDao.delete(pelicula);
    }
    
    
    
}
