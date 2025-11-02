package com.demonstracao.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.demonstracao.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	// 1º CAMPO (será a 1ª coluna)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// 2º CAMPO (será a 2ª coluna)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	// 3º CAMPO (será a 3ª coluna)
	// @Enumerated(EnumType.STRING) (REMOVIDO PARA SALVAR COMO INTEIRO)
	private Integer status;
	// private OrderStatus status;
	
	// 4º CAMPO (será a 4ª coluna)
	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;
	
	// 5º CAMPO (Associação com os itens do pedido)
	@OneToMany(mappedBy = "id.order") 
	private Set<OrderItem> items = new HashSet<>(); // Associação com OrderItem (1 para muitos)
	
	// ========================================================================
	//  🟢 INÍCIO DA IMPLEMENTAÇÃO (Order <-> Payment)
	// ========================================================================
	
	// 6º CAMPO (Associação com Payment - 1 para 1)
	@OneToOne(mappedBy = "order", cascade = jakarta.persistence.CascadeType.ALL)
	private Payment payment; 
	
	public Order() {
	}
	
	// O construtor foi atualizado para refletir a mudança do campo 'status'
	public Order(Long id, Instant moment, OrderStatus status, User client) {
		this.id = id;
		this.moment = moment;
		setStatus(status); // Usa o setter para garantir a conversão correta
		this.client = client;
	}

	// ... (getters e setters de id, moment, client) ...
	// Getter e Setter para Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// Getter e Setter para Moment
	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	// Getter customizado para Status
	public OrderStatus getStatus() {
		// Lógica para converter o Integer de volta para o Enum
		if (status == null) {
			return null;
		}
		return OrderStatus.valueOf(status);
	}
	
	// Setter customizado para Status
	public void setStatus(OrderStatus status) {
		// Lógica para converter o Enum para Integer
		if (status == null) {
			this.status = null;
		} else {
			this.status = status.getCode(); // Pega o código (ex: 1, 2) do Enum
		}
	}

	// Getter e Setter para Client
	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	// Getter e Setter para Payment
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
	// Getter para os Itens do Pedido
	public Set<OrderItem> getItems() {
		return items;
	}
	
	// ... (hashCode e equals) ...
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
}
// ========================================================================
// 
//                 ANÁLISE E DOCUMENTAÇÃO
// 
// ========================================================================

/*
 * ----- Análise do hashCode/equals -----
 *
 * A sua implementação do 'hashCode' e 'equals' baseada APENAS no 'id' está
 * PERFEITA. Esta é a forma correta de fazer para Entidades JPA, pois garante
 * que o Hibernate possa gerenciar os objetos corretamente mesmo que outros
 * campos (como 'status') mudem.
 */

/*
 * ----- Análise das Anotações JPA (Adicionadas) -----
 *
 * O código original não tinha anotações. Elas são necessárias para o Spring
 * Data JPA saber que esta classe deve virar uma tabela.
 *
 * @Entity // Diz ao JPA que esta classe é uma entidade (uma tabela).
 *
 * @Table(name = "tb_order") // Define o nome exato da tabela no banco de dados.
 *
 * @Id // Define que o campo 'id' é a Chave Primária.
 *
 * @GeneratedValue(strategy = GenerationType.IDENTITY) // Diz ao banco de dados
 * para autoincrementar o 'id' (Ex: 1, 2, 3...).
 * 
 * @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =
 * "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT") // Formata o campo 'moment'
 * para JSON no formato ISO 8601 (padrão web).
 *
 * @ManyToOne // Define o relacionamento: Muitos Pedidos (Orders) para Um
 * Usuário (client).
 *
 * @JoinColumn(name = "client_id") // Define o nome da coluna de chave
 * estrangeira (FK) na tabela 'tb_order' // que irá armazenar o ID do User
 * (client).
 *
 * implements Serializable // É uma boa prática para entidades, pois permite que
 * elas sejam // "serializadas" (convertidas em bytes), o que é útil em várias
 * // situações, como armazenamento em cache ou tráfego de rede.
 */

/*
 * ----- Análise dos Atributos (Correções) -----
 *
 * 1. 'moment' (Momento do pedido): - FOI MUDADO de 'String' para
 * 'java.time.Instant'. - POR QUÊ? Usar String para datas é uma má prática.
 * 'Instant' é o tipo moderno do Java para salvar um ponto exato no tempo (em
 * UTC), o que permite fazer cálculos (ex: pedidos das últimas 24h) e garante
 * que não haverá problemas de fuso horário.
 *
 * 2. 'status' (Status do pedido): - Está como 'String', o que funciona. -
 * 
 */
/*
 * =====================================================================
 * 🟢 INÍCIO DA ASSOCIAÇÃO (Order -> Payment)
 * =====================================================================
 *
 * 💡 @OneToOne: Define a relação de Um-para-Um.
 *
 * 💡 mappedBy = "order": Diz ao JPA que esta é a ponta "inversa" da relação.
 * A relação já foi "mapeada por" (controlada) pelo campo 'order'
 * lá na classe Payment.
 *
 * 💡 cascade = CascadeType.ALL:
 * Isso é MUITO importante. Diz ao JPA: "Se eu salvar um Order,
 * salve o Payment junto. Se eu deletar um Order, delete o Payment junto."
 * O ciclo de vida do Payment depende 100% do Order.
 * 
 * =================================================================
 * @OneToOne(mappedBy = "order", cascade = jakarta.persistence.CascadeType.ALL)
 * OnToOne é uma anotação JPA que define um relacionamento de um-para-um entre duas entidades.
 * cascade = CascadeType.ALL indica que todas as operações (persistência, remoção, atualização, etc.) realizadas na entidade "Order" devem ser propagadas para a entidade "Payment" associada.
 */