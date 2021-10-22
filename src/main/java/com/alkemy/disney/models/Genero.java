package com.alkemy.disney.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "genero")
@Getter
@Setter
public class Genero implements Serializable {
    
    
    // Variables de instancia/Campos de tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero", unique = true, nullable = false)
    private Integer idGenero;
    
    @Basic
    private String nombre;
    
    @Basic
    @Column(name = "url_imagen")
    private String urlImagen;
    
    @OneToMany
    private List<Pelicula> peliculasAsociadas;
    
    
    
    
    // Constructores
    public Genero() {
    }
    
    
    public Genero(String nombre, String urlImagen) {
        this.nombre = nombre;
        this.urlImagen = urlImagen;
        //this.peliculasAsociadas = new ArrayList<>();
    }
    
    
    
    
    
    
    
}
