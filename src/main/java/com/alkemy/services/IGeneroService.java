
package com.alkemy.services;

import com.alkemy.models.Genero;
import java.util.List;


public interface IGeneroService {
    
    public List<Genero> obtenerGeneros();
    
    public Genero encontrarGenero(Genero genero);
    
    public void crearGenero(Genero genero);
    
    public void eliminarGenero(Genero genero);
    
}
