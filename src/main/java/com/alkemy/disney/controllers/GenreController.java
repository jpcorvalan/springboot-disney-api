
package com.alkemy.disney.controllers;

import java.util.List;
import java.util.HashMap;
import javax.validation.Valid;
import com.alkemy.disney.models.Genre;
import com.alkemy.disney.services.IGenreService;
import com.alkemy.disney.exceptions.ModelNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/genres")
public class GenreController {
    
    @Autowired
    private IGenreService genreService;
    
    
    
    
    @GetMapping()
    @ApiOperation("Obtiene todos los Generos (Genres) guardados")
    @ApiResponse(code = 200, message = "OK")
    public List<Genre> obtenerGeneros(){
        List<Genre> allGenres = this.genreService.findAllGenres();
        
        for(Genre genre : allGenres){
            genre.setAsociatedShows(ListMapper.nullifiqueShowsNonUsedColumns(genre.getAsociatedShows()));
        }
        
        return allGenres;
    }
    
    
    
    
    @GetMapping(path = "/{id}")
    @ApiOperation("Obtiene un Genero (Genre) por su ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "Genero no encontrado")
    })
    public ResponseEntity<Genre> obtenerGeneroPorId(
            @ApiParam(value = "El ID del Genero (Genre) a buscar", required = true, example = "1")
            @PathVariable Integer id
    ){
        Genre findedGenre = this.genreService.findGenreById(id);
        
        if(findedGenre != null){
            return new ResponseEntity<>(findedGenre, HttpStatus.OK);            
        } else {
            throw new ModelNotFoundException("El genero con el id " + id + " no existe");
        }
    }
    
    
    
    
    @PostMapping("/create")
    @ApiOperation("Crea un Genero (Genre)")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "Genero no encontrado")
    })
    public ResponseEntity<Object> guardarGenero(@RequestBody @Valid Genre genre, Errors errors){
        try{
            return new ResponseEntity<>(this.genreService.createGenre(genre), HttpStatus.CREATED);
        } catch (Exception e){
            HashMap<String, String> mappedErrors = new HashMap<>();
            
            for(FieldError validation : errors.getFieldErrors()){
                mappedErrors.put(validation.getField(), validation.getDefaultMessage());
            }
            
            return new ResponseEntity<>(mappedErrors, HttpStatus.BAD_REQUEST);
        }
    }
    
    
    
    
    @DeleteMapping(path = "{id}")
    @ApiOperation("Borra un Genero (Genre) por su ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "Genero no encontrado")
    })
    public ResponseEntity<Genre> eliminarGenero(
            @ApiParam(value = "El ID del Genero (Genre) a eliminar", required = true, example = "1")
            @PathVariable("id") Integer id
    ){
        Genre genreFinded = genreService.findGenreById(id);
        
        if(genreFinded != null){
            // Borramos primero la relación que tiene con los Show
            genreService.deleteGenreShowRel(genreFinded.getIdGenre());
            
            // Luego borramos el Género como tal
            genreService.deleteGenreById(genreFinded.getIdGenre());
            
            return new ResponseEntity<>(genreFinded, HttpStatus.OK);
        } else {
            throw new ModelNotFoundException("El género con el id " + id + " no existe");
        }
    }
    
    
    
    
    // Si el cuerpo de la petición es inválido, arrojará un mensaje aclarándolo
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> invalidMessage(){
        String errorMsg = "La petición solicitada es inválida";
        return new ResponseEntity<>(errorMsg, HttpStatus.NO_CONTENT);
    }
    
}
