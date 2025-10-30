package com.demonstracao.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "tb_product")

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// JPA Annotations(Mapeamento relacional JPA)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Atributos basicos da entidade Product
	private Long id;
	private String name;
	private Double price;
	private String imgUrl;
	private String description;
	
	@Transient // Evita serialização para prevenir problemas de referência circular
	
	// Associação com Category (Muitos para Muitos) com Set para evitar duplicatas
	private Set<Category> categories = new HashSet<>();
	
	// Construtor sem args
	public Product() {
	}

	// Construtor com args
	public Product(Long id, String name, Double price, String imgUrl, String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.imgUrl = imgUrl;
		this.description = description;
	}

	// Getters e Setters
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	// hashCode e equals baseados em id
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

}
