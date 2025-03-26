package com.Veterinaria.Veterinaria.Controller;

import com.Veterinaria.Veterinaria.Model.Mascota;
import com.Veterinaria.Veterinaria.Model.Propietario;
import com.Veterinaria.Veterinaria.Services.IMascota;
import com.Veterinaria.Veterinaria.Services.IPropietario;
import com.Veterinaria.Veterinaria.Services.util.ServicioMascota;
import com.Veterinaria.Veterinaria.exception.BadRequest;
import com.Veterinaria.Veterinaria.exception.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/mascotas")
public class RestControllerMascota {

    @Autowired
    @Qualifier(value = "servicioMascota")
    IMascota service;

    @Autowired
    @Qualifier(value = "servicioPropietario")
    IPropietario servicePropietario;

    @Value(value = "${request.url}")
    private String urlEstatica;


    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        try {
            Integer ID = Integer.valueOf(id);
            if (service.existById(ID)) {
                return ResponseEntity.ok(service.findById(ID));
            } else throw new NotFound("no existe ninguna mascota con el id " + ID);

        } catch (NumberFormatException e) {
            throw new BadRequest("el id tiene que ser numérico");
        }
    }


    @PostMapping(value = "/{idPropietario}")
    public ResponseEntity<?> save(@PathVariable String idPropietario, @RequestBody Mascota mascota) {
        try {
            Integer idPr = Integer.valueOf(idPropietario);

            if (servicePropietario.existById(idPr)) {
                Propietario propietario = servicePropietario.findByid(idPr);
                propietario.addMascota(mascota);
                Mascota m = service.save(mascota);
                Propietario p = m.getPropietario();
                p.addMascota(m);
                URI uri = UriComponentsBuilder.fromUriString(urlEstatica).path("/{id}").buildAndExpand(m.getId()).toUri();
                return ResponseEntity.created(uri).body(m);
            } else throw new NotFound("no existe un propietario con el id " + idPr);

        } catch (NumberFormatException e) {
            throw new BadRequest("el id tiene que ser numérico");
        }


    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Mascota edit) {
        try {
            Integer ID = Integer.valueOf(id);

            if (service.existById(ID)) {
                Mascota mascota = service.findById(ID);
                mascota.setTipo(edit.getTipo());
                mascota.setNombre(edit.getNombre());
                mascota.setFechaIngreso(edit.getFechaIngreso());
                service.save(mascota);
                return ResponseEntity.ok(mascota);
            } else throw new NotFound("no existe una mascota con el id " + ID);

        } catch (NumberFormatException e) {
            throw new BadRequest("el id tiene que ser numérico");
        }
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            Integer ID = Integer.valueOf(id);

            if (service.existById(ID)) {
                service.delete(service.findById(ID));
                return ResponseEntity.noContent().build();
            } else throw new NotFound("no existe una mascota con el id " + ID);
        } catch (NumberFormatException e) {
            throw new BadRequest("el id tiene que ser numérico");
        }

    }


}
