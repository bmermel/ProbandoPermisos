package com.crece.crece.service;

import com.crece.crece.model.RolUsuario;
import com.crece.crece.model.TipoUsuario;
import com.crece.crece.model.dto.RolUsuarioDto;
import com.crece.crece.model.dto.TipoUsuarioDto;
import com.crece.crece.repository.IRolUsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolUsuarioService {
    private IRolUsuarioRepository rolUsuarioRepository;
    private ObjectMapper mapper;

    @Autowired
    public RolUsuarioService(IRolUsuarioRepository rolUsuarioRepository, ObjectMapper mapper) {
        this.rolUsuarioRepository = rolUsuarioRepository;
        this.mapper = mapper;
    }
    public void guardarRolusuario(RolUsuarioDto rolUsuarioDto){
        RolUsuario rolUsuario = mapper.convertValue(rolUsuarioDto, RolUsuario.class);
        rolUsuarioRepository.save(rolUsuario);
    }
    public RolUsuarioDto buscarRolUsuario(Long idRolUsuario) {
        Optional<RolUsuario> rolUsuario =  rolUsuarioRepository.findById(idRolUsuario);
        RolUsuarioDto rolUsuarioDto = null;
        if(rolUsuario.isPresent()){
            rolUsuarioDto = mapper.convertValue(rolUsuario, RolUsuarioDto.class);
        }
        return rolUsuarioDto;
    }
    public RolUsuario convertirDtoAClase(RolUsuarioDto rolUsuarioDto){
        return mapper.convertValue(rolUsuarioDto, RolUsuario.class);
    }
}
