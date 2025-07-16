package project.social.services;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.social.domain.User;
import project.social.domain.enums.UserRole;
import project.social.dto.auth.JwtTokenResponse;
import project.social.dto.auth.LoginRequestDto;
import project.social.dto.auth.RefreshRequestDto;
import project.social.dto.auth.SignupRequestDto;
import project.social.exceptions.auth.ExpiredTokenException;
import project.social.exceptions.auth.IncorrectPasswordException;
import project.social.exceptions.auth.InvalidTokenException;
import project.social.exceptions.base.ObjectNotFoundException;
import project.social.exceptions.domain.UserAlreadyExistsException;
import project.social.repositories.UserRepository;
import project.social.services.interfaces.IAuthService;
import project.social.util.EnumUtils;
import project.social.util.JwtUtils;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void register(@NotNull SignupRequestDto request) {
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
    public JwtTokenResponse login(@NotNull LoginRequestDto request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new ObjectNotFoundException("User not found."));

        if (!passwordEncoder.matches(request.password(), user.getPassword()))
            throw new IncorrectPasswordException("Incorrect password.");

        UserRole role = EnumUtils.safeValueOf(UserRole.class, user.getRole())
                .orElseThrow(() -> new InvalidTokenException("Invalid role data in request json body."));

        return jwtUtils.generateTokens(user.getId(), user.getUsername(), role);
    }

    @Override
    public JwtTokenResponse refresh(@NotNull RefreshRequestDto request) {
        if (!jwtUtils.isTokenValid(request.refreshToken()))
            throw new ExpiredTokenException("Invalid or expired token.");

        String userId = jwtUtils.getUserIdFromToken(request.refreshToken());
        String username = jwtUtils.extractUsername(request.refreshToken());
        String role = jwtUtils.extractRole(request.refreshToken());

        UserRole userRole = EnumUtils.safeValueOf(UserRole.class, role)
                .orElseThrow(() -> new InvalidTokenException("Invalid role data in request json body."));

        return jwtUtils.generateTokens(userId, username, userRole);
    }

    @Override
    public void logout() {

    }

    @Override
    public void changePassword() {

    }

    @Override
    public void recoverPassword() {

    }
}
