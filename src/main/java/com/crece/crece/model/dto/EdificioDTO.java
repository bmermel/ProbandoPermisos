package com.crece.crece.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class EdificioDTO {
        private String nombre;
        private String direccion;
        private String razonSocial;
        private int cuit;
    }
