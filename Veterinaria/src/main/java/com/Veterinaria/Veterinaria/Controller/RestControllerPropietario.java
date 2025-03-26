package com.Veterinaria.Veterinaria.Controller;


import com.Veterinaria.Veterinaria.Services.util.ServicioPropietario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
