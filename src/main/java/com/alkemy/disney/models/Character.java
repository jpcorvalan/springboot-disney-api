
/*

@Builder y @AllArgsConstructor (access = AccessLevel.PRIVATE)
    En esta clase se implementó el patrón Builder (Con @Builder de Lombok). Esta etiqueta se combinó con otra
    llamada @AllArgsConstructor(access = AccessLevel.private).
    Esta combinación hace que los constructores creados por el builder, sean Privados, y solo puedan ser accedidos
    por el mismo Lombok cuando se invoque al Builder.
    Sin la segunda anotación mencionada, se produce un error de compilación, ya que, por defecto, Spring necesita
    que las clases entidades tengan, mínimo, un constructor por defecto, el cual crea conflicto si implementamos
    solo @Builder, ya que por defecto, los constructores de este serán de acceso "default".

@JsonInclude(JsonInclude.Include.NON_NULL)
    Esta etiqueta sirve para que, al llamar a los endpoints de la API, solo se muestren aquellos campos que posean
    datos distintos a null.
    Se implementó, debido a la consigna que indica que, los registros en conjunto, deben mostrar cierta cantidad de campos,
    y los registros individuales, deben mostrar todos los campos.
    Al usar el patrón Builder explicado más arriba, creo instancias de objetos con solo los campos necesarios, que son
    los que luego terminan siendo insertados dentro de la lista requerida.

*/

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
