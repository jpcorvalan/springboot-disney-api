package com.alkemy.disney.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
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
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "characters")
@Getter
@Setter
@AllArgsConstructor (access = AccessLevel.PRIVATE)
@Builder
public class Character implements Serializable {
    
    
    // Variables de instancia/Campos de tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_character", unique = true, nullable = false)
    private Integer idCharacter;
    
    @Basic
    @Column(name = "image_url")
    private String imageUrl;
    
    @Basic
    private String name;
    
    @Basic
    private Integer age;
    
    @Basic
    private Double weight;
    
    @Basic
    @Column(columnDefinition = "TEXT")
    private String history;
    
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "characters"
    )
    private List<Show> shows;
    
    
    
    
    //Constructores
    
    // Constructor por defecto
    public Character() {
    }
    

    // Constructor necesario para crear las instancias, que se generarán por el POST de la API
    public Character(String imageUrl, String name, Integer age, Double weight, String history) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.history = history;
    }
    
    
    

}
