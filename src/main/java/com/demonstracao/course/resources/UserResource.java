package com.demonstracao.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demonstracao.course.entities.User;
import com.demonstracao.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	// Implementação dos endpoints RESTful para o recurso User
	// ==================================================
	// 1.GET - Buscar todos os usuários
	// ==================================================
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	// ==================================================
	// 2.GET - EndPoint para buscar um usuário por id
	// ==================================================
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User u = service.findById(id);
		return ResponseEntity.ok().body(u);
	}
	
	// ==================================================
	// 3. POST - Insert (Inserir)
	// ==================================================
	@PostMapping
    public ResponseEntity<User> insert(@RequestBody User user) {
        // 3.1. Salva o usuário no banco
        user = service.insert(user);
        
        // 3.2. Cria a URI (URL) para o novo recurso criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        // 2.3. Retorna a resposta "201 Created" com a URI no cabeçalho
        //    e o usuário salvo no corpo
        return ResponseEntity.created(uri).body(user);
    }
	
	// ==================================================
	// 4. DELETE - Deletar
	// ==================================================
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	// ==================================================
	// 5. PUT - Update (Atualizar)
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
		user = service.update(id, user);
		return ResponseEntity.ok().body(user);
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

//Implementação do método insert (inserir um novo usuário)
//	public ResponseEntity<User> insert(@RequestBody User obj) { é o método que insere um novo usuário
//		User newUser = service.insert(obj); // Chamar o serviço para inserir o novo usuário
//@RequestBody avisa ao Spring para pegar o JSON
// do corpo da requisição e transformar em um objeto User.
//		return ResponseEntity.ok().body(newUser); // Retornar a resposta HTTP com o novo usuário no corpo
