package com.demonstracao.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demonstracao.course.entities.Order;
import com.demonstracao.course.repositories.OrderRepository;

@Service 
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public List<Order> findAll() {
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get(); // <-- Ver nota sobre 'get()' na documentação
	}
	
}

// ========================================================================
// 
//                 ANOTAÇÕES E DOCUMENTAÇÃO
// 
// ========================================================================

/*
 * ----- Comentários Prováveis -----
 *
 * @Service // registrar a camada de serviço como um componente do Spring
 *
 * // implementar 2 operações básicas: findAll e findById
 *
 * // 1. Método para retornar todos os pedidos (findAll)
 *
 * // 2. Método para retornar um pedido por id (findById)
 *
 * // o método findById retorna um objeto Optional
 */
 
/*
 * ----- Notas sobre a Implementação -----
 *
 * 1. (Recomendação de Melhoria) Risco no 'obj.get()':
 * - O método 'obj.get()' como está, funciona. No entanto, ele irá
 * causar um erro (uma 'Exception') na sua aplicação se você
 * tentar buscar um ID de pedido que não existe no banco.
 * - MELHOR PRÁTICA: No futuro, é recomendado substituir 'obj.get()'
 * por 'obj.orElseThrow()', que permite lançar uma exceção 
 * personalizada (como 'ResourceNotFoundException') e tratá-la
 * na camada de Recurso (Resource/Controller).
 */