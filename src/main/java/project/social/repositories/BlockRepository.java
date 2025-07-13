package project.social.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.social.domain.Block;
import project.social.domain.enums.RestrictionType;

import java.util.Optional;

@Repository
public interface BlockRepository extends MongoRepository<Block, String> {
    Optional<Block> findByRequesterIdAndTargetIdAndType(String requesterId, String targetId, RestrictionType type);

    Page<Block> findAllByBlockerUserId(String blockerUserId, Pageable pageable);
}
