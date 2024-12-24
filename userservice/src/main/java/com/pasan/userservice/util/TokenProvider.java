package com.pasan.userservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenProvider {

    private final String SECRET_KEY = "mEj13xelNhzIP335Eu8KxdaI/FwIkeQF64pM30VcDlDQOUCmua6Vg76V00rqNYkr4mYkCk8udwf7egFQm2R3Xg==";
    private final long TOKEN_VALIDITY = 86400000; // 24 hours

    public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
