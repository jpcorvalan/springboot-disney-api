
package com.alkemy.disney.controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import javax.validation.Valid;
import com.alkemy.disney.exceptions.ModelNotFoundException;
import com.alkemy.disney.models.Show;
import com.alkemy.disney.services.IShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/movies")
public class ShowController {
    
    @Autowired
    private IShowService showService;
    
    
    

    
    
    @GetMapping()
    @ApiOperation("Obtiene todos los Shows")
    @ApiResponse(code = 200, message = "OK")
    public List<Show> obtenerShows(
            @RequestParam(required = false) Integer genre,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String order
    ){
        
        if (genre == null && title == null && order == null){
            return ListMapper.nullifiqueShowsNonUsedColumns(showService.findAllShows());
        } else {
            List<Show> showsByName = (title != null) ? showService.findByTitle(title) : new ArrayList<>();
            List<Show> showsByGenre = (genre != null) ? showService.findShowsByGenre(genre) : new ArrayList<>();
            List<Show> orderedShows = new ArrayList<>();
            
            if(order != null){
                switch(order){
                    case "ASC":
                        orderedShows = ListMapper.nullifiqueShowsNonUsedColumns(showService.orderAllByScoreAsc());
                        return orderedShows;
                    case "DESC":
                        orderedShows = ListMapper.nullifiqueShowsNonUsedColumns(showService.orderAllByScoreDesc());
                        return orderedShows;
                }                
            }
            
            showsByName.addAll(showsByGenre);
            
            return ListMapper.nullifiqueShowsNonUsedColumns(showsByName);
        }
        
    }
    
    
    
    
    @GetMapping(path = "/{id}")
    @ApiOperation("Obtiene un Show por su ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "El Show especificado no fue encontrado")
    })
    public ResponseEntity<Show> obtenerShowPorId(
            @ApiParam(value = "El ID del Show a buscar", required = true, example = "3")
            @PathVariable("id") int id
    ){
        
        Show findedShow = showService.findShowById(id);
        
        if(findedShow != null){
            
            findedShow.setCharacters(ListMapper.nullifiqueCharactersNonUsedColumns(findedShow.getCharacters()));
            
            return new ResponseEntity<>(findedShow, HttpStatus.OK);
            
        } else {
            throw new ModelNotFoundException("El show con el id " + id + " no existe");
        }
        
    }
    
    
    
    
    @PostMapping("/create")
    @ApiOperation("Crea un Show")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Campos faltantes o valores inválidos")
    })
    public ResponseEntity<Object> guardarShow(@RequestBody @Valid Show show, Errors errors) throws InvalidFormatException{
        try{
            return new ResponseEntity<>(showService.saveShow(show), HttpStatus.CREATED);
        } catch (Exception e){
            HashMap<String, String> mappedErrors = new HashMap<>();
            
            for(FieldError validation : errors.getFieldErrors()){
                mappedErrors.put(validation.getField(), validation.getDefaultMessage());
            }
            
            return new ResponseEntity<>(mappedErrors, HttpStatus.BAD_REQUEST);
        }
    }
    
    
    
    
    @DeleteMapping(path = "/{id}")
    @ApiOperation("Borra un Show por su ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "El Show especificado no fue encontrado")
    })
    public ResponseEntity<Show> borrarShow(
            @ApiParam(value = "El ID del Show a eliminar", required = true, example = "3")
            @PathVariable("id") Integer id
    ){
        Show showFinded = showService.findShowById(id);
        
        if(showFinded != null){
            
            // Borramos las relaciones que tiene Show con Character y Genre
            showService.deleteCharacterShowRel(showFinded.getIdShow());            
            showService.deleteGenreShowRel(showFinded.getIdShow());
            
            // Finalmente, borramos el show
            showService.deleteShowById(showFinded.getIdShow());
            
            return new ResponseEntity<>(showFinded, HttpStatus.OK);
        } else {
            throw new ModelNotFoundException("El personaje con el id " + id + " no existe");
        }
            
    }
    
    
    
    
    // Si la fecha está escrita en un formato inválido, arrojará una excepción que se manejará con este método
    @ExceptionHandler({InvalidFormatException.class})
    public ResponseEntity<Object> invalidDateFormat(){
        String errorMsg = "El formato de la fecha debe ser AAAA-mm-dd (Año-mes-día)";
        return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
    }
    
    
    
    
    // Si el cuerpo de la petición es inválido, arrojará un mensaje aclarándolo
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> invalidMessage(){
        String errorMsg = "La petición solicitada es inválida";
        return new ResponseEntity<>(errorMsg, HttpStatus.NO_CONTENT);
    }
    
}
