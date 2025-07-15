package project.social.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import project.social.domain.enums.UserRole;
import project.social.dto.auth.JwtTokenResponse;
import project.social.exceptions.auth.InvalidTokenException;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long ACCESS_TOKEN_EXPIRATION = 10 * 60 * 1000;
    private final long REFRESH_TOKEN_EXPIRATION = 24 * 60 * 60 * 1000;

    public JwtTokenResponse generateTokens(String userId, String username, UserRole role) {
        String accessToken = generateAccessToken(userId, username, role);
        String refreshToken = generateRefreshToken(userId, username, role);
        return new JwtTokenResponse(accessToken, refreshToken);
    }

    public String generateAccessToken(String userId, String username, UserRole role) {
        return Jwts.builder()
                .setSubject(userId)
                .claim("username", username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String userId, String username, UserRole role) {
        return Jwts.builder()
                .setSubject(userId)
                .claim("username", username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUserIdFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            throw new InvalidTokenException("Invalid or expired token.");
        }
    }

    public String extractUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("username", String.class);
        } catch (JwtException e) {
            throw new InvalidTokenException("Invalid or expired token.");
        }
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new InvalidTokenException("Missing or malformed Authorization header.");

        return authHeader.substring(7);
    }

    public String extractRole(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("role", String.class);
        } catch (JwtException e) {
            throw new InvalidTokenException("Invalid or expired token.");
        }
    }
}
