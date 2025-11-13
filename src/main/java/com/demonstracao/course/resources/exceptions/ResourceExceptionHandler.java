package com.demonstracao.course.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demonstracao.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Recurso não encontrado"; // Mensagem de erro padrão
		HttpStatus status = HttpStatus.NOT_FOUND; // Código de status HTTP 404
		
		// Criação do objeto StandardError com os detalhes do erro
		StandardError err = new StandardError(
				System.currentTimeMillis(), 
				status.value(), 
				error, 
				e.getMessage(), 
				request.getRequestURI()
				);
		
		return ResponseEntity.status(status).body(err);
	}
}
