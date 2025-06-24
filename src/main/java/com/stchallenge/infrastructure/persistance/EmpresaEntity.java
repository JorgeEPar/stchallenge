package com.stchallenge.infrastructure.persistance;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EmpresaEntity {
	@Id
	private String cuit;
	private String razonSocial;
	private LocalDate fechaAdhesion;

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
