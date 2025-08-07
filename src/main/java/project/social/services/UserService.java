package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.social.common.dtos.domain.user.UserDto;
import project.social.common.dtos.domain.user.UserResponseDto;
import project.social.common.dtos.domain.user.UserUpdateDto;
import project.social.common.exceptions.base.InvalidRequestException;
import project.social.common.exceptions.base.ObjectNotFoundException;
import project.social.domain.User;
import project.social.domain.mappers.UserMapper;
import project.social.repositories.UserRepository;
import project.social.services.interfaces.IUserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public Page<UserDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable).map(UserDto::new);
    }

    @Override
    public Page<UserResponseDto> findAllById(List<String> ids, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAllById(ids, pageable).map(UserResponseDto::new);
    }

    public List<UserDto> findAllById(List<String> ids) {
        List<UserDto> list = userRepository.findAllById(ids).stream()
                .map(UserDto::new)
                .toList();

        if (list.isEmpty()) throw new ObjectNotFoundException("No users found with the provided IDs.");
        return list;
    }

    @Override
    public UserResponseDto findById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found."));
        return new UserResponseDto(user);
    }

    @Override
    public UserResponseDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User not found."));
        return new UserResponseDto(user);
    }

    @Override
    public void updateUser(String userId, UserUpdateDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));

        if (request.id().equals(userId))
            throw new InvalidRequestException("Invalid request: user id and request body id are different.");

        userRepository.save(UserMapper.fromUpdateDto(user, request));
    }
}
