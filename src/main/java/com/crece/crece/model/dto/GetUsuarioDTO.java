package com.crece.crece.model.dto;


import com.crece.crece.model.Edificio;
import com.crece.crece.model.RolUsuario;
import com.crece.crece.model.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@RequiredArgsConstructor
public class GetUsuarioDTO {
    private String nombre;
    private String apellido;
    private String email;
    private Edificio edificio;
    private TipoUsuario tipoUsuario;
    private RolUsuario rolUsuario;

}
