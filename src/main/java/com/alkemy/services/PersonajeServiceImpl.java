package com.alkemy.services;

import com.alkemy.dao.IPersonajeDao;
import com.alkemy.models.Personaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class PersonajeServiceImpl implements IPersonajeService{
    
    @Autowired
    private IPersonajeDao personajeDao;

    @Override
    @Transactional(readOnly = true)
    public List<Personaje> obtenerPersonajes() {
        return (List<Personaje>) personajeDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Personaje encontrarPersonaje(Personaje personaje) {
        
        // Este método devuelve un "Optional<>", entonces para no cambiar el tipo de retorno del método
        // aquí, ni en la interface, hacemos uso de "orElse" para aclarar que pasará en caso de no encontrarse el personaje
        return personajeDao.findById(personaje.getId()).orElse(null);
    }

    @Override
    @Transactional
    public void guardarPersonaje(Personaje personaje) {
        personajeDao.save(personaje);
    }

    @Override
    @Transactional
    public void eliminarPersonaje(Personaje personaje) {
        personajeDao.delete(personaje);
    }
    
    
    
}
