// package com.restapijava.RestApiJava.util;

// import io.jsonwebtoken.JwtException;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;

// import java.security.Key;
// import java.util.Date;

// public class JwtUtil {

//     private final String SECRET_KEY = "testtinghjkriolertjikekenrketieoooo";
//     private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//     // private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
//     private final long expirationTime = 1000 * 60 * 60; // 1 hour

//     public String generateToken(String email) {
//         return io.jsonwebtoken.Jwts.builder()
//                 .setSubject(email)
//                 .setIssuedAt(new Date(System.currentTimeMillis()))
//                 .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
//                 // .signWith(key)
//                 .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
//                 .compact();
//     }

//     public String extractEmail(String token) {
//         return io.jsonwebtoken.Jwts.parserBuilder()
//                 .setSigningKey(key)
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody()
//                 .getSubject();
//     }

//     public boolean validateToken(String token) {
//         try {
//             io.jsonwebtoken.Jwts.parserBuilder()
//                     .setSigningKey(key)
//                     .build()
//                     .parseClaimsJws(token);
//             return true;
//         } catch (JwtException e) {
//             return false;
//         }
//     }
// }




package com.restapijava.RestApiJava.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private final String SECRET_KEY = "testtinghjkriolertjikekenrketieoooo"; // Your secret key
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());  // Ensure this matches for both signing and validation
    private final long expirationTime = 1000 * 60 * 60; // 1 hour

    // Generate JWT token
    public String generateToken(String email) {
        return io.jsonwebtoken.Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS256) // Sign with the same key
                .compact();
    }

    // Extract email from JWT token
    public String extractEmail(String token) {
        return io.jsonwebtoken.Jwts.parserBuilder()
                .setSigningKey(key) // Use the same key for validation
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validate the JWT token
    public boolean validateToken(String token) {
        try {
            io.jsonwebtoken.Jwts.parserBuilder()
                    .setSigningKey(key) // Use the same key for validation
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
