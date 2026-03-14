package com.interview.gatewayserver.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secretKey;
    public String validateToken(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes())

                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
