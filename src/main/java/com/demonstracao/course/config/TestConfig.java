package com.demonstracao.course.config;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.demonstracao.course.entities.Order;
import com.demonstracao.course.entities.User;
import com.demonstracao.course.repositories.OrderRepository;
import com.demonstracao.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "John Doe", "john@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		User u3 = new User(null, "Bob Brown", "bob@gmail.com", "966666666", "123456");
		User u4 = new User(null, "Robedson Saintphard", "robedson@gmail.com", "955555555", "123456");
		
		userRepository.saveAll(List.of(u1, u2, u3, u4));
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), "PAID", u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), "WAITING_PAYMENT", u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), "WAITING_PAYMENT", u1);
		Order o4 = new Order(null, Instant.parse("2019-08-23T10:15:30Z"), "DELIVERED", u3);
		
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
 * 2. Status do Pedido (String vs. Enum):
 * - O código estava passando 'OrderStatus.PAID' (um Enum).
 * - A classe 'Order' espera uma 'String' no construtor.
 * - Os valores foram corrigidos para strings (ex: "PAID").
 *
 * 3. Uso de 'List.of(...)': = é Java 9 ou superior
 * - O código original usava 'Arrays.asList(...)', que é válido.
 * - Foi alterado para 'List.of(...)' para simplificar e melhorar a legibilidade.
 * 
 * 
 * .
 */