package project.social.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.social.domain.RevokedToken;

import java.util.Optional;

public interface RevokedTokenRepository extends MongoRepository<RevokedToken, String> {
    Optional<RevokedToken> findByToken(String token);
}
