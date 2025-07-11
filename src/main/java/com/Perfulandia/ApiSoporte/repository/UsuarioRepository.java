package com.Perfulandia.ApiSoporte.repository;

import com.Perfulandia.ApiSoporte.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}