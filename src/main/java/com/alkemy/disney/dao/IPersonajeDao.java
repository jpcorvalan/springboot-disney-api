
package com.alkemy.disney.dao;

import com.alkemy.disney.models.Personaje;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonajeDao extends CrudRepository<Personaje, Integer> {
}
