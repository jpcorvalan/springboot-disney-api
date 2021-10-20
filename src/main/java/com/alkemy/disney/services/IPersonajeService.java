
package com.alkemy.disney.services;

import com.alkemy.disney.models.Personaje;
import java.util.List;


public interface IPersonajeService {
    
    public List<Personaje> obtenerPersonajes();
    
    public Personaje encontrarPersonaje(Personaje personaje);
    
    public void guardarPersonaje(Personaje personaje);
    
    public void eliminarPersonaje(Personaje personaje);
    
}
