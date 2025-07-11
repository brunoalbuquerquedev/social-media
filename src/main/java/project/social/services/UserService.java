package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.social.domain.User;
import project.social.dto.domain.UserDto;
import project.social.exceptions.base.ObjectNotFoundException;
import project.social.repositories.UserRepository;
import project.social.services.interfaces.IUserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::new).toList();
    }

    public List<UserDto> findAllById(List<String> id) {
        return userRepository.findAllById(id).stream().map(UserDto::new).toList();
    }

    public UserDto findById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found."));
        return new UserDto(user);
    }

    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User not found."));
        return new UserDto(user);
    }
}
