package com.stchallenge.infrastructure.adapter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.stchallenge.application.port.out.EmpresaPort;
import com.stchallenge.domain.model.Empresa;
import com.stchallenge.infrastructure.jpa.EmpresaJpaRepository;
import com.stchallenge.infrastructure.persistance.EmpresaEntity;

@Repository
public class EmpresaAdapterJpa implements EmpresaPort {

	private final EmpresaJpaRepository repository;

	public EmpresaAdapterJpa(EmpresaJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Empresa guardar(Empresa empresa) {
		EmpresaEntity entity = new EmpresaEntity(empresa.cuit(), empresa.razonSocial(), empresa.fechaAdhesion());
		return toDomain(repository.save(entity));
	}
	
	@Override
	public List<Empresa> buscarAdheridasDesde(LocalDate fecha) {
		return repository.findByFechaAdhesionAfter(fecha)
				.stream()
				.map(this::toDomain)
				.collect(Collectors.toList());
	}

	@Override
	public boolean existeAdheridaPorCuit(String cuit) {
		return repository.existsById(cuit);
	}

	private Empresa toDomain(EmpresaEntity e) {
		return new Empresa(e.getCuit(), e.getRazonSocial(), e.getFechaAdhesion());
	}
}
