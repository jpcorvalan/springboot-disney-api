
package com.alkemy.dao;

import com.alkemy.models.Personaje;
import org.springframework.data.repository.CrudRepository;

public interface IPersonajeDao extends CrudRepository<Personaje, Integer> {
}
