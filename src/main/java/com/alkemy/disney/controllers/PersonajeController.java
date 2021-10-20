
package com.alkemy.disney.controllers;

import com.alkemy.disney.models.Personaje;
import com.alkemy.disney.services.IPersonajeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personajes")
public class PersonajeController {
    
    @Autowired
    private IPersonajeService personajeService;
    
    @GetMapping()
    public List<Personaje> obtenerPersonajes(){
        return this.personajeService.obtenerPersonajes();
    }
    
    @PostMapping()
    public void guardarPersonaje(@RequestBody Personaje personaje){
        this.personajeService.guardarPersonaje(personaje);
    }
    
    
}
