package project.social.services.interfaces;

import project.social.dto.auth.JwtTokenDto;
import project.social.dto.auth.LoginRequestDto;
import project.social.dto.auth.RefreshRequestDto;
import project.social.dto.auth.SignupRequestDto;

public interface IAuthService {
    void register(SignupRequestDto request);

    JwtTokenDto login(LoginRequestDto request);

    JwtTokenDto refresh(RefreshRequestDto request);

    void logout(JwtTokenDto request);

    void changePassword();

    void recoverPassword();
}
