package com.crece.crece.model.dto;

import com.crece.crece.model.Edificio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.websocket.server.ServerEndpoint;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDashboardDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private Edificio edificio;

    private Boolean habilitado;
    private String telefono;
    private String unidadFuncional;


}
