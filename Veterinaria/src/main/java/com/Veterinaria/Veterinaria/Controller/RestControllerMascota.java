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

/**
 * Controlador para manejar los registros de la entidad mascota
 */
@RestController
@RequestMapping(value = "/mascotas")
public class RestControllerMascota {


    /**
     * bean de servicioMascota
     */
    @Autowired
    @Qualifier(value = "servicioMascota")
    private IMascota service;

    /**
     * bean de servicioPropietario
     */
    @Autowired
    @Qualifier(value = "servicioPropietario")
    private IPropietario servicePropietario;

    /**
     * variable de tipo string que toma el valor del archivo .properties
     * especificamente la propiedad request.url. el valor de esta variable representa la url
     * de una solicitud de tipo get y muestra donde se creó un registro. esta variable se creó por problemas
     * al crear la uri del contenido creado. la variable de entorno REQUEST_STATIC_URL especifica la url
     */
    @Value(value = "${request.url}")
    private String urlEstatica;


    /**
     * metodo para obtener todos los registros de la entidad mascota
     * @return cuerpo de la solicitud http con codigo de estado 200 y la lista de mascotas de la base de datos
     */
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }


    /**
     *
     * @param id el id de la mascota que se quiere encontrar. este id lo pasa el cliente
     * @return el cuerpo de la solicitud http con codigo de estado 200 y la mascota encontrada
     */
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


    /**
     * metodo para guardar una mascota. aqui se hace uso de la variable urlEstatica.
     * @param idPropietario el id del propietario al cual se le quiere agregar una nueva mascota
     * @param mascota la mascota que se le va a agregar
     * @return respuesta con codigo de estado 201, la url para encontrar el recurso y la mascota guardada
     */
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


    /**
     * metodo para actualizar los datos de una mascota
     * @param id id de la mascota que se quiere modificar
     * @param edit el objeto de mascota que va a remplazar a este
     * @return respuesta http con codigo de estado 200 y los nuevos datos de la mascota
     */
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


    /**
     * metodo para eliminar una mascota
     * @param id id de la mascota que se quiere eliminar
     * @return respuesta con codigo de estado 204 NO_CONTENT
     */
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
