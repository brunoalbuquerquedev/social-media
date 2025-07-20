package project.social.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import project.social.domain.RefreshToken;
import project.social.domain.enums.UserRole;
import project.social.dto.auth.JwtTokenDto;
import project.social.exceptions.auth.InvalidTokenException;
import project.social.repositories.RefreshTokenRepository;

import java.security.Key;
import java.time.OffsetDateTime;
import java.util.Date;

@Component
public class JwtUtils {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long ACCESS_TOKEN_EXPIRATION = 10 * 60 * 1000;
    private final long REFRESH_TOKEN_EXPIRATION = 24 * 60 * 60 * 1000;

    private RefreshTokenRepository refreshTokenRepository;

    public JwtTokenDto generateTokens(String userId, String username, UserRole role) {
        String accessToken = generateAccessToken(userId, username, role);
        String refreshToken = generateRefreshToken(userId, username, role);

        RefreshToken token = RefreshToken.builder()
                .userId(userId)
                .token(refreshToken)
                .createdAt(OffsetDateTime.now())
                .expiresAt(OffsetDateTime.now().plusDays(1))
                .build();

        refreshTokenRepository.save(token);

        return new JwtTokenDto(accessToken, refreshToken);
    }

    /**
     * Generates an access token for the user.
     *
     * @param userId   The user's ID.
     * @param username The user's username.
     * @param role     The user's role.
     * @return A JWT access token as a String.
     */
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

    /**
     * Generates a refresh token for the user.
     *
     * @param userId   The user's ID.
     * @param username The user's username.
     * @param role     The user's role.
     * @return A JWT refresh token as a String.
     */
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

    /**
     * Extracts the user ID from the JWT token.
     *
     * @param token The JWT token.
     * @return The user ID as a String.
     */
    public String extractUserId(String token) {
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

    /**
     * Validates the JWT token.
     *
     * @param token The JWT token to validate.
     * @return true if the token is valid, false otherwise.
     */
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

    /**
     * Extracts the token from the Authorization header.
     *
     * @param authHeader The Authorization header containing the token.
     * @return The extracted token as a String.
     */
    public String extractToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new InvalidTokenException("Missing or malformed Authorization header.");

        return authHeader.substring(7);
    }

    /**
     * Extracts a specific field from the JWT token.
     *
     * @param token The JWT token.
     * @param field The field to extract.
     * @return The value of the specified field as a String.
     */
    public String extractField(String token, String field) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get(field, String.class);
        } catch (JwtException e) {
            throw new InvalidTokenException("Invalid or expired token.");
        }
    }

    /**
     * Extracts the expiration date from the JWT token.
     *
     * @param token The JWT token.
     * @return The expiration date as a Date object.
     */
    public Date extractExpiration(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
        } catch (JwtException e) {
            throw new InvalidTokenException("Invalid or expired token.");
        }
    }
}
