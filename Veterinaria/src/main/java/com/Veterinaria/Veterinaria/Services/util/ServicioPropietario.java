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

/**
 * Implementacion de la interfaz IPropietario que realiza un crud basico
 */
@Service
@Transactional
public class ServicioPropietario implements IPropietario {

    /**
     * bean de RepositorioPropietario
     */
    @Autowired
    private RepositorioPropietario dao;

    /**
     *
     * @return todos los registros de la entidad Propietarios de la base de datos
     */
    @Override
    public List<Propietario> findAll() {
        Iterable<Propietario> all = dao.findAll();
        return StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());
    }

    /**
     *
     * @param id identificador del propietario
     * @return el Propietario encontrado o null si no existe
     */
    @Override
    public Propietario findByid(Integer id) {
        return dao.findById(id).orElse(null);
    }

    /**
     *
     * @param p Objeto Propietario que se va a guardar
     * @return el Propietario guardado con su nuevo identificador
     */
    @Override
    public Propietario save(Propietario p) {
        return dao.save(p);
    }

    /**
     *
     * @param p el objeto Propietario que se va a eliminar
     */
    @Override
    public void delete(Propietario p) {
        dao.delete(p);
    }

    /**
     *
     * @param id identificador del propietario
     * @return true si existe o false si no existe
     */
    @Override
    public boolean existById(Integer id) {
        return dao.existsById(id);
    }

    /**
     *
     * @return el total de registros en la base de datos
     */
    @Override
    public Long count() {
        return dao.count();
    }
}
