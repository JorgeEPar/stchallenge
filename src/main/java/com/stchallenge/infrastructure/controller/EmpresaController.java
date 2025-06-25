package com.stchallenge.infrastructure.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stchallenge.application.port.in.GestionEmpresaUseCase;
import com.stchallenge.domain.model.Empresa;
import com.stchallenge.infrastructure.controller.dto.EmpresaRequest;
import com.stchallenge.infrastructure.controller.dto.EmpresaResponse;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

	private final GestionEmpresaUseCase gestionEmpresaUseCase;

	public EmpresaController(GestionEmpresaUseCase gestionEmpresaUseCase) {
		this.gestionEmpresaUseCase = gestionEmpresaUseCase;
	}

	@PostMapping("/adherir")
	public EmpresaResponse adherirEmpresa(@RequestBody EmpresaRequest request) {
		final Empresa empresa = new Empresa(request.getCuit(), request.getRazon_social(), null);
		final Empresa empresaAdherida = this.gestionEmpresaUseCase.adherirEmpresa(empresa);
		return new EmpresaResponse(empresaAdherida.cuit(), empresa.razonSocial(), empresaAdherida.fechaAdhesion());
	}
	
}
