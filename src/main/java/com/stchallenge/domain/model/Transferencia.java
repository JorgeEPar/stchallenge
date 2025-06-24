/**
 * 
 */
package com.stchallenge.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transferencia(Long id, String cuitEmpresa, BigDecimal importe, String cuentaDebito, String cuentaCredito,
		LocalDate fecha) {

}
