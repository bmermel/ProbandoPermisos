package com.crece.crece.controller;

import com.crece.crece.model.dto.UsuarioDTO;
import com.crece.crece.model.dto.UsuarioDashboardDTO;
import com.crece.crece.service.UsuarioService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin("*")
public class DashboardController {

    @Autowired
    private  UsuarioService usuarioService;
    private final Logger log = Logger.getLogger(DashboardController.class);

    @GetMapping("/user")
    public ResponseEntity<UsuarioDashboardDTO> getUserData(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UsuarioDashboardDTO usuarioDTO = usuarioService.getUsuarioByEmail(userDetails.getUsername());
        ResponseEntity<UsuarioDashboardDTO> response;

        if(usuarioDTO != null){
            response =  new ResponseEntity<UsuarioDashboardDTO>(usuarioDTO, HttpStatus.OK);
            log.info("se accedio correctamente al usuario: status: " + response);
        }
        else{
            response =  new ResponseEntity<>(HttpStatus.NOT_FOUND);
            log.error("hubo un error al querer acceder al usuario: status: " + response + " objeto: " + usuarioDTO);
        }
        return response;
    }

}
