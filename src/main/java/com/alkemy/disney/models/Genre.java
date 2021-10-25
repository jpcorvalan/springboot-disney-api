package com.alkemy.disney.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "genres")
@Getter
@Setter
public class Genre implements Serializable {
    
    
    // Variables de instancia/Campos de tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre", unique = true, nullable = false)
    private Integer idGenre;
    
    @Basic
    @NotEmpty(message = "Tiene que especificar un nombre")
    private String name;
    
    @Basic
    @Column(name = "image_url")
    @NotEmpty(message = "Tiene que especificar una imagen")
    private String imageUrl;
    
    @OneToMany
    private List<Show> asociatedShows;
    
    
    
    
    // Constructores
    public Genre() {
    }
    
    
    public Genre(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
    
    
    
    
    
    
    
}
