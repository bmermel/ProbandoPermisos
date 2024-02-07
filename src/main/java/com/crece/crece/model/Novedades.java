package com.crece.crece.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@Table(name = "novedades")
@NoArgsConstructor
@Builder
public class Novedades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String texto;

    private LocalDate fecha;
    private Boolean sendEmail;


    @JoinColumn(name = "edificio_id")
    @OneToOne(cascade = CascadeType.MERGE)
    private Edificio edificio;

}


