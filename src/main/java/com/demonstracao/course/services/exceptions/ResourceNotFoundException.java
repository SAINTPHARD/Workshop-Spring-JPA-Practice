package com.demonstracao.course.services.exceptions;

public class ResourceNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
		public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}

}
