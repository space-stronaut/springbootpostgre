package com.authenticate.belajar.jwt;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerate {
    public String generateToken(String id) {
        return Jwts.builder()
            .setSubject(id)
            .signWith(SignatureAlgorithm.HS256, "yourSecretKey")
            .compact();
    }

    public String parserToken(String token){
        String secretKey = "yourSecretKey";
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
