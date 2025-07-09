package project.social.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.social.domain.Block;

import java.util.List;
import java.util.Optional;

public interface BlockRepository extends MongoRepository<Block, String> {
    Optional<Block> findByBlockerIdAndBlockedId(String blockerId, String blockedId);
    List<Block> findByBlockerId(String blockerId);
}
