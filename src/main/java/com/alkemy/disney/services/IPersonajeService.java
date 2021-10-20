
package com.alkemy.disney.services;

import com.alkemy.disney.models.Personaje;
import java.util.List;


public interface IPersonajeService {
    
    public List<Personaje> obtenerPersonajes();
    
    public Personaje encontrarPersonaje(Personaje personaje);
    
    public Personaje encontrarPersonajePorId(int id);
    
    public Personaje guardarPersonaje(Personaje personaje);
    
    public boolean eliminarPersonaje(int id);
    
}
