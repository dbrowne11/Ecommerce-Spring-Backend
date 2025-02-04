package com.danielbrowne.ecommerce.rest.api.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
//    @Value()
    private String jwtSecret = "4e5339d1e867980d261260069e72d91c1a5d31ddfe6232107bda910cf1925df0";
    private Long jwtExpirationDate = 604800000000L;

    // generate jwt token
    public String generateToken(Authentication authentication) {
        String username =  authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(key())
                .compact();
        return token;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // get username from token
    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new RuntimeException("Invalid JWT Token");
        } catch (ExpiredJwtException ex) {
            throw new RuntimeException("Expired JWT Token");
        } catch (UnsupportedJwtException ex) {
            throw new RuntimeException("Unsupported JWT Token");
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("JWT claims string is null or empty");
        }

    }
}
