
package com.alkemy.disney.services;

import com.alkemy.disney.models.Show;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alkemy.disney.dao.IShowDao;


@Service
public class ShowServiceImplementation implements IShowService{
    
    @Autowired
    private IShowDao showDao;

    @Override
    @Transactional(readOnly = true)
    public List<Show> findAllShows() {
        return (List<Show>) showDao.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Show findShowById(int id){
        return showDao.findById(id).orElse(null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Show> findShowsByGenre(Integer idGenre){
        return showDao.findShowsByGenre(idGenre);
    }

    @Override
    @Transactional(readOnly = true)
    public Show findShow(Show show) {
        return showDao.findById(show.getIdShow()).orElse(null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Show> findByTitle(String title){
        return showDao.findByTitle(title);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Show> orderAllByScoreAsc(){
        return showDao.orderAllByScoreAsc();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Show> orderAllByScoreDesc(){
        return showDao.orderAllByScoreDesc();
    }

    @Override
    @Transactional
    public Show saveShow(Show show) {
        return showDao.save(show);
    }
    
    @Override
    @Transactional
    public void deleteCharacterShowRel(Integer id){
        showDao.deleteCharacterShowRel(id);
    }
    
    
    @Override
    @Transactional
    public void deleteGenreShowRel(Integer id){
        showDao.deleteGenreShowRel(id);
    }
    
    
    @Override
    @Transactional
    public void deleteShowById(Integer id){
        showDao.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteShow(Show show) {
        showDao.delete(show);
    }
    
    
    
}
