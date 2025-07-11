package project.social.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import project.social.dto.auth.JwtTokenResponse;
import project.social.exceptions.auth.InvalidTokenException;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long ACCESS_TOKEN_EXPIRATION = 10 * 60 * 1000;
    private final long REFRESH_TOKEN_EXPIRATION = 24 * 60 * 60 * 1000;

    public JwtTokenResponse generateTokens(String userId, String username) {
        String accessToken = generateAccessToken(userId, username);
        String refreshToken = generateRefreshToken(userId, username);
        return new JwtTokenResponse(accessToken, refreshToken);
    }

    public String generateAccessToken(String userId, String username) {
        return Jwts.builder()
                .setSubject(userId)
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String userId, String username) {
        return Jwts.builder()
                .setSubject(userId)
                .claim("username", username)
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

    public String extractAndValidateHeader(String header) {
        if (header == null || !header.startsWith("Bearer "))
            throw new InvalidTokenException("Invalid authorization header.");

        String token = header.replace("Bearer ", "");

        if (!isTokenValid(token))
            throw new InvalidTokenException("Invalid token.");

        return token;
    }

    public String extractToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new InvalidTokenException("Missing or malformed Authorization header.");

        return authHeader.substring(7);
    }
}
