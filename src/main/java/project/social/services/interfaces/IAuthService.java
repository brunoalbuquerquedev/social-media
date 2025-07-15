package project.social.services.interfaces;

import project.social.dto.auth.JwtTokenResponse;
import project.social.dto.auth.LoginRequestDto;
import project.social.dto.auth.RefreshRequestDto;
import project.social.dto.auth.SignupRequestDto;

public interface IAuthService {
    void register(SignupRequestDto request);

    JwtTokenResponse login(LoginRequestDto request);

    JwtTokenResponse refresh(RefreshRequestDto request);

    void logout();

    void changePassword();

    void recoverPassword();
}
