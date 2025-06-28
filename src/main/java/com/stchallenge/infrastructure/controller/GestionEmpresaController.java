package com.stchallenge.infrastructure.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stchallenge.application.port.in.GestionEmpresaUseCase;
import com.stchallenge.domain.model.Empresa;
import com.stchallenge.infrastructure.controller.dto.EmpresaRequest;
import com.stchallenge.infrastructure.controller.dto.EmpresaResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empresas")
public class GestionEmpresaController {

	private final GestionEmpresaUseCase gestionEmpresaUseCase;

	public GestionEmpresaController(GestionEmpresaUseCase gestionEmpresaUseCase) {
		this.gestionEmpresaUseCase = gestionEmpresaUseCase;
	}

	@PostMapping("/adherir")
	public EmpresaResponse adherirEmpresa(@Valid @RequestBody EmpresaRequest request) {
		final Empresa empresa = new Empresa(request.getCuit(), request.getRazon_social(), null);
		final Empresa empresaAdherida = this.gestionEmpresaUseCase.adherirEmpresa(empresa);
		return new EmpresaResponse(empresaAdherida.cuit(), empresa.razonSocial(), empresaAdherida.fechaAdhesion());
	}

	@GetMapping("/adheridas-ultimo-mes")
	public List<EmpresaResponse> empresasAdheridas() {
		final List<Empresa> empresasAdheridas = gestionEmpresaUseCase.obtenerEmpresasAdheridasUltimoMes();
		return mapToEmpresaResponse(empresasAdheridas);
	}

	@GetMapping("/transferencias-ultimo-mes")
	public List<EmpresaResponse> empresasConTransferencias() {
		final List<Empresa> empresasAdheridas = gestionEmpresaUseCase.obtenerEmpresasConTransferenciasUltimoMes();
		return mapToEmpresaResponse(empresasAdheridas);
	}

	private List<EmpresaResponse> mapToEmpresaResponse(final List<Empresa> empresasAdheridas) {
		return empresasAdheridas.stream().map(e -> new EmpresaResponse(e.cuit(), e.razonSocial(), e.fechaAdhesion()))
				.collect(Collectors.toList());
	}
}
