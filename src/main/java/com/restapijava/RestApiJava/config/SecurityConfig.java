package com.restapijava.RestApiJava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.restapijava.RestApiJava.middleware.JavaAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JavaAuthenticationFilter javaAuthenticationFilter;

    public SecurityConfig(JavaAuthenticationFilter javaAuthenticationFilter) {
        this.javaAuthenticationFilter = javaAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login","/register").permitAll() // Allow login endpoint without authentication
                .requestMatchers("/employees").authenticated() // Protect addEmployee endpoint
                .anyRequest().permitAll()  // Allow other requests without authentication
            )
            .addFilterBefore(javaAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
