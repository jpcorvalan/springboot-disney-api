package com.alkemy.services;

import com.alkemy.dao.IGeneroDao;
import com.alkemy.models.Genero;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class GeneroServiceImpl implements IGeneroService{
    
    @Autowired
    private IGeneroDao generoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Genero> obtenerGeneros() {
        return (List<Genero>) generoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Genero encontrarGenero(Genero genero) {
        return generoDao.findById(genero.getId()).orElse(null);
    }

    @Override
    @Transactional
    public void crearGenero(Genero genero) {
        generoDao.save(genero);
    }

    @Override
    @Transactional
    public void eliminarGenero(Genero genero) {
        generoDao.delete(genero);
    }
    
    
    
}
