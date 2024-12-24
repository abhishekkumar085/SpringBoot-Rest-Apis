package com.restapijava.RestApiJava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.restapijava.RestApiJava.util.JwtUtil;

@Configuration
public class AppConfig {
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
    
}
