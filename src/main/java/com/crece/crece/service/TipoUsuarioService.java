package com.crece.crece.service;

import com.crece.crece.model.Edificio;
import com.crece.crece.model.TipoUsuario;
import com.crece.crece.model.Usuario;
import com.crece.crece.model.dto.EdificioDTO;
import com.crece.crece.model.dto.TipoUsuarioDto;
import com.crece.crece.model.dto.UsuarioDTO;
import com.crece.crece.repository.ITipoUsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoUsuarioService {

    private ITipoUsuarioRepository tipoUsuarioRepository;
    private ObjectMapper mapper;

    @Autowired
    public TipoUsuarioService(ITipoUsuarioRepository tipoUsuarioRepository, ObjectMapper mapper) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
        this.mapper = mapper;
    }
    public void guardarTipoUsuario(TipoUsuarioDto tipoUsuarioDto){
        TipoUsuario tipoUsuario = mapper.convertValue(tipoUsuarioDto, TipoUsuario.class);
        tipoUsuarioRepository.save(tipoUsuario);
    }

    public TipoUsuarioDto buscarTipoUsuario(Long idTipoUsuario) {
        Optional<TipoUsuario> tipoUsuario =  tipoUsuarioRepository.findById(idTipoUsuario);
        TipoUsuarioDto tipoUsuarioDto = null;
            if(tipoUsuario.isPresent()){
                tipoUsuarioDto = mapper.convertValue(tipoUsuario, TipoUsuarioDto.class);
            }
            return tipoUsuarioDto;
    }
    public TipoUsuario convertirDtoAClase(TipoUsuarioDto tipoUsuarioDto){
        return mapper.convertValue(tipoUsuarioDto, TipoUsuario.class);
    }
}
