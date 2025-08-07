package project.social.resources;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.social.common.dtos.auth.*;
import org.springframework.web.bind.annotation.*;
import project.social.services.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthResource {

    private final AuthService authService;

    /**
     * Handles user registration.
     *
     * @param request the signup request containing user details
     * @return a response indicating the result of the signup operation
     */
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequestDto request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Handles user login.
     *
     * @param request the login request containing email and password
     * @return a response containing the JWT token if login is successful
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        JwtTokenDto token = authService.login(request);
        return ResponseEntity.ok(new AuthResponseDto(token));
    }

    /**
     * Handles token refresh.
     *
     * @param request the refresh request containing the refresh token
     * @return a response containing the new JWT token if refresh is successful
     */
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refresh(@Valid @RequestBody RefreshRequestDto request) {
        JwtTokenDto token = authService.refresh(request);
        return ResponseEntity.ok(new AuthResponseDto(token));
    }

    /**
     * Handles user logout.
     *
     * @param request the JWT token to be invalidated
     * @return a response indicating successful logout
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody JwtTokenDto request) {
        authService.logout(request);
        return ResponseEntity.noContent().build();
    }

    /**
     * Handles password change.
     *
     * @return a response indicating successful password change
     */
    @PostMapping("/password/change")
    public ResponseEntity<AuthResponseDto> changePassword() {
        authService.changePassword();
        return ResponseEntity.noContent().build();
    }

    /**
     * Handles password recovery.
     *
     * @return a response indicating successful password recovery initiation
     */
    @PostMapping("/recover")
    public ResponseEntity<AuthResponseDto> recoverPassword() {
        authService.recoverPassword();
        return ResponseEntity.noContent().build();
    }

    /**
     * Handles email verification.
     *
     * @param token the verification token
     * @return a response indicating successful email verification
     */
    @GetMapping("/verify-email")
    public ResponseEntity<Void> verifyEmail(@RequestParam String token) {
        authService.verifyEmail();
        return ResponseEntity.ok().build();
    }
}
