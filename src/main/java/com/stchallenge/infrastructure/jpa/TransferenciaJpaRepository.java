package com.stchallenge.infrastructure.jpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.stchallenge.infrastructure.persistance.EmpresaEntity;
import com.stchallenge.infrastructure.persistance.TransferenciaEntity;

public interface TransferenciaJpaRepository extends CrudRepository<TransferenciaEntity, Long> {

	@Query("SELECT DISTINCT e FROM EmpresaEntity e "
			+ "JOIN TransferenciaEntity t ON e.cuit = t.cuitEmpresa "
			+ "WHERE t.fecha > :date")
	List<EmpresaEntity> findEmpresasConTransferenciasDesde(LocalDate date);

}
