package project.social.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.social.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);

    void deleteAllByUserId(String userId);
}
