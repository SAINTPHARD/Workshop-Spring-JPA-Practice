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
@Profile({"test", "docker"}) 
public class TestConfig implements CommandLineRunner {

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

    @Override
    public void run(String... args) throws Exception {

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

        Product p1 = new Product(null, "The Lord of the Rings", 90.5, "", "Lorem ipsum dolor sit amet, consectetur.");
        Product p2 = new Product(null, "Smart TV", 2190.0, "", "Nulla eu imperdiet purus. Maecenas ante.");
        Product p3 = new Product(null, "Macbook Pro", 1250.0, "", "Nam eleifend maximus tortor, at mollis.");
        Product p4 = new Product(null, "PC Gamer", 1200.0, "", "Donec aliquet odio ac rhoncus cursus.");
        Product p5 = new Product(null, "Rails for Dummies", 100.99, "", "Cras fringilla convallis sem vel faucibus.");
        Product p6 = new Product(null, "Electric Guitar", 750.0, "", "Cras fringilla convallis sem vel faucibus.");

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
        
        p1.getCategories().add(departamentoRH);
        p2.getCategories().add(departamentoTI);
        p3.getCategories().add(departamentoFinanceiro);
        p4.getCategories().add(departamentoComercial);
        p5.getCategories().add(departamentoMarketing);
        p6.getCategories().add(departamentoProducao);

        productRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6));

        User u1 = new User(null, "John Doe", "john@gmail.com", "9888888s888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
        User u3 = new User(null, "Bob Brown", "bob@gmail.com", "966666666", "123456");
        User u4 = new User(null, "Robedson Saintphard", "robedson@gmail.com", "955555555", "123456");

        userRepository.saveAll(List.of(u1, u2, u3, u4));
        
        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
        Order o4 = new Order(null, Instant.parse("2019-08-23T10:15:30Z"), OrderStatus.DELIVERED, u3);
        
        orderRepository.saveAll(List.of(o1, o2, o3, o4));
        
        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
        OrderItem oi5 = new OrderItem(o4, p2, 1, p2.getPrice());
        OrderItem oi6 = new OrderItem(o4, p4, 1, p4.getPrice());
        OrderItem oi7 = new OrderItem(o4, p6, 1, p6.getPrice());
        
        orderItemRepository.saveAll(List.of(oi1, oi2, oi3, oi4, oi5, oi6, oi7));
        
        Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
        o1.setPayment(pay1);

        Payment pay2 = new Payment(null, Instant.parse("2019-07-21T05:42:10Z"), o2);
        o2.setPayment(pay2);
        
        Payment pay3 = new Payment(null, Instant.parse("2019-07-23T10:00:00Z"), o3);
        o3.setPayment(pay3);

        Payment pay4 = new Payment(null, Instant.parse("2019-08-23T11:15:30Z"), o4);
        o4.setPayment(pay4);

        orderRepository.saveAll(List.of(o1, o2, o3, o4));
    }
}

/*
========================================================================
                 DOCUMENTAÇÃO DA CLASSE TESTCONFIG
========================================================================

Esta classe serve para popular o banco de dados com dados de teste
quando a aplicação é iniciada.

--- ANOTAÇÕES DA CLASSE ---
@Configuration: Indica ao Spring que esta é uma classe de configuração.
@Profile({"test", "docker"}): Ativa esta classe apenas nos perfis 'test' ou 'docker'.
implements CommandLineRunner: Faz com que o método 'run' seja executado na inicialização.

--- INJEÇÃO DE DEPENDÊNCIAS (@Autowired) ---
- userRepository: Repositório para salvar/buscar Usuários.
- orderRepository: Repositório para salvar/buscar Pedidos.
- categoryRepository: Repositório para salvar/buscar Categorias.
- productRepository: Repositório para salvar/buscar Produtos.
- orderItemRepository: Repositório para salvar/buscar Itens de Pedido.

--- MÉTODO run(...) ---
Este método é executado na inicialização da aplicação e segue esta ordem:

1. CRIAÇÃO DE CATEGORIAS
   - Cria 10 objetos Category na memória.
   - categoryRepository.saveAll(...): Salva a lista de categorias no BD.

2. CRIAÇÃO DE PRODUTOS
   - Cria 6 objetos Product na memória.
   - productRepository.saveAll(...): Salva a lista de produtos no BD.

3. ASSOCIAÇÃO PRODUTO-CATEGORIA (Muitos-para-Muitos)
   - p1.getCategories().add(...): Adiciona categorias aos produtos na memória.
   - productRepository.saveAll(...): Salva os produtos novamente para atualizar a tabela de associação.

4. CRIAÇÃO DE USUÁRIOS
   - Cria 4 objetos User na memória.
   - userRepository.saveAll(...): Salva a lista de usuários no BD.

5. CRIAÇÃO DE PEDIDOS (Orders)
   - Cria 4 objetos Order, associando um status e um cliente (User).
   - (NOTA: Os pedidos ainda não estão salvos no BD, só na memória).

6. CRIAÇÃO DE ITENS DE PEDIDO (OrderItems)
   - Cria 7 objetos OrderItem, associando-os aos Pedidos (o1-o4) e Produtos (p1-p6).
   - orderItemRepository.saveAll(...): Salva os itens de pedido no BD.

7. CRIAÇÃO DE PAGAMENTOS (Payments)
   - Cria 4 objetos Payment, associando cada um a um Pedido.
   - o1.setPayment(pay1): Liga o pagamento ao pedido na memória (relação OneToOne).

8. SALVAR PEDIDOS (e Pagamentos)
   - orderRepository.saveAll(List.of(o1, o2, o3, o4)):
   - Salva os 4 pedidos no BD.
   - Devido ao 'CascadeType.ALL' na entidade Order, os Pagamentos (pay1, pay2, pay3, pay4)
     que foram associados no passo 7 são salvos AUTOMATICAMENTE.

*/