package com.demonstracao.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demonstracao.course.entities.Category;
import com.demonstracao.course.repositories.CategoryRepository;

@Service
public class CategoryService {
// implementar 2 operações básicas: findAll e findById

	@Autowired
	private CategoryRepository repository;

	// 1. Método para retornar todos os usuários (findAll)
	public List<Category> findAll() {
		return repository.findAll();
	}

	// 2. o método findById retorna um objeto Optional
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}
}