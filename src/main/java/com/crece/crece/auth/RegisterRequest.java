package com.crece.crece.auth;

import com.crece.crece.model.Edificio;
import com.crece.crece.model.RolUsuario;
import com.crece.crece.model.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
     String nombre;
     String apellido;
     String email;
     String password;
     Long idRolUsuario;
     Long idEdificio;
     Long idTipoUsuario;
     String telefono;
     String unidadFuncional;

     @JsonProperty()
     public String getPassword(){
     return password;
     }
}
