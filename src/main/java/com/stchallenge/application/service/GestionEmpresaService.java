package com.stchallenge.application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stchallenge.application.port.in.GestionEmpresaUseCase;
import com.stchallenge.application.port.out.EmpresaPort;
import com.stchallenge.application.port.out.TransferenciaPort;
import com.stchallenge.domain.exception.BusinessException;
import com.stchallenge.domain.model.Empresa;

@Service
public class GestionEmpresaService implements GestionEmpresaUseCase {

	private final EmpresaPort empresaPort;
	private final TransferenciaPort transferenciaPort;

	public GestionEmpresaService(EmpresaPort empresaPort, TransferenciaPort transferenciaPort) {
		this.empresaPort = empresaPort;
		this.transferenciaPort = transferenciaPort;
	}

	@Override
	public Empresa adherirEmpresa(Empresa empresa) {
		if (empresaPort.existeAdheridaPorCuit(empresa.cuit())) {
			throw new BusinessException("La empresa con el CUIT " + empresa.cuit() + " ya esta adherida.");
		}
		Empresa emp = new Empresa(empresa.cuit(), empresa.razonSocial(), LocalDate.now());
		return empresaPort.guardar(emp);
	}

	@Override
	public List<Empresa> obtenerEmpresasAdheridasUltimoMes() {
		return empresaPort.buscarAdheridasDesde(LocalDate.now().minusMonths(1));
	}

	@Override
	public List<Empresa> obtenerEmpresasConTransferenciasUltimoMes() {
		return transferenciaPort.buscarEmpresasConTransferenciasDesde(LocalDate.now().minusMonths(1));
	}
}
