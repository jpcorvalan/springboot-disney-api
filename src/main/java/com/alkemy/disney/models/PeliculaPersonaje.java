
package com.alkemy.disney.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "peliculas_personajes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaPersonaje implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;
    
    @Column(name = "id_pelicula")
    private Integer pelicula;    

    @Column(name = "id_personaje")
    private Integer personaje;
    
}
