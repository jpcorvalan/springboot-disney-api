
package com.alkemy.services;

import com.alkemy.models.Personaje;
import java.util.List;


public interface IPersonajeService {
    
    public List<Personaje> obtenerPersonajes();
    
    public Personaje encontrarPersonaje(Personaje personaje);
    
    public void guardarPersonaje(Personaje personaje);
    
    public void eliminarPersonaje(Personaje personaje);
    
}
