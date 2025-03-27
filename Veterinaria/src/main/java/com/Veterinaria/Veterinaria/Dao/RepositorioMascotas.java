package com.Veterinaria.Veterinaria.Dao;

import com.Veterinaria.Veterinaria.Model.Mascota;
import org.springframework.data.repository.CrudRepository;

/**
 * bean que contiene los metodos para manejar los registros de las mascotas
 */
public interface RepositorioMascotas extends CrudRepository<Mascota,Integer> {
}
