package project.social.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.social.domain.User;
import project.social.dto.auth.LoginRequestDto;
import project.social.dto.auth.RefreshRequestDto;
import project.social.dto.auth.SignupRequestDto;
import project.social.repositories.UserRepository;
import project.social.services.exceptions.IncorrectPasswordException;
import project.social.services.exceptions.InvalidTokenException;
import project.social.services.exceptions.ObjectNotFoundException;
import project.social.services.exceptions.UserAlreadyExistsException;
import project.social.dto.auth.JwtTokenResponse;
import project.social.util.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public JwtTokenResponse register(SignupRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new UserAlreadyExistsException("This email already has an account.");

        if (userRepository.existsByUsername(request.getUsername()))
            throw new UserAlreadyExistsException("This username is unavailable.");

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return jwtUtil.generateTokens(user.getId(), user.getUsername());
    }

    public JwtTokenResponse login(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ObjectNotFoundException("User not found."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new IncorrectPasswordException("Incorrect password.");

        return jwtUtil.generateTokens(user.getId(), user.getUsername());
    }

    public JwtTokenResponse refresh(RefreshRequestDto request) {
        if (!jwtUtil.isTokenValid(request.getRefreshToken()))
            throw new InvalidTokenException("Invalid or expired token.");

        String userId = jwtUtil.getUserIdFromToken(request.getRefreshToken());
        String username = jwtUtil.extractUsername(request.getRefreshToken());

        if (userId == null || username == null)
            throw new InvalidTokenException("Invalid token.");

        return jwtUtil.generateTokens(userId, username);
    }
}
