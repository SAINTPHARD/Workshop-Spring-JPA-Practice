package com.demonstracao.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demonstracao.course.entities.User;

@RestController            			// Anotação para definir que a classe é um recurso REST
@RequestMapping(value = "/users")  // Definir o caminho do recurso (value é o endpoint e "/users" é o caminho)
public class UserResource {

	@GetMapping              		// método que responde a requisições GET
	// Implementar os métodos para acessar o recurso (CRUD - Create, Read, Update, Delete)
	public ResponseEntity<User> findAll() {
		// Simular um usuário para teste
		// User u = new User(Long.valueOf(1), "name", "email", "phone", "password");
		User u = new User(1L, "Maria", "mariasilva@gmail.com", "99998888", "123456");

		return ResponseEntity.ok().body(u);
	}
}

// responseEntity é um tipo especial do Spring que encapsula várias informações de uma resposta HTTP para um serviço RESTful, incluindo o corpo da resposta, os cabeçalhos HTTP e o status HTTP.
// responseEntities.ok() é um método estático da classe ResponseEntity que cria uma instância de ResponseEntity com o status HTTP 200 OK. Isso indica que a solicitação foi bem-sucedida e que o recurso solicitado foi encontrado e retornado com sucesso.
// .body(u) é um método que define o corpo da resposta HTTP. Nesse caso, o corpo