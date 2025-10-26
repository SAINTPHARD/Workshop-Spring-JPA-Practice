package com.demonstracao.course.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.demonstracao.course.entities.User;
import com.demonstracao.course.repositories.UserRepository;

@Configuration 	// Anotação para definir que a classe é uma classe de configuração do Spring
@Profile("test") // Anotação para definir que a classe de configuração é específica para o perfil de teste
public class TestConfig implements CommandLineRunner {

	@Autowired // Anotação para injetar a dependência do UserConfig
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		// Usuaris para popular o banco de dados de teste
		User u1 = new User(null, "John Doe", "john@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		User u3 = new User(null, "Bob Brown", "bob@gmail.com", "966666666", "123456");
		User u4 = new User(null, "Robedson Saintphard", "robedsongmail.com", "955555555", "123456");
		
		// Salvar os usuários no banco de dados
		userRepository.saveAll(List.of(u1, u2, u3, u4)); // Java 9 ou superior
		// Ou ==> userRepository.saveAll(java.util.Arrays.asList(u1, u2, u3));
	}
}
