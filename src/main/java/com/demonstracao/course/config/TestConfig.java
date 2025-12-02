package com.demonstracao.course.config;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.demonstracao.course.entities.Category;
import com.demonstracao.course.entities.Order;
import com.demonstracao.course.entities.OrderItem;
import com.demonstracao.course.entities.Payment;
import com.demonstracao.course.entities.Product;
import com.demonstracao.course.entities.User;
import com.demonstracao.course.entities.enums.OrderStatus;
import com.demonstracao.course.repositories.CategoryRepository;
import com.demonstracao.course.repositories.OrderItemRepository;
import com.demonstracao.course.repositories.OrderRepository;
import com.demonstracao.course.repositories.ProductRepository;
import com.demonstracao.course.repositories.UserRepository;

@Configuration
@Profile({"test", "dev", "docker"}) // Só roda este config nos perfis 'test' ou 'docker'
public class TestConfig implements CommandLineRunner {

    // --- 1. INJEÇÃO DE DEPENDÊNCIAS ---
    // Injeta todos os repositórios necessários para manipular os dados
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository; 

    /**
     * Este é o método principal implementado da interface CommandLineRunner.
     * Ele será executado automaticamente assim que a aplicação iniciar.
     */
    @Override
    public void run(String... args) throws Exception {

        // --- 2. CRIAÇÃO DE CATEGORIAS ---
        // Cria as entidades independentes primeiro (que não dependem de outras)
    	Category departamentoTI = new Category(null, "Tecnologia da Informação");
    	Category departamentoRH = new Category(null, "Recursos Humanos");
    	Category departamentoFinanceiro = new Category(null, "Financeiro");
    	Category departamentoComercial = new Category(null, "Comercial e Vendas");
    	Category departamentoMarketing = new Category(null, "Marketing e Comunicação");
    	Category departamentoProducao = new Category(null, "Produção e Operações");
    	Category departamentoJuridico = new Category(null, "Jurídico");
    	Category departamentoLogistica = new Category(null, "Logística e Suprimentos");
    	Category departamentoAdministrativo = new Category(null, "Administrativo");
    	Category departamentoPesquisa = new Category(null, "Pesquisa e Desenvolvimento");

    	// Salva as categorias no banco de dados
    	categoryRepository.saveAll(List.of(
    	    departamentoTI, departamentoRH, departamentoFinanceiro, departamentoComercial,
    	    departamentoMarketing, departamentoProducao, departamentoJuridico,
    	    departamentoLogistica, departamentoAdministrativo, departamentoPesquisa
    	));
        
        // --- 3. CRIAÇÃO DE PRODUTOS ---
        Product p1 = new Product(null, "The Lord of the Rings", 90.5, "", "Lorem ipsum dolor sit amet, consectetur.");
        Product p2 = new Product(null, "Smart TV", 2190.0, "", "Nulla eu imperdiet purus. Maecenas ante.");
        Product p3 = new Product(null, "Macbook Pro", 1250.0, "", "Nam eleifend maximus tortor, at mollis.");
        Product p4 = new Product(null, "PC Gamer", 1200.0, "", "Donec aliquet odio ac rhoncus cursus.");
        Product p5 = new Product(null, "Rails for Dummies", 100.99, "", "Cras fringilla convallis sem vel faucibus.");
        Product p6 = new Product(null, "Electric Guitar", 750.0, "", "Cras fringilla convallis sem vel faucibus.");

        // Salva os produtos ANTES de associar as categorias
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
        
        // --- 4. ASSOCIAÇÃO PRODUTO <-> CATEGORIA (Many-to-Many) ---
        // Adiciona as categorias aos produtos na memória
        p1.getCategories().add(departamentoRH);
        p2.getCategories().add(departamentoTI);
        p3.getCategories().add(departamentoFinanceiro);
        p4.getCategories().add(departamentoComercial);
        p5.getCategories().add(departamentoMarketing);
        p6.getCategories().add(departamentoProducao);

        // Salva os produtos NOVAMENTE para persistir a associação na tabela de junção
        productRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6));

        // --- 5. CRIAÇÃO DE USUÁRIOS ---
        User u1 = new User(null, "John Doe", "john@gmail.com", "9888888s888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
        User u3 = new User(null, "Bob Brown", "bob@gmail.com", "966666666", "123456");
        User u4 = new User(null, "Robedson Saintphard", "robedson@gmail.com", "955555555", "123456");

        userRepository.saveAll(List.of(u1, u2, u3, u4));
        
        // --- 6. CRIAÇÃO DE PEDIDOS (Orders) ---
        // Cria os pedidos, associando-os aos usuários já salvos
        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
        Order o4 = new Order(null, Instant.parse("2019-08-23T10:15:30Z"), OrderStatus.DELIVERED, u3);
        
        // Salva os pedidos ANTES de criar os OrderItems
        orderRepository.saveAll(List.of(o1, o2, o3, o4));
        
        // --- 7. CRIAÇÃO DOS ITENS DE PEDIDO (OrderItem) ---
        // Entidade de associação (Many-to-Many com atributos extras)
        // Associa os Pedidos (o1-o4) e Produtos (p1-p6)
        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
        OrderItem oi5 = new OrderItem(o4, p2, 1, p2.getPrice());
        OrderItem oi6 = new OrderItem(o4, p4, 1, p4.getPrice());
        OrderItem oi7 = new OrderItem(o4, p6, 1, p6.getPrice());
        
        orderItemRepository.saveAll(List.of(oi1, oi2, oi3, oi4, oi5, oi6, oi7));

        // --- 8. ASSOCIAÇÃO DE PAGAMENTOS (One-to-One) ---
        // Cria os pagamentos e os associa aos pedidos na memória
        Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
        o1.setPayment(pay1);

        Payment pay2 = new Payment(null, Instant.parse("2019-07-21T05:42:10Z"), o2);
        o2.setPayment(pay2);
        
        Payment pay3 = new Payment(null, Instant.parse("2019-07-23T10:00:00Z"), o3);
        o3.setPayment(pay3);

        Payment pay4 = new Payment(null, Instant.parse("2019-08-23T11:15:30Z"), o4);
        o4.setPayment(pay4);

		// Salva os pedidos novamente para persistir os pagamentos.
        // a relação com Payment (CascadeType.ALL), salvar o Order
        // persistirá também a entidade Payment associada.
        orderRepository.saveAll(List.of(o1, o2, o3, o4));
    }
}