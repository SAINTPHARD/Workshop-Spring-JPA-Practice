package com.demonstracao.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demonstracao.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
