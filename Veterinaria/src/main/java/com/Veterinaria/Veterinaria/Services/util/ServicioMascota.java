package com.Veterinaria.Veterinaria.Services.util;

import com.Veterinaria.Veterinaria.Dao.RepositorioMascotas;
import com.Veterinaria.Veterinaria.Model.Mascota;
import com.Veterinaria.Veterinaria.Services.IMascota;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class ServicioMascota implements IMascota {

    @Autowired
    private RepositorioMascotas dao;

    @Override
    public Mascota save(Mascota m) {
        return dao.save(m);
    }

    @Override
    public Mascota findById(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public boolean existById(Integer id) {
        return dao.existsById(id);
    }

    @Override
    public List<Mascota> findAll() {
        Iterable<Mascota> all = dao.findAll();
        return StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Long count() {
        return dao.count();
    }

    @Override
    public void delete(Mascota m) {
       dao.delete(m);
    }
}
