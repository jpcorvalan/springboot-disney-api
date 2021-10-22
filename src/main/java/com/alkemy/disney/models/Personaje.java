package com.alkemy.disney.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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


//@JsonIgnoreProperties(value = { "idPersonaje", "edad", "peso", "historia", "peliculas" })
@Entity
@Table(name = "personajes")
@Getter
@Setter
public class Personaje implements Serializable {
    
    
    // Variables de instancia/Campos de tabla
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
    @Column(columnDefinition = "TEXT")
    private String historia;
    
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "personajes"
    )
    private List<Pelicula> peliculas;
    
    
    
    
    //Constructores
    public Personaje() {
    }

    
    public Personaje(String urlImagen, String nombre, Integer edad, Double peso, String historia) {
        this.urlImagen = urlImagen;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
        //this.peliculas = new ArrayList<>();
    }
    
}
