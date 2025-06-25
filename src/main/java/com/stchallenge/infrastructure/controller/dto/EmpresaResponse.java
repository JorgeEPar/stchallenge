package com.stchallenge.infrastructure.controller.dto;

import java.time.LocalDate;

public class EmpresaResponse {

	private String cuit;
	private String razon_social;
	private LocalDate fecha_adhesion;
	
	public EmpresaResponse(String cuit, String razon_social, LocalDate fecha_adhesion) {
		this.cuit = cuit;
		this.razon_social = razon_social;
		this.fecha_adhesion = fecha_adhesion;
	}
	
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getRazon_social() {
		return razon_social;
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	public LocalDate getFecha_adhesion() {
		return fecha_adhesion;
	}
	public void setFecha_adhesion(LocalDate fecha_adhesion) {
		this.fecha_adhesion = fecha_adhesion;
	}	
}
