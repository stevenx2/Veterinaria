package com.Veterinaria.Veterinaria.Controller;


import com.Veterinaria.Veterinaria.Model.Propietario;
import com.Veterinaria.Veterinaria.Services.IPropietario;
import com.Veterinaria.Veterinaria.Services.util.ServicioPropietario;
import com.Veterinaria.Veterinaria.exception.BadRequest;
import com.Veterinaria.Veterinaria.exception.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/propietarios")
public class RestControllerPropietario {

    @Autowired
    @Qualifier(value = "servicioPropietario")
    IPropietario service;

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


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Propietario propietario) {
        Propietario guardado = service.save(propietario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(guardado.getId()).toUri();
        return ResponseEntity.created(uri).body(guardado);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Propietario propietario) {
        try {
            Integer ID = Integer.valueOf(id);

            if (service.existById(ID)) {
                Propietario propietarioObtenido = service.findByid(ID);
                propietarioObtenido.setNombre(propietario.getNombre());
                propietarioObtenido.setEdad(propietario.getEdad());
                propietarioObtenido.setApellido(propietario.getApellido());
                service.save(propietarioObtenido);
                return ResponseEntity.noContent().build();

            } else throw new NotFound("no existe un propietario con el id " + ID);

        } catch (NumberFormatException e) {
            throw new BadRequest("el id tiene que ser númerico");
        }
    }


}
