package com.jsf.backend.util;
import com.jsf.backend.model.User;
import com.jsf.backend.model.Admin;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secretKeyString;

    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 1000 * 60 * 1;  // 1 minutes
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60  * 10;  // 1 week

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKeyString.getBytes());
    }

    // User-specific tokens
    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", "user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String extractEmailId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenInvalidOrExpired(String emailFromRequest, String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token);

            Claims claims = claimsJws.getBody();
            if (claims.getExpiration().before(new Date())) {
                return true;
            }
            String emailFromToken = claims.getSubject();
            return !emailFromToken.equals(emailFromRequest);
        } catch (JwtException e) {
            return true;
        }
    }

    public List<GrantedAuthority> extractAuthorities(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String role = claims.get("role", String.class);
        return Collections.singletonList(() -> role);
    }
}
