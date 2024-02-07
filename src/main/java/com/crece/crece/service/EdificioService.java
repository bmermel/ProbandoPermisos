package com.crece.crece.service;

import com.crece.crece.model.Edificio;

import com.crece.crece.model.dto.EdificioDTO;
import com.crece.crece.model.dto.GetEdificioListDto;
import com.crece.crece.repository.IEdificioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EdificioService {
    @Autowired
    private IEdificioRepository edificioRepository;

    @Autowired
    ObjectMapper mapper;

    private void guardarEdificio(EdificioDTO edificioDTO){
        Edificio edificio = mapper.convertValue(edificioDTO, Edificio.class);
        edificioRepository.save(edificio);
    }

    public void crearEdificio(EdificioDTO edificioDTO) {
        guardarEdificio(edificioDTO);
    }

    public Optional<Edificio> leerEdificio(Long id) {
        Optional<Edificio> edificio = edificioRepository.findById(id);
        if(edificio.isPresent())
            return edificio;
        return null;
    }

    public void eliminarEdificio(Long id) {
        edificioRepository.deleteById(id);
}

    public List<GetEdificioListDto> getEdificios (){
        List<Edificio> edificioList = edificioRepository.findAll();

        List<GetEdificioListDto> edificioListDtos = new ArrayList<>();

        for(Edificio edificio : edificioList){
            GetEdificioListDto getEdificioListDto = mapper.convertValue(edificio, GetEdificioListDto.class);
            edificioListDtos.add(getEdificioListDto);
        }
        return edificioListDtos;
    }

}
