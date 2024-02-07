package com.crece.crece.model;


import com.fasterxml.jackson.annotation.*;
import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "edificio")
public class Edificio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "direccion", nullable = false)
    private String direccion;


    @Column(name = "razonSocial", nullable = false)
    private String razonSocial;

    @Column(name = "cuit", nullable = false)
    private int cuit;


    @JsonIgnore
    @OneToMany (mappedBy = "edificio")
    private List<Usuario> usuarios;
    @OneToMany (mappedBy = "edificio")
    private List<Archivo> archivoList;
}

