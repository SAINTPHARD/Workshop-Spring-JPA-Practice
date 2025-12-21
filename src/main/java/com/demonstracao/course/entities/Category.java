package com.demonstracao.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name = "tb_category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// atributos, construtores, getters e setters
	// 1. Crie os atributos
	private Long id;
	private String name;
	
	@JsonIgnore // Evita loop infinito na serialização JSON
	@ManyToMany(mappedBy = "categories") // mapeamento inverso da associação muitos para muitos
	private Set<Product> products = new HashSet<>();
	
	
	// 2. Crie os construtores(sem args)
	public Category() {
	}
	
	// 2.1 construtor com args
	public Category(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	// 3. getters e setters
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

	// 4. hashCode e equals (baseados em id e name)
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
}
