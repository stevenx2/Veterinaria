package com.Veterinaria.Veterinaria.Services;


import com.Veterinaria.Veterinaria.Model.Propietario;

import java.util.List;

/**
 * Interfaz con metodos para manejar la pauta del acceso a datos de la entidad Propietario
 */
public interface IPropietario {

    /**
     *
     * @return una lista con todos los propietarios
     */
    List<Propietario> findAll();

    /**
     *
     * @param id identificador del propietario
     * @return el propietario encontrado
     */
    Propietario findByid(Integer id);

    /**
     *
     * @param p Objeto Propietario que se va a guardar
     * @return el Propietario guardado
     */
    Propietario save(Propietario p);

    /**
     *
     * @param p el objeto Propietario que se va a eliminar
     */
    void delete(Propietario p);

    /**
     *
     * @param id identificador del propietario
     * @return true si existe o false si no existe
     */
    boolean existById(Integer id);

    /**
     *
     * @return total de registros de Propietarios
     */
    Long count();
}
