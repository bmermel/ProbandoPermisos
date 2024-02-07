package com.crece.crece.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActualizarUsuarioDTO {
    private String nombre;
    private String apellido;
}
