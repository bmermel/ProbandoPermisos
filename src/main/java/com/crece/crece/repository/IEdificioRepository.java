package com.crece.crece.repository;

import com.crece.crece.model.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEdificioRepository extends JpaRepository<Edificio, Long> {
}
