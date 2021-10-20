
package com.alkemy.disney.models;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    
    @EmbeddedId
    private PeliculaPersonajePK id;
    
    @ManyToOne
    @JoinColumn(name = "id_pelicula", insertable = false, updatable = false)
    private Pelicula pelicula;    
    
    @ManyToOne
    @JoinColumn(name = "id_personaje", insertable = false, updatable = false)
    private Personaje personaje;
    
}
