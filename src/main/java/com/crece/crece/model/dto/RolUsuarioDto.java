package com.crece.crece.model.dto;

import com.crece.crece.model.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RolUsuarioDto {
    private Roles rol;
}
