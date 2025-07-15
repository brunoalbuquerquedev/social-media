package project.social.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.social.domain.Like;

import java.util.Optional;

@Repository
public interface LikeRepository extends MongoRepository<Like, String> {
    Page<Like> findALLByUserId(String userId, Pageable pageable);

    Page<Like> findAllByPostId(String postId, Pageable pageable);

    Optional<Like> findByUserIdAndPostId(String userId, String postId);
}
