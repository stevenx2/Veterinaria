package com.Veterinaria.Veterinaria.ExceptionHandler;


import com.Veterinaria.Veterinaria.exception.BadRequest;
import com.Veterinaria.Veterinaria.exception.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador de actua como manejador de excepciones
 */
@RestControllerAdvice
public class RestControllerExcaptionHandler {

    /**
     * este metodo se ejecuta cada vez que se lanza una excepcion de este tipo. sucede cuando el cliente manda un dato incorrecto en la peticion (un ejemplo es un id no numerico)
     * @param e el tipo de excepcion que atrapa este metodo
     * @return la respuesta en el cuerpo de la peticion. tiene datos como la fecha,mensaje y codigo del error. codigo 400 BAD_REQUEST
     */
    @ExceptionHandler(BadRequest.class)
    protected ResponseEntity<Object> badRequest(BadRequest e) {
        return new ResponseEntity<>(getHeaderHttp(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    /**
     * este metodo se ejecuta cada vez que se lanza una excepcion de este tipo. se lanza cada vez que el registro que el cliente pide no existe en la base de datos
     * @param e el tipo de excepcion que atrapa este metodo
     * @return la respuesta en el cuerpo de la peticion. tiene datos como la fecha,mensaje y codigo del error. codigo 404 NOT_FOUND
     */
    @ExceptionHandler(NotFound.class)
    protected ResponseEntity<Object> notFound(NotFound e) {
        return new ResponseEntity<>(getHeaderHttp(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }


    /**
     * este metodo genera el cuerpo del error de la peticion http
     * @param msg el mensaje de error
     * @param status el codigo de estado del error.
     * @return un Map que contiene el cuerpo del error
     */
    private Map<String, Object> getHeaderHttp(String msg, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("mensaje", msg);
        map.put("status", status.value());
        map.put("fecha", LocalDate.now());
        return map;
    }
}
