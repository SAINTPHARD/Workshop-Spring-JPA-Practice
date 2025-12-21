package com.demonstracao.course.resources.exceptions;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demonstracao.course.services.exceptions.DatabaseException;
import com.demonstracao.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    // ===================================================================================
    //                               ERROS DE CLIENTE (4xx)
    // ===================================================================================

    /**
     * Trata erro 404 (Not Found)
     * Disparado quando um ID não é encontrado no banco.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> handleNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Recurso não encontrado",
                e.getMessage(),
                request.getRequestURI()
        );
        
        return ResponseEntity.status(status).body(err);
    }

    /**
     * Trata erro 409 (Conflict)
     * Disparado quando há violação de integridade (ex: deletar usuário com pedidos).
     */
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> handleDatabase(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Conflito de Dados",
                "Operação não permitida: Violação de integridade referencial.",
                request.getRequestURI()
        );
        
        return ResponseEntity.status(status).body(err);
    }

    /**
     * Trata erro 400 (Bad Request)
     * Disparado quando a validação (@Valid) de campos falha.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;

        // Coleta todos os erros de campo em uma única string formatada
        String fieldsSummary = e.getBindingResult().getFieldErrors().stream()
                .map(this::formatFieldError)
                .collect(Collectors.joining("; "));

        String message = fieldsSummary.isBlank() ? "Erro de validação nos campos enviada." : fieldsSummary;

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Dados inválidos",
                message,
                request.getRequestURI()
        );
        
        return ResponseEntity.status(status).body(err);
    }

    // ===================================================================================
    //                               ERROS DE SERVIDOR (5xx)
    // ===================================================================================

    /**
     * Trata erro 500 (Internal Server Error)
     * Fallback para qualquer erro não previsto. Oculta detalhes técnicos por segurança.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleGeneral(Exception e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Erro interno do servidor",
                "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.",
                request.getRequestURI()
        );
        
        return ResponseEntity.status(status).body(err);
    }

    // ===================================================================================
    //                                  MÉTODOS AUXILIARES
    // ===================================================================================

    private String formatFieldError(FieldError fe) {
        String msg = fe.getDefaultMessage();
        if (msg == null || msg.isBlank()) msg = "valor inválido";
        return fe.getField() + ": " + msg;
    }
}