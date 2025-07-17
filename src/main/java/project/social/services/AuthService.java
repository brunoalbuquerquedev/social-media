package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.social.domain.RevokedToken;
import project.social.domain.User;
import project.social.domain.enums.UserRole;
import project.social.dto.auth.JwtTokenDto;
import project.social.dto.auth.LoginRequestDto;
import project.social.dto.auth.RefreshRequestDto;
import project.social.dto.auth.SignupRequestDto;
import project.social.exceptions.auth.ExpiredTokenException;
import project.social.exceptions.auth.IncorrectPasswordException;
import project.social.exceptions.auth.InvalidRequestDataException;
import project.social.exceptions.auth.InvalidTokenException;
import project.social.exceptions.base.ObjectNotFoundException;
import project.social.exceptions.domain.UserAlreadyExistsException;
import project.social.repositories.RefreshTokenRepository;
import project.social.repositories.RevokedTokenRepository;
import project.social.repositories.UserRepository;
import project.social.services.interfaces.IAuthService;
import project.social.util.EnumUtils;
import project.social.security.JwtUtils;

import java.time.OffsetDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RevokedTokenRepository revokedTokenRepository;

    @Override
    public void register(SignupRequestDto request) {
        if (userRepository.existsByEmail(request.email()))
            throw new UserAlreadyExistsException("This email already has an account.");

        if (userRepository.existsByUsername(request.username()))
            throw new UserAlreadyExistsException("This username is unavailable.");

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .role(request.role())
                .password(passwordEncoder.encode(request.password()))
                .createdAt(OffsetDateTime.now())
                .build();

        userRepository.save(user);
    }

    @Override
    public JwtTokenDto login(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new ObjectNotFoundException("User not found."));

        if (!passwordEncoder.matches(request.password(), user.getPassword()))
            throw new IncorrectPasswordException("Incorrect password.");

        UserRole role = EnumUtils.safeValueOf(UserRole.class, user.getRole())
                .orElseThrow(() -> new InvalidRequestDataException("Invalid data in json body."));

        return jwtUtils.generateTokens(user.getId(), user.getUsername(), role);
    }

    @Override
    public JwtTokenDto refresh(RefreshRequestDto request) {
        String token = request.refreshToken();

        refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Refresh token not found or already revoked."));

        if (!jwtUtils.isTokenValid(token)) {
            refreshTokenRepository.deleteByToken(token);
            throw new ExpiredTokenException("Token expired.");
        }

        String userId = jwtUtils.extractUserId(token);
        String username = jwtUtils.extractField(token, "username");
        String role = jwtUtils.extractField(token, "role");

        UserRole userRole = EnumUtils.safeValueOf(UserRole.class, role)
                .orElseThrow(() -> new InvalidTokenException("Invalid role data in request json body."));

        refreshTokenRepository.deleteByToken(token);
        return jwtUtils.generateTokens(userId, username, userRole);
    }

    @Override
    public void logout(JwtTokenDto request) {
        String accessToken = request.accessToken();
        String userId = jwtUtils.extractUserId(accessToken);
        Date expiration = jwtUtils.extractExpiration(request.accessToken());

        RevokedToken revokedToken = RevokedToken.builder()
                .userId(userId)
                .token(accessToken)
                .expiresAt(OffsetDateTime.from(expiration.toInstant()))
                .createdAt(OffsetDateTime.now())
                .build();

        revokedTokenRepository.save(revokedToken);
        refreshTokenRepository.deleteByToken(request.refreshToken());
    }

    @Override
    public void changePassword() {
        throw new UnsupportedOperationException("Unsupported operation.");
    }

    @Override
    public void recoverPassword() {
        throw new UnsupportedOperationException("Unsupported operation.");
    }
}
