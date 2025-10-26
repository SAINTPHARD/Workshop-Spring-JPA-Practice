package com.demonstracao.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demonstracao.course.entities.User;
import com.demonstracao.course.repositories.UserRepository;

@Service 
public class UserService {
// implementar 2 operações básicas: findAll e findById
	
	@Autowired
	private UserRepository repository;
	
	// 1. Método para retornar todos os usuários (findAll)
	public List<User> findAll() {
		return repository.findAll();
	}
	
	
	// 2. o método findById retorna um objeto Optional
		public User findById(Long id) {
			Optional<User> obj = repository.findById(id);
			return obj.get();
		}
}

/**
// @Service // registrar a camada de serviço como um componente do Spring
* 1. Método para retornar todos os usuários (findAll)
* public List<User> findAll() {
		return repository.findAll();
	}
*	
* 2. Método para retornar um usuário por id (findById)
* public class UserService {
	public User findById(Long id) {
		return repository.findById(id).get();
 */