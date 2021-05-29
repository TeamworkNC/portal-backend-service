package com.moviesandchill.portalbackendservice.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.token.cookie_name}")
    private String cookieName;

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private int validityInMilliseconds;

    @PostConstruct
    private void postConstruct() {
        log.info("postConstruct");
        log.info(cookieName);
        log.info(secret);
        log.info(String.valueOf(validityInMilliseconds));
    }

    public String createToken(long userId) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        Key key = getSigningKey();

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public Authentication getAuthentication(String token) {
        long userId = getUserIdFormToken(token);
        return new SimpleAuthentication(userId);
    }

    public long getUserIdFormToken(String token) {
        var parser = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build();

        String subject = parser.parseClaimsJws(token)
                .getBody()
                .getSubject();

        return Long.parseLong(subject);
    }

    public Optional<String> getTokenFromRequest(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return Optional.of(bearerToken.substring(7));
        }
        return Optional.empty();
    }

    public boolean validateToken(String token) {
        try {
            var parser = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build();

            var claims = parser.parseClaimsJws(token);
            return claims != null && !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
