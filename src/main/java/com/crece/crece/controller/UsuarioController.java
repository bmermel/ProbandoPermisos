package com.crece.crece.controller;

import com.crece.crece.model.Usuario;
import com.crece.crece.model.dto.ActualizarUsuarioDTO;
import com.crece.crece.model.dto.GetUsuarioDTO;
import com.crece.crece.model.dto.UsuarioDTO;
import com.crece.crece.model.dto.UsuarioDashboardDTO;
import com.crece.crece.service.UsuarioService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private  UsuarioService usuarioService;



    @GetMapping("/all")
    public List<GetUsuarioDTO> getUsuarios(){
        return usuarioService.getUsuarios();
    }

    @PutMapping("/{id}")
    public void modificarUsuario(@PathVariable Long id, @RequestBody ActualizarUsuarioDTO actualizarUsuarioDTO){
        UsuarioDTO user = usuarioService.leerUsuario(id);

        if(user != null){
            usuarioService.modificarUsuario(actualizarUsuarioDTO);
        }
    }
    @GetMapping("/emailsPorEdificio/{edificioId}/{destinatario}")
    public ResponseEntity<List<String>> obtenerEmailsPorEdificio(@PathVariable Long edificioId,
                                                                 @PathVariable String destinatario) {
        List<String> emails = usuarioService.getEmailsPorEdificio(edificioId,destinatario);
        return ResponseEntity.status(HttpStatus.OK).body(emails);
    }
    @GetMapping("/emailsPorEdificioSinTipo/{edificioId}")
    public ResponseEntity<List<String>> obtenerEmailsPorEdificioSinDestinatario(@PathVariable Long edificioId) {
        List<String> emails = usuarioService.getEmailsPorEdificioSinTipo(edificioId);
        return ResponseEntity.status(HttpStatus.OK).body(emails);
    }
    @PatchMapping("/{idUsuario}/cambiar-estado")
    public ResponseEntity<String> cambiarEstadoUsuario(@PathVariable Long idUsuario) {

        try {
            usuarioService.cambiarEstadoUsuario(idUsuario);
            return ResponseEntity.ok("Estado del usuario cambiado exitosamente.");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<UsuarioDashboardDTO> getUsuarioByMail(@PathVariable String email) {
        try {
            UsuarioDashboardDTO usuarioDTO = usuarioService.getUsuarioByEmail(email);

            if (usuarioDTO != null) {
                return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam("email") String email, @RequestParam("newPassword") String newPassword) {
        try {
            // Lógica para cambiar la contraseña y actualizar en la base de datos
            usuarioService.changePassword(email, newPassword);

            // Opcional: Enviar un correo de confirmación
            // mailService.sendPasswordChangeConfirmation(email);

            return ResponseEntity.ok("Password changed successfully");
        } catch (Exception e) {
            // Manejar cualquier error que pueda ocurrir durante el cambio de contraseña
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to change password");
        }
}
}
