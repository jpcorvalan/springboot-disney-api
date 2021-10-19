
package com.alkemy.services;

import com.alkemy.dao.IPeliculaDao;
import com.alkemy.models.Pelicula;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


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
