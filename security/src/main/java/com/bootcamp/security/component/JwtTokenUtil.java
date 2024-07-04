package com.bootcamp.security.component;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import java.util.ArrayList;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    // generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
        log.info("roles={}", roles);
        if (roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            log.info("put isAdmin");
            claims.put("isAdmin", true);
        }
        if (roles.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            log.info("put isUser");
            claims.put("isUser", true);
    }
        if (roles.contains(new SimpleGrantedAuthority("ROLE_MODERATOR"))) {
            log.info("put isModerator");
            claims.put("isModerator", true);
        }
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
            .claims(claims)
            .subject(subject)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
            .signWith(getSignInKey())
            .compact();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().verifyWith(getSignInKey()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException | SignatureException ex) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
        } catch (ExpiredJwtException ex) {
            throw ex;
        }
    }

    public String getUsernameFromToken(String token) {
        Jws<Claims> claims = Jwts.parser().verifyWith(getSignInKey()).build()
                                    .parseSignedClaims(token);
        return claims.getPayload().getSubject();
    }

    public ArrayList<SimpleGrantedAuthority> getRolesFromToken(String token) {
        Jws<Claims> claims = Jwts.parser().verifyWith(getSignInKey()).build()
                                .parseSignedClaims(token);

        ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();

        Boolean isAdmin = claims.getPayload().get("isAdmin", Boolean.class);
        Boolean isUser = claims.getPayload().get("isUser", Boolean.class);
        Boolean isModerator = claims.getPayload().get("isModerator", Boolean.class);
        log.info("claims: {},{},{}",isAdmin, isUser, isModerator);

        if (isAdmin != null && isAdmin)
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        if (isUser != null && isUser)
            roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (isModerator != null && isModerator)
            roles.add(new SimpleGrantedAuthority("ROLE_MODERATOR"));

        return roles;
    }

    private SecretKey getSignInKey() {
        byte[] bytes = Base64.getDecoder().decode(this.jwtSecret.getBytes(StandardCharsets.UTF_8));
        return Keys.hmacShaKeyFor(bytes); }
}
