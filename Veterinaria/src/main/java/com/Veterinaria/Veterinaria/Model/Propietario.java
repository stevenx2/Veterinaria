package com.Veterinaria.Veterinaria.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto plano donde de almacenan los registros de la entidad propietario de la base de datos
 */
@Data
@NoArgsConstructor
@Table(name = "propietario")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Propietario {

    @Id
    @Column(name = "id_propietario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "edad")
    private int edad;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propietario")
    private List<Mascota> mascotas = new ArrayList<>();

    public Propietario(String nombre, String apellido, int edad, List<Mascota> mascotas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.mascotas = mascotas;
    }


    /**
     * este metodo agrega una nueva mascota al propietario y se le asigna el propietario actual a la mascota
     * @param mascota la Mascota que se va a agregar al Propietario
     */
    public void addMascota(Mascota mascota) {
        mascotas.add(mascota);
        mascota.setPropietario(this);
    }

    /**
     *
     * @param mascota el objeto Mascota que se va a eliminar del propietario
     */
    public void removeMascota(Mascota mascota) {
        mascotas.remove(mascota);
        mascota.setPropietario(null);
    }
}
