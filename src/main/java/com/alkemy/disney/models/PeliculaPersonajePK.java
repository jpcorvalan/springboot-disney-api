
package com.alkemy.disney.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaPersonajePK implements Serializable {
    
    @Column(name = "id_pelicula")
    private Integer idPelicula;
    
    @Column(name = "id_personaje")
    private Integer idPersonaje;
    
}
