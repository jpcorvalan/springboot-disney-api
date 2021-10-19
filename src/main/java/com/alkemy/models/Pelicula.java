package com.alkemy.models;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class Pelicula {
    
    private Integer id;
    
    private String urlImagen;
    
    private String titulo;
    
    private Date fechaCreacion;
    
    private Byte calificacion;
    
    private List<Personaje> personajesAsociados;
    
}
