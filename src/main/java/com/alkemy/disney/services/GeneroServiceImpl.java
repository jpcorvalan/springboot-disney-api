package com.alkemy.disney.services;

import com.alkemy.disney.dao.IGeneroDao;
import com.alkemy.disney.models.Genero;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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
