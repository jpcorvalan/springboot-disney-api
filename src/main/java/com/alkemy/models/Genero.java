package com.alkemy.models;

import java.util.List;
import lombok.Data;

@Data
public class Genero {
    
    private Integer id;
    
    private String nombre;
    
    private String urlImagen;
    
    private List<Pelicula> peliculasAsociadas;
    
}
