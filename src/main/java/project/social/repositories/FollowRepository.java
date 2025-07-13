package project.social.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.social.domain.Follow;

import java.util.Optional;

@Repository
public interface FollowRepository extends MongoRepository<Follow, String> {
    Optional<Follow> findByRequesterIdAndTargetId(String requesterId, String targetId);

    Page<Follow> findAllByRequesterId(String requesterId, Pageable pageable);
}
