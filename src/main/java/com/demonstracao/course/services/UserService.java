package com.demonstracao.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.demonstracao.course.entities.User;
import com.demonstracao.course.repositories.UserRepository;
// Imports necessários para as exceções customizadas
import com.demonstracao.course.services.exceptions.DatabaseException;
import com.demonstracao.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException; // Import para o 'update'

@Service 
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	// 1. Método para retornar todos os usuários (findAll)
	public List<User> findAll() {
		return repository.findAll();
	}
	
	
	// 2. o método findById
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		// Se o objeto estiver presente, retorna-o; caso contrário, lança a exceção personalizada
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	// 3. Implementação da Inserção de User
	public User insert(User obj) {
		return repository.save(obj);
	}
		
	// 4. Implementação do Delete com tratamento de exceções
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} 
        catch (EmptyResultDataAccessException e) {
            // Captura o erro se o ID não for encontrado
			throw new ResourceNotFoundException(id);
		} 
        catch (DataIntegrityViolationException e) {
            // Captura o erro se o usuário tiver pedidos associados (integridade)
			throw new DatabaseException(e.getMessage());
		}
	}

	// 5. Implementação do Update
	public User update(Long id, User user) {
		try {
	        User existingUser = repository.getReferenceById(id); 
	        updateData(existingUser, user);  
	        return repository.save(existingUser);
		} 
        catch (EntityNotFoundException e) {
            // Captura o erro se o ID não for encontrado ao tentar salvar
            throw new ResourceNotFoundException(id);
        }
	}

	/**
	 * Método auxiliar privado para isolar a lógica de atualização.
	 */
	private void updateData(User existingUser, User user) {
		existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPhone(user.getPhone());
		// existingUser.setPassword(user.getPassword()); // (Mantive comentado como no seu original)
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