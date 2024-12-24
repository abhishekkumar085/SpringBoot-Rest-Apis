package com.restapijava.RestApiJava.middleware;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.restapijava.RestApiJava.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JavaAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JavaAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException{
        String uri = request.getRequestURI();

        if (uri.contains("/login") || uri.contains("/register")) {
            filterChain.doFilter(request, response); // Proceed to the next filter
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Unauthorized: Missing or invalid token");
            return;
        }
        String token = authHeader.substring(7);
        try {
            String email = jwtUtil.extractEmail(token);
            if (!jwtUtil.validateToken(token)) {
                throw new Exception("Invalid token");
            }

            // Add user email to request attribute (optional)
            request.setAttribute("email", email);

        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Unauthorized: " + e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);

    }

    
}