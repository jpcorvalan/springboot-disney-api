
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
import java.time.LocalDate;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @NotEmpty(message = "Tiene que especificar una imagen")
    private String imageUrl;
    
    @Basic
    @NotEmpty(message = "Tiene que especificar un nombre para la película/serie")
    private String title;
    
    @Column(name = "release_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "El formato de la fecha debe ser AAAA-mm-dd (Año-mes-día)")
    private LocalDate releaseDate;
    
    @Basic
    @Min(value = 1, message = "El puntaje debe ir de 1 a 5")
    @Max(value = 5, message = "El puntaje debe ir de 1 a 5")
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

    public Show(String imageUrl, String title, LocalDate releaseDate, Byte score) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.releaseDate = releaseDate;
        this.score = score;
    }
    
    
    
    
 
}
