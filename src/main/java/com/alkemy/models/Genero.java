package com.alkemy.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "genero")
public class Genero implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;
    
    @Basic
    private String nombre;
    
    @Basic
    @Column(name = "url_imagen")
    private String urlImagen;
    
    @OneToMany(mappedBy = "genero")
    private List<Pelicula> peliculasAsociadas;
    
}
