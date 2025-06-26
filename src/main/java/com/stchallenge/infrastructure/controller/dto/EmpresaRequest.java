package com.stchallenge.infrastructure.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class EmpresaRequest {
	
	@NotBlank(message = "El CUIT es obligatorio.")
	@Pattern(regexp = "^\\d{2}-\\d{8}-\\d{1}$", message = "El CUIT posee formato invalido.")
	private String cuit;
	private String razon_social;

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

}
