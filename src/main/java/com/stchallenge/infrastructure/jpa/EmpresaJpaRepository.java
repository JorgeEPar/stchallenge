package com.stchallenge.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stchallenge.infrastructure.persistance.EmpresaEntity;

public interface EmpresaJpaRepository extends JpaRepository<EmpresaEntity, String> {

}
