package com.alkemy.disney.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "personajes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Personaje implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personaje", unique = true, nullable = false)
    private Integer idPersonaje;
    
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
    
    @OneToMany(mappedBy = "pelicula")
    private List<PeliculaPersonaje> peliculas;
    
//    @ManyToMany(mappedBy = "personajes")
//    private List<Pelicula> peliculas;
    
}
