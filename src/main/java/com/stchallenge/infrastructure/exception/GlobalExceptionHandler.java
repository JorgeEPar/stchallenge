package com.stchallenge.infrastructure.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
	       String mensaje = ex.getBindingResult().getFieldErrors().stream()
	               .map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.joining(", "));

			return ResponseEntity.badRequest()
					.body(errorBody(HttpStatus.BAD_REQUEST.value(), "Request validation error.", mensaje));
		}

		private Map<String, Object> errorBody(int status, String error, String message) {
			return Map.of("status", status, "error", error, "message", message);
		}
}
