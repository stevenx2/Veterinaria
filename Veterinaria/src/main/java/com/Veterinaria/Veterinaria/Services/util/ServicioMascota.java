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

/**
 * Implementacion de IMascota con un crud basico
 */
@Service
@Transactional
public class ServicioMascota implements IMascota {

    /**
     * bean de RepositorioMascotas para acceder a los registros de la entidad Mascota en la base de datos
     */
    @Autowired
    private RepositorioMascotas dao;

    /**
     *
     * @param m es el objeto de tipo Mascota que va a ser guardado
     * @return el objeto que se guardó con su nuevo identificador
     */
    @Override
    public Mascota save(Mascota m) {
        return dao.save(m);
    }

    /**
     *
     * @param id identificador de la mascota
     * @return el objeto Mascota encontrado o null si no existe
     */
    @Override
    public Mascota findById(Integer id) {
        return dao.findById(id).orElse(null);
    }

    /**
     *
     * @param id identificador de la mascota
     * @return true si existe o false si no existe
     */
    @Override
    public boolean existById(Integer id) {
        return dao.existsById(id);
    }

    /**
     *
     * @return todos los registros de la entidad Mascota en la base de datos
     */
    @Override
    public List<Mascota> findAll() {
        Iterable<Mascota> all = dao.findAll();
        return StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());
    }

    /**
     *
     * @return el total de registros en la entidad Mascota
     */
    @Override
    public Long count() {
        return dao.count();
    }

    /**
     *
     * @param m el objeto de tipo Mascota que va a ser eliminado
     */
    @Override
    public void delete(Mascota m) {
       dao.delete(m);
    }
}
