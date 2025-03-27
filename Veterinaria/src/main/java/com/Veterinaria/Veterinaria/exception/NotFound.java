package com.Veterinaria.Veterinaria.exception;

/**
 * excepcion que se lanza cuando no se encuentra un registro en la base de datos
 */
public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}
