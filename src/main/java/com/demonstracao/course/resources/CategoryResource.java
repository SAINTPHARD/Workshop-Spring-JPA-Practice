package com.demonstracao.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demonstracao.course.entities.Category;
import com.demonstracao.course.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category c = service.findById(id);
		return ResponseEntity.ok().body(c);
	}
}

// @RestController // Anotação para definir que a classe é um recurso REST
// @RequestMapping(value = "/users") // Definir o caminho do recurso (value é o endpoint e "/users" é o caminho)

// public class UserResource {
// 	@Autowired // Injeção de dependência do serviço
// 	private UserService service; // Atributo do tipo UserService

// 	@GetMapping // método que responde a requisições GET
// 	public ResponseEntity<List<User>> findAll() { // método que responde a requisições GET
// 		List<User> list = service.findAll(); // Chamar o serviço para buscar todos os usuários
// 		return ResponseEntity.ok().body(list); // Retornar a resposta HTTP com a lista de usuários no corpo
// 	}

// responseEntity é um tipo especial do Spring que encapsula várias informações de uma resposta HTTP para um serviço RESTful, incluindo o corpo da resposta, os cabeçalhos HTTP e o status HTTP.
// responseEntities.ok() é um método estático da classe ResponseEntity que cria uma instância de ResponseEntity com o status HTTP 200 OK. Isso indica que a solicitação foi bem-sucedida e que o recurso solicitado foi encontrado e retornado com sucesso.
// .body(u) é um método que define o corpo da resposta HTTP. Nesse caso, o corpo

// public responseEntity<List<User>> findAll() { // método que responde a requisições GET
/**
 * // User u = new User(Long.valueOf(1), "name", "email", "phone", "password");
 * // User u = new User(1L, "Maria", "mariasilva@gmail.com", "99998888",
 * "123456"); // Apagar a instanciação acima quando for implementar o serviço
 * manualmente return ResponseEntity.ok().body(u);
 */

// IMPLEMENTAÇÃO DO MÉTODO findById
//	@GetMapping(value = "/{id}") // Definir o endpoint para buscar um usuário por id
//	public ResponseEntity<User> findById(/* @PathVariable */ Long id) {
// @PathVariable é uma anotação usada em controladores RESTful para vincular um parâmetro de método a uma variável de caminho na URL da solicitação HTTP.
//		User u = service.findById(id); // Chamar o serviço para buscar o usuário por id
//		return ResponseEntity.ok().body(u); // Retornar a resposta HTTP com o usuário no corpo
//	} 

// List<User> list = service.findAll(); // Chamar o serviço para buscar todos os usuários
// return ResponseEntity.ok().body(list); // Retornar a resposta HTTP com a lista de usuários no corpo
