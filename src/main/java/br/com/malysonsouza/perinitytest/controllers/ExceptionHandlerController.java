package br.com.malysonsouza.perinitytest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.malysonsouza.perinitytest.dto.ErrorDTO;
import br.com.malysonsouza.perinitytest.exceptions.RegraNegocioException;

@RestControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErrorDTO> handler(RegraNegocioException ex){
        return new ResponseEntity<>( new ErrorDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
        
    }

}
