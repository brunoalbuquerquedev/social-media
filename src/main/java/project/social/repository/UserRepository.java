package project.social.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.social.domain.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
