/**package com.demonstracao.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// Novos imports necessários para remover a senha gerada
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                .anyRequest().permitAll()
            )
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));
        
        return http.build();
    }

    /**
     * ESTE É O MÉTODO MÁGICO.
     * Ao definir um UserDetailsService manualmente, o Spring Boot entende que
     * não precisa gerar a senha aleatória no console.
     */
/**
    @Bean
    public UserDetailsService userDetailsService() {
        // Cria um usuário em memória apenas para satisfazer a configuração.
        // Como usamos .permitAll() lá em cima, ninguém vai precisar logar de verdade.
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user);
    }
}
*/