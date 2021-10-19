package com.alkemy.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "personajes")
public class Personaje implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;
    
    @Basic
    @Column(name = "url_imagen")
    private String urlImagen;
    
    @Basic
    private String nombre;
    
    @Basic
    private Integer edad;
    
    @Basic
    private Double peso;
    
    @Basic
    private String historia;
    
    @ManyToMany(mappedBy = "personajesAsociados")
    private List<Pelicula> peliculasAsociadas;
    
}
