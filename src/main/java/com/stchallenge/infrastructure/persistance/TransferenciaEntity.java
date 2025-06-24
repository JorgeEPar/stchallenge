package com.stchallenge.infrastructure.persistance;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TransferenciaEntity {

	@Id
	@GeneratedValue
	private Long id;
	private String cuitEmpresa;
	private BigDecimal importe;
	private String cuentaDebito;
	private String cuentaCredito;
	private LocalDate fecha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCuitEmpresa() {
		return cuitEmpresa;
	}

	public void setCuitEmpresa(String cuitEmpresa) {
		this.cuitEmpresa = cuitEmpresa;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getCuentaDebito() {
		return cuentaDebito;
	}

	public void setCuentaDebito(String cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
	}

	public String getCuentaCredito() {
		return cuentaCredito;
	}

	public void setCuentaCredito(String cuentaCredito) {
		this.cuentaCredito = cuentaCredito;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
}
