package com.stchallenge.infrastructure.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stchallenge.domain.exception.AdhesionEmpresaException;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(AdhesionEmpresaException.class)
	public ResponseEntity<?> handleBusinessException(AdhesionEmpresaException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(errorBody(HttpStatus.CONFLICT.value(), "Error de negocio.", e.getMessage()));
	}

	private Map<String, Object> errorBody(int status, String error, String message) {
		return Map.of("status", status, "error", error, "message", message);
	}
}
