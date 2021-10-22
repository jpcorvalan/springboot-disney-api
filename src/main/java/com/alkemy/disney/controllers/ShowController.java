
package com.alkemy.disney.controllers;

import com.alkemy.disney.models.Show;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.disney.services.IShowService;

@RestController
@RequestMapping("/movies")
public class ShowController {
    
    @Autowired
    private IShowService showService;
    
    @GetMapping()
    public List<Show> findAllMovies(){
        return this.showService.findAllShows();
    }
    
    @PostMapping("/crear")
    public Show guardarPelicula(@RequestBody Show show){        
        return this.showService.saveShow(show);
    }
    
}
