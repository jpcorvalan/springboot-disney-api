package com.alkemy.disney.services;

import java.util.List;
import com.alkemy.disney.models.Pelicula;

public interface IPeliculaService {  
    
    public List<Pelicula> obtenerPeliculas();
    
    public Pelicula encontrarPelicula(Pelicula pelicula);
    
    public Pelicula guardarPelicula(Pelicula pelicula);
    
    public void eliminarPelicula(Pelicula pelicula);
    
}
