package com.stchallenge.application.port.out;

import java.time.LocalDate;
import java.util.List;

import com.stchallenge.domain.model.Empresa;

public interface EmpresaPort {
	Empresa guardar(Empresa empresa);
	List<Empresa> buscarAdheridasDesde(LocalDate fecha);
}
