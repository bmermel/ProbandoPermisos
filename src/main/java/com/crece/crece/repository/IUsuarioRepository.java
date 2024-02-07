package com.crece.crece.repository;

import com.crece.crece.model.Edificio;
import com.crece.crece.model.Usuario;
import com.crece.crece.model.dto.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface IUsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmail(String userEmail);


    List<Usuario> findByEdificioId(Long edificioId);
}
