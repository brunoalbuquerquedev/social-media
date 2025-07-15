package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.dto.domain.UserDto;

import java.util.List;

public interface IUserService {
    Page<UserDto> findAllById(List<String> id, int page, int size);

    UserDto findById(String id);

    UserDto findByUsername(String username);
}
