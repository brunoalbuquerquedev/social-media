package project.social.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.social.domain.RevokedToken;

public interface RevokedTokenRepository extends MongoRepository<RevokedToken, String> {

}
