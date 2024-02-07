package com.crece.crece.repository;

import com.crece.crece.model.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ArchivoRepository extends JpaRepository<Archivo, Long> {
    Optional<Archivo> findByName(String fileName);
    List<Archivo> findAllByFechaCargaBetween(LocalDate fechaInicio, LocalDate fechaFin);
    List<Archivo> findAllByOrderByFechaCargaAsc();
    List<Archivo> findAllByOrderByFechaCargaDesc();
    void deleteById(Long id);


    List<Archivo> findByNameContainingIgnoreCase(String nombre);

    List<Archivo> findByType(String categoria);

    List<Archivo> findByEdificio_Id(Long edificioId);
}