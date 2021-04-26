package com.moviesandchill.portalbackendservice.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Arrays;
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
        return Keys.hmacShaKeyFor(keyBytes);
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
        System.out.println(req);
        System.out.println(req.getHeaderNames());
        var cookies = req.getCookies();
        System.out.println(Arrays.toString(cookies));

        if (cookies == null) {
            return Optional.empty();
        }

        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(cookieName))
                .findFirst()
                .map(Cookie::getValue);
    }

    public void setTokenToResponse(HttpServletResponse res, String token) {
        Cookie cookie = new Cookie(cookieName, token);
        cookie.setMaxAge(validityInMilliseconds);
        cookie.setHttpOnly(false);
        cookie.setSecure(false);
        cookie.setPath("/");
        res.addCookie(cookie);
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
