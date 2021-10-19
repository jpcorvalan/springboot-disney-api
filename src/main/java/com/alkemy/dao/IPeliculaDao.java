
package com.alkemy.dao;

import com.alkemy.models.Pelicula;
import org.springframework.data.repository.CrudRepository;

public interface IPeliculaDao extends CrudRepository<Pelicula, Integer>{   
    
    
}
