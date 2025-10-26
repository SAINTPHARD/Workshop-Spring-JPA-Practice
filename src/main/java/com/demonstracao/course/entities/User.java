package com.demonstracao.course.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore; // <-- IMPORTAÇÃO ADICIONADA

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	@JsonIgnore // <-- ANOTAÇÃO ADICIONADA PARA EVITAR LOOP INFINITO
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();
	
	public User() {
	}
	
	public User(Long id, String name, String email, String phone, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

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

	public List<Order> getOrders() {
		return orders;
	}

	// O método setOrders() foi removido (Ver anotações abaixo)
	
}

// ========================================================================
// 
//                 ANOTAÇÕES E DOCUMENTAÇÃO
// 
// ========================================================================

/*
 * ----- Os comentários originais (movidos para cá) -----
 *
 * @Entity					// Anotação para definir que a classe é uma entidade JPA (Java Persistence API)
 * @Table(name = "tb_user")	// Nome da tabela no banco de dados
 *
 * // 1. Implementar os atributos basicos
 * @GeneratedValue(strategy = GenerationType.IDENTITY) // Anotação para definir que o id é gerado automaticamente pelo banco de dados
 *
 * // Associação com a classe Order (um para muitos)
 *
 * // 2. Implementar os construtores (vazio porque é framework e com todos os atributos)
 * // Construtores que recebem os atributos objetos
 *
 * // 3. Implementar os getters e setters
 *
 * // 4. Implementar os metodos hashCode e equals (somente o id)
 *
 * // 5. Implementar o getter da associação com a classe Order
 *
 * // private List<Order> orders = new ArrayList<>(); // Associação com a classe Order (um para muitos)
 */

/*
 * ----- Análise e Aprimoramentos (Por Gemini) -----
 *
 * 1. (Crítico) Adição do @JsonIgnore:
 * - POR QUÊ? Sem isso, ao testar sua API (GET /users/1), o Spring tentaria
 * converter o User para JSON. Ele encontraria a lista 'orders'.
 * Ao converter cada 'Order', ele encontraria o 'client' (que é um User).
 * Ao converter esse 'User', ele encontraria a lista 'orders'...
 * Isso cria um LOOP INFINITO (StackOverflowError) e sua aplicação quebra.
 * - SOLUÇÃO: @JsonIgnore diz ao conversor JSON: "Ignore este campo 'orders'
 * ao criar o JSON".
 *
 * 2. (Boa Prática) Remoção do `setOrders(List<Order> orders)`:
 * - POR QUÊ? A lista 'orders' é gerenciada pelo JPA. Não é uma boa prática
 * permitir que alguém de fora "troque" a lista inteira por uma nova.
 * - SOLUÇÃO: Mantenha apenas o `getOrders()`. Se você precisar
 * adicionar um pedido a um usuário, você fará:
 * `user.getOrders().add(newOrder);`
 *
 * 3. (Validação) O resto do seu código está PERFEITO:
 * - `equals/hashCode` usando só o 'id' é o correto.
 * - Inicializar a lista (`= new ArrayList<>()`) é excelente para evitar
 * NullPointerException.
 * - As anotações `@OneToMany(mappedBy = "client")` estão corretas.
 */