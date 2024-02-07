package com.crece.crece.controller;

import com.crece.crece.model.dto.EdificioDTO;
import com.crece.crece.model.dto.GetEdificioListDto;
import com.crece.crece.service.EdificioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edificio")
@CrossOrigin(origins = "*")
public class EdificioController {
    @Autowired
    EdificioService edificioService;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping()
    public ResponseEntity<?> crearEdificio(@RequestBody EdificioDTO edificioDTO){
        edificioService.crearEdificio(edificioDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public EdificioDTO getArchivo(@PathVariable Long id){
        return  mapper.convertValue( edificioService.leerEdificio(id), EdificioDTO.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEdificio(@PathVariable Long id){
        ResponseEntity<String> response = null;
        edificioService.eliminarEdificio(id);
        response = ResponseEntity.status(HttpStatus.OK).body("Eliminado");
        return response;
    }
    @GetMapping()
    public List<GetEdificioListDto> getEdificios () {
        return edificioService.getEdificios();
    }
}
