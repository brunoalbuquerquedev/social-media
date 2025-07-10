package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.social.domain.User;
import project.social.repositories.UserRepository;
import project.social.exceptions.base.ObjectNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllById(List<String> id) {
        return userRepository.findAllById(id)
                .orElseThrow(() -> new ObjectNotFoundException("No users found."));
    }

    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found."));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User not found."));
    }
}
