package com.stchallenge.infrastructure.controller.dto;

import java.time.LocalDate;

public class EmpresaResponse {

	private String cuit;
	private String razonSocial;
	private LocalDate fechaAdhesion;

	public EmpresaResponse(String cuit, String razonSocial, LocalDate fechaAdhesion) {
		this.cuit = cuit;
		this.razonSocial = razonSocial;
		this.fechaAdhesion = fechaAdhesion;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public LocalDate getFechaAdhesion() {
		return fechaAdhesion;
	}

	public void setFechaAdhesion(LocalDate fechaAdhesion) {
		this.fechaAdhesion = fechaAdhesion;
	}
}
