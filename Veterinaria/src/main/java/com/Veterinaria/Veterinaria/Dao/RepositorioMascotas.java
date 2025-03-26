package com.Veterinaria.Veterinaria.Dao;

import com.Veterinaria.Veterinaria.Model.Mascota;
import org.springframework.data.repository.CrudRepository;

public interface RepositorioMascotas extends CrudRepository<Mascota,Integer> {
}
