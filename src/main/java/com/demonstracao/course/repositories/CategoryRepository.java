package com.demonstracao.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demonstracao.course.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Esta interface fica vazia por enquanto.
    // Ela já herda todos os métodos:
    // save(), saveAll(), findById(), findAll(), delete(), etc.

}