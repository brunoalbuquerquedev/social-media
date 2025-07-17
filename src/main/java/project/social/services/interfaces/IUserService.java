package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.common.dtos.domain.user.UserDto;
import project.social.common.dtos.domain.user.UserResponseDto;
import project.social.common.dtos.domain.user.UserUpdateDto;

import java.util.List;

public interface IUserService {
    Page<UserResponseDto> findAllById(List<String> id, int page, int size);

    UserResponseDto findById(String id);

    UserResponseDto findByUsername(String username);

    void updateUser(String userId, UserUpdateDto dto);
}
