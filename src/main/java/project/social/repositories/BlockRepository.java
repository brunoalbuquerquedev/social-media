package project.social.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.social.domain.Block;
import project.social.domain.enums.RestrictionType;

import java.util.Optional;

public interface BlockRepository extends MongoRepository<Block, String> {
    Optional<Block> findByBlockerUserIdAndBlockingUserIdAndType(String blockerUserId,
                                                                String blockingUserId,
                                                                RestrictionType type);
}
