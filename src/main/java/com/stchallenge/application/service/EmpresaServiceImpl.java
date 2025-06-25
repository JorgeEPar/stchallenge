package com.stchallenge.application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stchallenge.application.port.in.GestionEmpresaUseCase;
import com.stchallenge.application.port.out.EmpresaPort;
import com.stchallenge.domain.model.Empresa;

@Service
public class EmpresaServiceImpl implements GestionEmpresaUseCase {

	private final EmpresaPort empresaPort;

	public EmpresaServiceImpl(EmpresaPort empresaPort) {
		this.empresaPort = empresaPort;
	}

	@Override
	public Empresa adherirEmpresa(Empresa empresa) {
		Empresa emp = new Empresa(empresa.cuit(), empresa.razonSocial(), LocalDate.now());
		return empresaPort.guardar(emp);
	}

	@Override
	public List<Empresa> obtenerEmpresasAdheridasUltimoMes() {
		return empresaPort.buscarAdheridasDesde(LocalDate.now().minusMonths(1));
	}
}
