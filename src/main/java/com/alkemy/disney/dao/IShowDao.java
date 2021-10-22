
package com.alkemy.disney.dao;

import com.alkemy.disney.models.Show;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShowDao extends CrudRepository<Show, Integer>{ 
}
