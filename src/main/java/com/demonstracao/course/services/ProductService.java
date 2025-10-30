package com.demonstracao.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demonstracao.course.entities.Product;
import com.demonstracao.course.repositories.ProductRepository;

@Service // Marca a classe como um serviço gerenciado pelo Spring
public class ProductService {

    @Autowired // Injeta o repositório de produtos
    private ProductRepository repository;

    // Retorna todos os produtos do banco
    public List<Product> findAll() {
        return repository.findAll();
    }

    // Busca um produto pelo ID
    public Product findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.orElse(null); // Retorna o produto ou null se não encontrado
    }
}
