package com.crece.crece.repository;

import com.crece.crece.model.Archivo;
import com.crece.crece.model.Novedades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository


public interface NovedadesRepository extends JpaRepository<Novedades,Long> {

    Optional<Novedades> findByEdificioId(Long edificioId);
}
