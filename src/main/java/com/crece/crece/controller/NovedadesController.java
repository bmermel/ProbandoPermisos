package com.crece.crece.controller;

import com.crece.crece.model.dto.EdificioDTO;
import com.crece.crece.model.dto.GetEdificioListDto;
import com.crece.crece.model.dto.NovedadesDTO;
import com.crece.crece.service.EdificioService;
import com.crece.crece.service.NovedadesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/novedades")
@CrossOrigin(origins = "*")
public class NovedadesController {
    @Autowired
    NovedadesService service;

    @Autowired
    private ObjectMapper mapper;

    @Secured("ADMIN")
    @PostMapping()
    public ResponseEntity<?> crearNovedad(@RequestBody NovedadesDTO novedadesDTO) throws MessagingException, UnsupportedEncodingException {
        service.guardarNovedad(novedadesDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarNovedad(@PathVariable Long id, @RequestBody NovedadesDTO novedadesDTO){
        try {
            service.editarNovedad(id, novedadesDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Novedad no encontrada", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al editar la novedad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarNovedad(@PathVariable Long id) {
        try {
            service.eliminarNovedad(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("Novedad no encontrada", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la novedad", HttpStatus.NOT_FOUND);
        }
    }
   /* @GetMapping()
    public ResponseEntity<List<NovedadesDTO>> obtenerTodasNovedades() {
        List<NovedadesDTO> novedadesDTOList = service.obtenerNovedades();
        return new ResponseEntity<>(novedadesDTOList, HttpStatus.OK);
    }*/

    @GetMapping("/{edificioId}")
    public NovedadesDTO obtenerNovedadesSegunEdificio(@PathVariable Long edificioId) throws Exception {
        try {
            NovedadesDTO novedad = service.obtenerNovedadPorEdificioId(edificioId);
            return novedad;
    } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
