package com.crece.crece.model.dto;

import lombok.Getter;
import lombok.Setter;

 @Getter
 @Setter
    public class ArchivoDTO {
        private Long id;
        private String categoria;
        private String descripcion;
        private String fechaDeIngreso;
        private String destinatario;
        private String filePath;
    }
