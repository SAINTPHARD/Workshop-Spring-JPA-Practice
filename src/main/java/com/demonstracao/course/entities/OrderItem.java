package com.demonstracao.course.entities;

import java.util.Objects;

import com.demonstracao.course.entities.PK.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

// mapeamento da entidade OrderItem
@Entity // indica que a classe é uma entidade JPA
@Table(name = "tb_order_item") // nome da tabela no banco de dados
public class OrderItem {

	// private OrderItemPK id = new OrderItemPK();
	@EmbeddedId // indica que é uma chave composta
	private OrderItemPK id = new OrderItemPK();
	
	private Integer quantity;
	private Double price;
	
	// Constructor sem argumentos
	public OrderItem() {
		this.id = new OrderItemPK();
	}

	// Constructor com argumentos
	// insere order e product na chave composta e instancia os atributos
	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		super();
		this.id = new OrderItemPK();
		id.setOrder(order); // insere order na chave composta
		id.setProduct(product); // insere product na chave composta
		this.quantity = quantity;
		this.price = price;
	}

	// Getters and Setters 
	// Getter para Order
	@JsonIgnore // para evitar referência cíclica na serialização JSON
	public Order getOrder() {
		return id.getOrder();
	}
	// Setter para Order
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	// ==================================================
	// Getter para Product
	@JsonIgnore
	public Product getProduct() {
		return id.getProduct();
	}
	// Setter para Product
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	// ==================================================
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	// Método para calcular o subtotal do item do pedido
	public Double getSubTotal() {
		return price * quantity;
	}

	// hashCode and equals para comparar objetos
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
	
}
