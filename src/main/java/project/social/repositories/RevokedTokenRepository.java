package project.social.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.social.domain.RevokedToken;

import java.time.OffsetDateTime;

public interface RevokedTokenRepository extends MongoRepository<RevokedToken, String> {
    int deleteAllByExpiresAtBefore(OffsetDateTime time);
}
