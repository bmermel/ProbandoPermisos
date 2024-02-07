package com.crece.crece.model.dto;

import com.crece.crece.model.enums.Tipos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoUsuarioDto {
    private Tipos tipo;
}
