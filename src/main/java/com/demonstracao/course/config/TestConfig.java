package com.demonstracao.course.config;

import java.time.Instant;
import java.util.List;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.demonstracao.course.entities.Category;
import com.demonstracao.course.entities.Order;
import com.demonstracao.course.entities.User;
// 1. IMPORTAÇÃO DO ENUM
import com.demonstracao.course.entities.enums.OrderStatus;
import com.demonstracao.course.repositories.CategoryRepository;
import com.demonstracao.course.repositories.OrderRepository;
import com.demonstracao.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired // 1. INJEÇÃO DO REPOSITORY
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void run(String... args) throws Exception {
		
		// 1. USUÁRIOS PARA POPULAR O BANCO DE DADOS DE TESTE
		Category c1 = new Category(null, "Electronics");
		Category c2 = new Category(null, "Books");
		Category c3 = new Category(null, "Computers");
		Category c4 = new Category(null, "Clothing");
		
		// Salvar as categorias no banco de dados
		categoryRepository.saveAll(List.of(c1, c2, c3, c4));
		
		User u1 = new User(null, "John Doe", "john@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		User u3 = new User(null, "Bob Brown", "bob@gmail.com", "966666666", "123456");
		User u4 = new User(null, "Robedson Saintphard", "robedson@gmail.com", "955555555", "123456");
		
		userRepository.saveAll(List.of(u1, u2, u3, u4));
		
		// 2. ATUALIZADO DE STRING PARA ENUM
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
		Order o4 = new Order(null, Instant.parse("2019-08-23T10:15:30Z"), OrderStatus.DELIVERED, u3);
		
		orderRepository.saveAll(List.of(o1, o2, o3, o4));
	}
}

// ========================================================================
// 
//                 ANOTAÇÕES E DOCUMENTAÇÃO
// 
// ========================================================================

/*
 * ----- Comentários Originais -----
 *
 * @Configuration 	// Anotação para definir que a classe é uma classe de configuração do Spring
 * @Profile("test") // Anotação para definir que a classe de configuração é específica para o perfil de teste
 *
 * @Autowired // Anotação para injetar a dependência do UserConfig
 *
 * // 1. Usuaris para popular o banco de dados de teste
 *
 * // Salvar os usuários no banco de dados
 * // userRepository.saveAll(List.of(u1, u2, u3, u4)); // Java 9 ou superior
 * // Ou ==> userRepository.saveAll(java.util.Arrays.asList(u1, u2, u3));
 *
 * // 2. CRIAR NOVOS PEDIDOS
 *
 * // Salvar os pedidos no banco de dados
 */
 
/*
 * ----- Notas sobre código Aplicadas -----
 *
 * 1. Injeção do 'OrderRepository':
 * - O código estava tentando usar 'orderRepository.saveAll(...)' sem
 * ter injetado essa dependência.
 * - Foi necessário adicionar 'import com.demonstracao.course.repositories.OrderRepository;'
 * e a injeção '@Autowired private OrderRepository orderRepository;'
 * no topo da classe.
 *
 * 2. (ATUALIZAÇÃO) Status do Pedido (String para Enum):
 * - O código foi atualizado para usar o Enum 'OrderStatus'
 * em vez de Strings "soltas" (como "PAID").
 * - Foi necessário adicionar a importação
 * 'com.demonstracao.course.entities.enums.OrderStatus'.
 * - As instanciações dos pedidos foram alteradas de
 * '"PAID"' para 'OrderStatus.PAID',
 * '"WAITING_PAYMENT"' para 'OrderStatus.WAITING_PAYMENT', etc.
 * - Isso pressupõe que a classe 'Order' também foi
 * atualizada para aceitar 'OrderStatus' em seu construtor.
 *
 * 3. Uso de 'List.of(...)': = é Java 9 ou superior
 * - O código original usava 'Arrays.asList(...)', que é válido.
 * - Foi alterado para 'List.of(...)' para simplificar e melhorar a legibilidade.
 * * * .
 */