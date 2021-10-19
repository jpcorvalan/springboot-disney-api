package com.alkemy.services;

import java.util.List;
import com.alkemy.models.Pelicula;

public interface IPeliculaService {  
    
    public List<Pelicula> obtenerPeliculas();
    
    public Pelicula encontrarPelicula(Pelicula pelicula);
    
    public void guardarPelicula(Pelicula pelicula);
    
    public void eliminarPelicula(Pelicula pelicula);
    
}
