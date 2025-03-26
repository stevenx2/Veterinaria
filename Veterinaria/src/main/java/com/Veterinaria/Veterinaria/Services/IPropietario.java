package com.Veterinaria.Veterinaria.Services;


import com.Veterinaria.Veterinaria.Model.Propietario;

import java.util.List;

public interface IPropietario {
    List<Propietario> findAll();

    Propietario findByid(Integer id);

    Propietario save(Propietario p);

    void delete(Propietario p);

    boolean existById(Integer id);

    Long count();
}
