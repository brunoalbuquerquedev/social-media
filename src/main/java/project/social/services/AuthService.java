package project.social.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.social.domain.User;
import project.social.dto.auth.LoginRequest;
import project.social.dto.auth.RefreshRequest;
import project.social.dto.auth.SignupRequest;
import project.social.repository.UserRepository;
import project.social.services.exceptions.IncorrectPasswordException;
import project.social.services.exceptions.InvalidTokenException;
import project.social.services.exceptions.ObjectNotFoundException;
import project.social.services.exceptions.UserAlreadyExistsException;
import project.social.util.JwtTokenResponse;
import project.social.util.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public JwtTokenResponse register(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new UserAlreadyExistsException("This email already has an account.");

        if (userRepository.existsByUsername(request.getUsername()))
            throw new UserAlreadyExistsException("This username is unavailable.");

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return JwtUtil.generateTokens(user.getId(), user.getUsername());
    }

    public JwtTokenResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ObjectNotFoundException("User not found."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new IncorrectPasswordException("Incorrect password.");

        return JwtUtil.generateTokens(user.getId(), user.getUsername());
    }

    public JwtTokenResponse refresh(RefreshRequest request) {
        if (!JwtUtil.isTokenValid(request.getRefreshToken()))
            throw new InvalidTokenException("Invalid or expired token.");

        String userId = JwtUtil.validateAndGetUserId(request.getRefreshToken());
        String username = JwtUtil.extractUsername(request.getRefreshToken());

        if (userId == null || username == null)
            throw new InvalidTokenException("Invalid token.");

        return JwtUtil.generateTokens(userId, username);
    }
}
