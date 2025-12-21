package com.demonstracao.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demonstracao.course.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
