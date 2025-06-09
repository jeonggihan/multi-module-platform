package com.adnetwork.api.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import com.adnetwork.api.domain.member.dto.LoginUserDto;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    private final String secretKey = "super-super-secret-jwt-signing-key-123456"; // TODO: application yml
    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    private static final long TOKEN_VALIDITY_MS = 1000 * 60 * 60; // 1시간

    public String createToken(LoginUserDto loginUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("memberId", loginUser.getMemberId());
        claims.put("loginId", loginUser.getLoginId());
        claims.put("roles", loginUser.getRoles());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(loginUser.getLoginId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String createRefreshToken(String loginId) {
        return Jwts.builder()
                .setSubject(loginId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7)) // 7일
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
