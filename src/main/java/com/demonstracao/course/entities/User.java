package com.demonstracao.course.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity						// Anotação para definir que a classe é uma entidade JPA (Java Persistence API)
@Table(name = "tb_user")	// Nome da tabela no banco de dados
public class User implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// 1. Implementar os atributos basicos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Anotação para definir que o id é gerado automaticamente pelo banco de dados
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	// 2. Implementar os construtores (vazio porque é framework e com todos os atributos)
	public User() {
	}
	
	// Construtores que recebem os atributos objetos
	public User(Long id, String name, String email, String phone, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	// 3. Implementar os getters e setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// 4. Implementar os metodos hashCode e equals (somente o id)
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
}
