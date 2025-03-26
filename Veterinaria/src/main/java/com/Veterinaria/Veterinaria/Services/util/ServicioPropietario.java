package com.Veterinaria.Veterinaria.Services.util;


import com.Veterinaria.Veterinaria.Dao.RepositorioPropietario;
import com.Veterinaria.Veterinaria.Model.Propietario;
import com.Veterinaria.Veterinaria.Services.IPropietario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class ServicioPropietario implements IPropietario {

    @Autowired
    private RepositorioPropietario dao;

    @Override
    public List<Propietario> findAll() {
        Iterable<Propietario> all = dao.findAll();
        return StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Propietario findByid(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Propietario save(Propietario p) {
        return dao.save(p);
    }

    @Override
    public void delete(Propietario p) {
        dao.delete(p);
    }

    @Override
    public boolean existById(Integer id) {
        return dao.existsById(id);
    }

    @Override
    public Long count() {
        return dao.count();
    }
}
