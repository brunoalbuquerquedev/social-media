package project.social.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.social.domain.Follow;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends MongoRepository<Follow, String> {
    Optional<Follow> findByRequesterIdAndTargetId(String requesterId, String targetId);

    List<Follow> findByRequesterId(String requesterId);
}
