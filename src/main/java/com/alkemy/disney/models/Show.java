package com.alkemy.disney.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "shows")
@Getter
@Setter
@AllArgsConstructor (access = AccessLevel.PRIVATE)
@Builder
public class Show implements Serializable {
    
    
    // Variables de instancia/Campos de tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_show", unique = true, nullable = false)
    private Integer idShow;
    
    @Basic
    @Column(name = "image_url")
    private String imageUrl;
    
    @Basic
    private String title;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "release_date")
    private Date releaseDate;
    
    @Basic
    private Byte score;
    
    
    //Relación ManyToMany donde especificamos los campos de la 3er tabla
    @ManyToMany(
            fetch = FetchType.LAZY, 
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "characters_shows",
            joinColumns = {
                @JoinColumn(name = "id_show")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "id_character")
            }
    )
    private List<Character> characters;
    
    
    
    
    // Constructores
    public Show() {
    }

    public Show(String imageUrl, String title, Date releaseDate, Byte score) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.releaseDate = releaseDate;
        this.score = score;
    }
    
    
    
    
    
}
