package org.example.authproject.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
    // Replace this with a secure key and configuration  (e.g., environment variable, configuration file)
    private final String SECRET_STRING = "securesecretkeymustbeatleast32characterslongaa";
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes());
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;



    public String generateToken(String username) {
        
        JwtBuilder builder = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, Jwts.SIG.HS256);
        return builder.compact();
    }

 

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean validateToken(String token,UserDetails userDetails,String email) {
        return (userDetails.getUsername().equals(email) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date()); 
    }
}
