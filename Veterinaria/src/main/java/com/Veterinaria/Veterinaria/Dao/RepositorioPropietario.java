package com.Veterinaria.Veterinaria.Dao;

import com.Veterinaria.Veterinaria.Model.Propietario;
import org.springframework.data.repository.CrudRepository;

/**
 * bean que contiene los metodos para manejar los registros de propietarios.
 */
public interface RepositorioPropietario extends CrudRepository<Propietario,Integer> {
}
