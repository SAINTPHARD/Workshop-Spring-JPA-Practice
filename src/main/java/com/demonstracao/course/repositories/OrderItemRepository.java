package com.demonstracao.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demonstracao.course.entities.OrderItem;
import com.demonstracao.course.entities.PK.OrderItemPK;

/**
 * Interface de repositório para a entidade OrderItem.
 * O Spring Data JPA criará a implementação automaticamente.
 */
@Repository // Opcional se já estende JpaRepository, mas é boa prática
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

    // O corpo pode ficar vazio!
    // O JpaRepository já fornece todos os métodos padrão como:
    // save(), saveAll(), findById(), findAll(), delete(), etc.

}