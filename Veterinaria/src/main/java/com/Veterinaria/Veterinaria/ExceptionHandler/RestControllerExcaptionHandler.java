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

@RestControllerAdvice
public class RestControllerExcaptionHandler {

    @ExceptionHandler(BadRequest.class)
    protected ResponseEntity<Object> badRequest(BadRequest e) {
        return new ResponseEntity<>(getHeaderHttp(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFound.class)
    protected ResponseEntity<Object> notFound(NotFound e) {
        return new ResponseEntity<>(getHeaderHttp(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }


    //Método que devuelve el header de una solicitud http cuando se ocasiona un error
    private Map<String, Object> getHeaderHttp(String msg, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("mensaje", msg);
        map.put("status", status.value());
        map.put("fecha", LocalDate.now());
        return map;
    }
}
