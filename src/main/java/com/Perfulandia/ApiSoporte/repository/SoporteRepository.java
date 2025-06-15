package com.Perfulandia.ApiSoporte.repository;

import com.Perfulandia.ApiSoporte.models.Soporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoporteRepository extends JpaRepository<Soporte, Integer> {
}