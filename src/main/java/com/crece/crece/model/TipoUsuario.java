package com.crece.crece.model;


import com.crece.crece.model.enums.Tipos;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tipoUsuario")
public class TipoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipos tipo;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoUsuario")
    private List<Usuario> usuarios;
}
