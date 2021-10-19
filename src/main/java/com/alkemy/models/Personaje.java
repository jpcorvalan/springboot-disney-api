package com.alkemy.models;

import java.util.List;
import lombok.Data;

@Data
public class Personaje {
    
    private Integer id;
    
    private String urlImagen;
    
    private String nombre;
    
    private Integer edad;
    
    private Double peso;
    
    private String historia;
    
    private List<Pelicula> peliculasAsociadas;
    
}
