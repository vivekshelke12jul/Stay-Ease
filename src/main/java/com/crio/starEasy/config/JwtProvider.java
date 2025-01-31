package com.crio.starEasy.config;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static com.crio.starEasy.config.JwtConstants.SECRET_KEY;

public class JwtProvider {

    private static final SecretKey secretKey;

    static {
        secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        System.out.println(secretKey);
    }

    public static String generateToken(Authentication auth) {
        return Jwts.builder()
                .issuer("Vivek")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .claim("email", auth.getName())
                .signWith(secretKey)
                .compact();
    }

    public static String getEmailFromJwtToken(String jwt) {
        jwt = jwt.substring(7);

        JwtParser jwtParser = Jwts.parser()
                .verifyWith(secretKey)
                .build();

        String email = jwtParser
                .parseSignedClaims(jwt)
                .getPayload()
                .get("email", String.class);

        return email;
    }
}
