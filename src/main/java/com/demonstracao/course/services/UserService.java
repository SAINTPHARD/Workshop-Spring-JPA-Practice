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
	
	// 3. Implementação da Inserção de User
		public User insert(User obj) {
		return repository.save(obj);
	}
		
	// 4. Implementação do Delete
		public void delete(Long id) {
			repository.deleteById(id);
		}

	// 5. Implementação do Update
		public User update(Long id, User user) {

	        User existingUser = findById(id); 
	        updateData(existingUser, user);
	        return repository.save(existingUser);
	    }

	    /**
	     * Método auxiliar privado para isolar a lógica de atualização.
	     * Atualiza a entidade 'existingUser' (monitorada pelo JPA)
	     * com os dados do objeto 'user' (vindo da requisição).
	     */
	    private void updateData(User existingUser, User user) {
	        existingUser.setName(user.getName());
	        existingUser.setEmail(user.getEmail());
	        existingUser.setPhone(user.getPhone());
	        //existingUser.setPassword(user.getPassword());
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