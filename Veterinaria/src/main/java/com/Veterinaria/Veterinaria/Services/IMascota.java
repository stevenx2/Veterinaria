package com.Veterinaria.Veterinaria.Services;

import com.Veterinaria.Veterinaria.Model.Mascota;

import java.util.List;

public interface IMascota {
    Mascota save(Mascota m);

    Mascota findById(Integer id);

    boolean existById(Integer id);

    List<Mascota> findAll();

    Long count();

    void delete(Mascota m);

}
