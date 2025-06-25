package com.stchallenge.application.port.in;

import java.util.List;

import com.stchallenge.domain.model.Empresa;

public interface GestionEmpresaUseCase {

	Empresa adherirEmpresa(Empresa empresa);

	List<Empresa> obtenerEmpresasAdheridasUltimoMes();
}
