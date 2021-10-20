package com.alkemy.disney.services;

import com.alkemy.disney.dao.IPersonajeDao;
import com.alkemy.disney.models.Personaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
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
        return personajeDao.findById(personaje.getIdPersonaje()).orElse(null);
    }
    
    @Override
    public Personaje encontrarPersonajePorId(int id){
        return personajeDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Personaje guardarPersonaje(Personaje personaje) {
        return personajeDao.save(personaje);
    }

    @Override
    @Transactional
    public boolean eliminarPersonaje(int id) {
        try{
            personajeDao.deleteById(id);
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    
    
}
