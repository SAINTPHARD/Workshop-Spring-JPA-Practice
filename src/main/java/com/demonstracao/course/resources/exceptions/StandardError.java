package com.demonstracao.course.resources.exceptions;

import java.io.Serializable;
import java.time.Instant; // <--- 1. Importe o Instant
import java.util.List;

import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	// 1. Atributos
	// Formataçlão da data para o padrão ISO 8601
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant timestamp; // momento em que o erro ocorreu
	
	private Integer status; // código do status HTTP
	private String error; // mensagem de erro
	private String message; // mensagem detalhada
	private String path; // caminho da requisição que causou o erro
	private String method; // método HTTP da requisição que causou o erro
	private List<FieldError> errors; // lista de erros de validação (se houver)
	
	// 2. Construtor vazio
	public StandardError() {
	}
	
	// 3. Construtor com(argumentos todos os atributos
	public StandardError(Instant timestamp, Integer status, String error, String message, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	// 4. Getters e Setters
	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}