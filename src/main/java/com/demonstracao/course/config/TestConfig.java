package com.demonstracao.course.config;

import java.time.Instant; // <-- AJUSTE: Import necessário para Order
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.demonstracao.course.entities.Category;
import com.demonstracao.course.entities.Order; // <-- AJUSTE: Import necessário
import com.demonstracao.course.entities.OrderItem;
import com.demonstracao.course.entities.Product;
import com.demonstracao.course.entities.User;
import com.demonstracao.course.entities.enums.OrderStatus; // <-- AJUSTE: Import necessário
import com.demonstracao.course.repositories.CategoryRepository;
import com.demonstracao.course.repositories.OrderItemRepository; // <-- AJUSTE: Importado o repositório correto
import com.demonstracao.course.repositories.OrderRepository;
import com.demonstracao.course.repositories.ProductRepository;
import com.demonstracao.course.repositories.UserRepository;

/**
 * Classe de configuração para popular o banco de dados H2
 * quando o perfil 'test' estiver ativo.
 */
@Configuration
@Profile({"test", "docker"}) // ativa tanto no perfil test quanto docker
public class TestConfig implements CommandLineRunner {

    @Autowired // Injetar a dependência do UserRepository
    private UserRepository userRepository; // Repositório para a entidade User

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository; 

    @Override
    public void run(String... args) throws Exception {

        // 1. ======CRIAR CATEGORIAS ======
    	// ====== DEPARTAMENTOS ======
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

    	categoryRepository.saveAll(List.of(
    	    departamentoTI, departamentoRH, departamentoFinanceiro, departamentoComercial,
    	    departamentoMarketing, departamentoProducao, departamentoJuridico,
    	    departamentoLogistica, departamentoAdministrativo, departamentoPesquisa
    	));


        // 2. ====== PRODUTOS ======
        Product p1 = new Product(null, "The Lord of the Rings", 90.5, "", "Lorem ipsum dolor sit amet, consectetur.");
        Product p2 = new Product(null, "Smart TV", 2190.0, "", "Nulla eu imperdiet purus. Maecenas ante.");
        Product p3 = new Product(null, "Macbook Pro", 1250.0, "", "Nam eleifend maximus tortor, at mollis.");
        Product p4 = new Product(null, "PC Gamer", 1200.0, "", "Donec aliquet odio ac rhoncus cursus.");
        Product p5 = new Product(null, "Rails for Dummies", 100.99, "", "Cras fringilla convallis sem vel faucibus.");
        Product p6 = new Product(null, "Electric Guitar", 750.0, "", "Cras fringilla convallis sem vel faucibus.");

        // Salvar os produtos no banco de dados
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
        
        // 3. ====== ASSOCIAR PRODUTOS ÀS CATEGORIAS ======
        p1.getCategories().add(departamentoRH);
        p2.getCategories().add(departamentoTI);
        p3.getCategories().add(departamentoFinanceiro);
        p4.getCategories().add(departamentoComercial);
        p5.getCategories().add(departamentoMarketing);
        p6.getCategories().add(departamentoProducao);

        // Salva as associações
        productRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6));


        // ====== USUÁRIOS ======
        User u1 = new User(null, "John Doe", "john@gmail.com", "9888888s888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
        User u3 = new User(null, "Bob Brown", "bob@gmail.com", "966666666", "123456");
        User u4 = new User(null, "Robedson Saintphard", "robedson@gmail.com", "955555555", "123456");

        // Salvar os usuários no banco de dados
        userRepository.saveAll(List.of(u1, u2, u3, u4));

        
        // ====== PEDIDOS ======
        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
        Order o4 = new Order(null, Instant.parse("2019-08-23T10:15:30Z"), OrderStatus.DELIVERED, u3);
        
        // Salvar os pedidos no banco de dados
        orderRepository.saveAll(List.of(o1, o2, o3, o4));
        
        
        // 4. ======CRIAR ITENS DO PEDIDO (CLasse OrderItem) ======
        // Associei os itens aos pedidos o1, o2 e o3.
        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
        OrderItem oi5 = new OrderItem(o4, p2, 1, p2.getPrice());
        OrderItem oi6 = new OrderItem(o4, p4, 1, p4.getPrice());
        OrderItem oi7 = new OrderItem(o4, p6, 1, p6.getPrice());
        
        // Salvar os itens do pedido no banco de dados
        orderItemRepository.saveAll(List.of(oi1, oi2, oi3, oi4, oi5, oi6, oi7));
        
        // orderRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
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
 * - Foi necessário adicionar 'import com.demonstracao.course.repositories.OrderRepository;'
 * e a injeção '@Autowired private OrderRepository orderRepository;'
 * no topo da classe.
 *
 * 2. (ATUALIZAÇÃO) Status do Pedido (String para Enum):
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