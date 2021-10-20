
package com.alkemy.disney.dao;

import com.alkemy.disney.models.Genero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGeneroDao extends CrudRepository<Genero, Integer>{    
}
