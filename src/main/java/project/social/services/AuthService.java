package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.social.domain.User;
import project.social.dto.auth.JwtTokenResponse;
import project.social.dto.auth.LoginRequestDto;
import project.social.dto.auth.RefreshRequestDto;
import project.social.dto.auth.SignupRequestDto;
import project.social.exceptions.auth.ExpiredTokenException;
import project.social.exceptions.user.UserAlreadyExistsException;
import project.social.repositories.UserRepository;
import project.social.exceptions.auth.IncorrectPasswordException;
import project.social.exceptions.auth.InvalidRequestDataException;
import project.social.exceptions.auth.InvalidTokenException;
import project.social.exceptions.base.ObjectNotFoundException;
import project.social.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder;

    public void register(SignupRequestDto request) {
        if (userRepository.existsByEmail(request.email()))
            throw new UserAlreadyExistsException("This email already has an account.");

        if (userRepository.existsByUsername(request.username()))
            throw new UserAlreadyExistsException("This username is unavailable.");

        if (request.username() == null || request.email() == null || request.password() == null)
            throw new InvalidRequestDataException("Invalid request.");

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
        userRepository.save(user);
    }

    public JwtTokenResponse login(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new ObjectNotFoundException("User not found."));

        if (!passwordEncoder.matches(request.password(), user.getPassword()))
            throw new IncorrectPasswordException("Incorrect password.");

        return jwtUtil.generateTokens(user.getId(), user.getUsername());
    }

    public JwtTokenResponse refresh(RefreshRequestDto request) {
        if (!jwtUtil.isTokenValid(request.refreshToken()))
            throw new ExpiredTokenException("Invalid or expired token.");

        String userId = jwtUtil.getUserIdFromToken(request.refreshToken());
        String username = jwtUtil.extractUsername(request.refreshToken());

        if (userId == null || username == null)
            throw new InvalidTokenException("Invalid token.");

        return jwtUtil.generateTokens(userId, username);
    }
}
