package project.social.services.interfaces;

import project.social.dto.auth.JwtTokenDto;
import project.social.dto.auth.LoginRequestDto;
import project.social.dto.auth.RefreshRequestDto;
import project.social.dto.auth.SignupRequestDto;

public interface IAuthService {
    /**
     * Registers a new user with the provided signup request.
     *
     * @param request the signup request containing user details
     */
    void register(SignupRequestDto request);

    /**
     * Logs in a user with the provided login request.
     *
     * @param request the login request containing email and password
     * @return a JWT token if login is successful
     */
    JwtTokenDto login(LoginRequestDto request);

    /**
     * Refreshes the JWT token using the provided refresh request.
     *
     * @param request the refresh request containing the refresh token
     * @return a new JWT token if refresh is successful
     */
    JwtTokenDto refresh(RefreshRequestDto request);

    /**
     * Logs out the user by invalidating the provided JWT token.
     *
     * @param request the JWT token to be invalidated
     */
    void logout(JwtTokenDto request);

    /**
     * Changes the user's password.
     */
    void changePassword();

    /**
     * Recovers the user's password.
     */
    void recoverPassword();

    /**
     * Verifies the user's email.
     */
    void verifyEmail();
}
