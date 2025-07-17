package project.social.services.interfaces;

import project.social.common.dtos.auth.JwtTokenDto;
import project.social.common.dtos.auth.LoginRequestDto;
import project.social.common.dtos.auth.RefreshRequestDto;
import project.social.common.dtos.auth.SignupRequestDto;

public interface IAuthService {
    void register(SignupRequestDto request);

    JwtTokenDto login(LoginRequestDto request);

    JwtTokenDto refresh(RefreshRequestDto request);

    void logout(JwtTokenDto request);

    void changePassword();

    void recoverPassword();
}
