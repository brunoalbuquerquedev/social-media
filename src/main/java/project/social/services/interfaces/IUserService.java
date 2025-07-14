package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.social.dto.domain.UserDto;

import java.util.List;

public interface IUserService {
    Page<UserDto> findAll(int page, int size);

    Page<UserDto> findAllById(List<String> id, int page, int size);

    UserDto findById(String id);

    UserDto findByUsername(String username);
}
