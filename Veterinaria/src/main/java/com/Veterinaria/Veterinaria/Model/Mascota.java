package com.Veterinaria.Veterinaria.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "mascotas")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private Integer id;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "id_propietario")
    private Propietario propietario;

    public Mascota(String tipo, String nombre, LocalDate fechaIngreso) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
    }
}
