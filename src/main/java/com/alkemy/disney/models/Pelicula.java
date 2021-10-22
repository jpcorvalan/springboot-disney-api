package com.alkemy.disney.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(value = { "idPelicula", "fechaCreacion", "calificacion", "personajes"})
@Data
@Entity
@Table(name = "peliculas")
@Getter
@Setter
public class Pelicula implements Serializable {
    
    
    // Variables de instancia/Campos de tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula", unique = true, nullable = false)
    private Integer idPelicula;
    
    @Basic
    @Column(name = "url_imagen")
    private String urlImagen;
    
    @Basic
    private String titulo;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    
    @Basic
    private Byte calificacion;
    
    
    //Relación ManyToMany donde especificamos los campos de la 3er tabla
    @ManyToMany(
            fetch = FetchType.LAZY, 
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "peliculas_personajes",            
            joinColumns = {
                @JoinColumn(name = "id_pelicula")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "id_personaje")
            }
    )
    private List<Personaje> personajes;
    
    
    
    
    // Constructores
    public Pelicula() {
    }
    

    public Pelicula(String urlImagen, String titulo, Date fechaCreacion, Byte calificacion) {
        this.urlImagen = urlImagen;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
        //this.personajes = new ArrayList<>();
    }
    
}
