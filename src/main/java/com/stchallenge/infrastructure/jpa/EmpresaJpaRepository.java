package com.stchallenge.infrastructure.jpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stchallenge.infrastructure.persistance.EmpresaEntity;

public interface EmpresaJpaRepository extends JpaRepository<EmpresaEntity, String> {
	List<EmpresaEntity> findByFechaAdhesionAfter(LocalDate date);
}
