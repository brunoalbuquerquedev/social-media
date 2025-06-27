package project.social.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.social.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<List<User>> findAllById(List<String> id);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
