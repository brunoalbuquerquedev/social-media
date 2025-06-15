package project.mongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.mongo.domain.User;
import project.mongo.repository.UserRepository;
import project.mongo.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found."));
    }

    public User insert(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        Optional<User> optionalUser = Optional.of(findById(user.getId()));
        userRepository.delete(optionalUser.get());
    }

    public void deleteById(String id) {
        Optional<User> user = Optional.of(findById(id));
        userRepository.deleteById(id);
    }
}
