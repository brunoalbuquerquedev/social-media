package project.social.services.interfaces;

import project.social.dto.domain.UserDto;

import java.util.List;

public interface IUserService {
    List<UserDto> findAll();

    List<UserDto> findAllById(List<String> id);

    UserDto findById(String id);

    UserDto findByUsername(String username);
}
