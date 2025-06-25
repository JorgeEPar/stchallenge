package com.stchallenge.infrastructure.adapter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.stchallenge.application.port.out.TransferenciaPort;
import com.stchallenge.domain.model.Empresa;
import com.stchallenge.infrastructure.jpa.TransferenciaJpaRepository;
import com.stchallenge.infrastructure.persistance.EmpresaEntity;

@Repository
public class TransferenciaAdapterJpa implements TransferenciaPort {
	
	private final TransferenciaJpaRepository transferenciaRepository;

	public TransferenciaAdapterJpa(TransferenciaJpaRepository transferenciaRepository) {
		this.transferenciaRepository = transferenciaRepository;
	}

	@Override
	public List<Empresa> buscarEmpresasConTransferenciasDesde(LocalDate fecha) {
		List<EmpresaEntity> empresas = transferenciaRepository.findEmpresasConTransferenciasDesde(fecha);
		return empresas.stream().map(e -> new Empresa(e.getCuit(), e.getRazonSocial(), e.getFechaAdhesion()))
				.collect(Collectors.toList());
	}

}
