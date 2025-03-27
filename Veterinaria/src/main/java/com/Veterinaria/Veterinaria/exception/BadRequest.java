package com.Veterinaria.Veterinaria.exception;

/**
 * excepcion que se lanza cuando el cliente envia un dato incorrecto en el cuerpo de la solicitud
 */
public class BadRequest extends RuntimeException {
    public BadRequest(String message) {
        super(message);
    }
}
