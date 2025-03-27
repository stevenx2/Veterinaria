package com.Veterinaria.Veterinaria.Services;

import com.Veterinaria.Veterinaria.Model.Mascota;

import java.util.List;

/**
 * Interfaz con metodos para manejar la pauta del acceso a datos de la entidad Mascota
 */
public interface IMascota {

    /**
     *
     * @param m es el objeto de tipo Mascota que va a ser guardado
     * @return la mascota guardada
     */
    Mascota save(Mascota m);

    /**
     *
     * @param id identificador de la mascota
     * @return la mascota que se encontró
     */
    Mascota findById(Integer id);

    /**
     *
     * @param id identificador de la mascota
     * @return true si existe o false si no existe
     */
    boolean existById(Integer id);

    /**
     *
     * @return la lista con todas las mascotas
     */
    List<Mascota> findAll();

    /**
     *
     * @return el total de registros
     */
    Long count();

    /**
     *
     * @param m el objeto de tipo Mascota que va a ser eliminado
     */
    void delete(Mascota m);

}
