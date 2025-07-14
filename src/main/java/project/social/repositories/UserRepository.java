package project.social.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.social.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Page<User> findAllById(List<String> id, Pageable pageable);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
