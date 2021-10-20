
package com.alkemy.disney.services;

import com.alkemy.disney.models.Genero;
import java.util.List;


public interface IGeneroService {
    
    public List<Genero> obtenerGeneros();
    
    public Genero encontrarGenero(Genero genero);
    
    public Genero crearGenero(Genero genero);
    
    public void eliminarGenero(Genero genero);
    
}
