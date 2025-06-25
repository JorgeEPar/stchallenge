package com.stchallenge.application.port.out;

import java.time.LocalDate;
import java.util.List;

import com.stchallenge.domain.model.Empresa;

public interface TransferenciaPort {
	List<Empresa> buscarEmpresasConTransferenciasDesde(LocalDate fecha);
}
