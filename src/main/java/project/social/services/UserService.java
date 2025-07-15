package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<UserDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable).map(UserDto::new);
    }
  
    @Override
    public Page<UserDto> findAllById(List<String> id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAllById(id, pageable).map(UserDto::new);
    }

    @Override
    public UserDto findById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found."));
        return new UserDto(user);
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User not found."));
        return new UserDto(user);
    }
}
