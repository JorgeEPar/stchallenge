package com.stchallenge.infrastructure.persistance;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "empresa")
public final class EmpresaEntity {
	@Id
	private String cuit;
	private String razonSocial;
	private LocalDate fechaAdhesion;
	
	public EmpresaEntity() {}
	
	public EmpresaEntity(String cuit, String razonSocial, LocalDate fechaAdhesion) {
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
