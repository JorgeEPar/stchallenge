package com.stchallenge.domain.model;

import java.time.LocalDate;

public record Empresa(String cuit, String razonSocial, LocalDate fechaAdhesion) {
}
