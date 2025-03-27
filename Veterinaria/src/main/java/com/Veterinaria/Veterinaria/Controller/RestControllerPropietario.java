package com.Veterinaria.Veterinaria.Controller;


import com.Veterinaria.Veterinaria.Model.Propietario;
import com.Veterinaria.Veterinaria.Services.IPropietario;
import com.Veterinaria.Veterinaria.exception.BadRequest;
import com.Veterinaria.Veterinaria.exception.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * controlador de los propietarios de las mascotas
 */
@RestController
@RequestMapping(value = "/propietarios")
public class RestControllerPropietario {


    /**
     * objeto de tipo IMascota con los metodos para manejar la base de datos
     */
    @Autowired
    @Qualifier(value = "servicioPropietario")
    private IPropietario service;

    /**
     * metodo para obtener todos los registros de propietarios
     * @return un codigo de estado 200 con una lista de todos los propietarios
     */
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }


    /**
     * metodo para obtener un registro en especifico.
     * @param id id que inserta el cliente y representa el identificador del propietario. se manejan todas las excepciones en caso de pasar con id numerico o si simplemente no existe en la base de datos
     * @return respuesta con codigo de estado 200 y el propietario obtenido
     */
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


    /**
     * metodo para guardar registros en la entidad propietario de la base de datos
     * @param propietario Objeto que se quiere guardar en la entidad propietario de la base de datos. el cliente pasa este objeto
     * @return respuesta con codigo de estado 201 (recurso creado) y la uri de donde del endpoint para acceder al recurso creado
     */
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Propietario propietario) {
        Propietario guardado = service.save(propietario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(guardado.getId()).toUri();
        return ResponseEntity.created(uri).body(guardado);
    }


    /**
     * metodo para editar datos de la entidad propietario de la base de datos. no se puede modificar las mascotas directamente aquí. se tiene que ir al RestControllerMascota para modificar cada una individualmente
     * @param id Id del propietario que se quiere actualizar. si no existe o es numerico se lanza una excepcion
     * @param propietario el objeto de tipo Propietario que se quiere guardar. el cliente manda este objeto en la solicitud
     * @return respuesta en el cuerpo de la solicitud con codigo de estado 200 y el objeto editado
     */
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
                return ResponseEntity.ok(propietarioObtenido);

            } else throw new NotFound("no existe un propietario con el id " + ID);

        } catch (NumberFormatException e) {
            throw new BadRequest("el id tiene que ser númerico");
        }
    }


    /**
     * metodo para eliminar un propietario (también se eliminan todas las mascotas que estén vinculadas a este propietario)
     * @param id el id del propietario que se quiere eliminar. el cliente pasa este id en la solicitud
     * @return codigo de estado 204 NOT_CONTENT
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            Integer ID = Integer.valueOf(id);

            if (service.existById(ID)) {
                service.delete(service.findByid(ID));
                return ResponseEntity.noContent().build();
            } else throw new NotFound("no existe un propietario con el id " + ID);

        } catch (NumberFormatException e) {
            throw new BadRequest("el id tiene que ser numérico");
        }

    }


}
