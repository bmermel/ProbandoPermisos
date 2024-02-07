package com.crece.crece.repository;

import com.crece.crece.model.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
}
