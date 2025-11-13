package com.demonstracao.course.services.exceptions;

// Garanta que está herdando de "RuntimeException"
public class ResourceNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    // Construtor que recebe o ID que não foi encontrado
    public ResourceNotFoundException(Object id) {
        super("Recurso não encontrado. Id: " + id);
    }
}