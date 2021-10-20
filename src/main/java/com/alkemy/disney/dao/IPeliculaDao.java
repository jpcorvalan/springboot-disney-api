
package com.alkemy.disney.dao;

import com.alkemy.disney.models.Pelicula;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPeliculaDao extends CrudRepository<Pelicula, Integer>{ 
}
