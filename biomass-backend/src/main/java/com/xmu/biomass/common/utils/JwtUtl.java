package com.xmu.biomass.common.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

/**
 * @ Author：ythu
 * @ Date：2025/3/26  下午3:32
 */
@ConfigurationProperties(prefix = "jwt")
@Component
@Data
public class JwtUtl {
    private String secret;
    private long expire;

    public String generateToken(String subject) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expire * 1000L);
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder().subject(subject).issuedAt(now).expiration(exp).signWith(key).compact();
    }

    public String parseToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }
}
