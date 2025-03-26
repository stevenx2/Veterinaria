package com.Veterinaria.Veterinaria.Controller;


import com.Veterinaria.Veterinaria.Services.util.ServicioPropietario;
import com.Veterinaria.Veterinaria.exception.BadRequest;
import com.Veterinaria.Veterinaria.exception.NotFound;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/propietarios")
public class RestControllerPropietario {

    @Autowired
    ServicioPropietario service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        try {
            Integer ID = Integer.valueOf(id);

            if (service.existById(ID)) {
                return ResponseEntity.ok(service.findByid(ID));
            } else throw new NotFound("no existe un propietario con el id " + ID);

        } catch (NumberFormatException e) {
            throw new BadRequest("El id tiene que ser numérico");
        }
    }


}
