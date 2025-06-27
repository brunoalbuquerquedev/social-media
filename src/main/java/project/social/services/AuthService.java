package project.social.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.social.domain.User;
import project.social.dto.auth.JwtTokenResponse;
import project.social.dto.auth.LoginRequestDto;
import project.social.dto.auth.RefreshRequestDto;
import project.social.dto.auth.SignupRequestDto;
import project.social.repositories.UserRepository;
import project.social.services.exceptions.*;
import project.social.util.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void register(SignupRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new UserAlreadyExistsException("This email already has an account.");

        if (userRepository.existsByUsername(request.getUsername()))
            throw new UserAlreadyExistsException("This username is unavailable.");

        if (request.getUsername() == null || request.getEmail() == null || request.getPassword() == null)
            throw new InvalidRequestDataException("Invalid request.");

        User.Builder userBuilder = new User.Builder();
        userBuilder.username(request.getUsername());
        userBuilder.email(request.getEmail());
        userBuilder.password(passwordEncoder.encode(request.getPassword()));
        User user = userBuilder.build();
        userRepository.save(user);
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
