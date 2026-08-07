package project.social.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.social.domain.Retweet;

import java.util.Optional;

@Repository
public interface RetweetRepository extends MongoRepository<Retweet, String> {
    Page<Retweet> findByPostId(String postId, Pageable pageable);

    Page<Retweet> findByUserId(String userId, Pageable pageable);

    Optional<Retweet> findByUserIdAndPostId(String userId, String postId);

    void deleteByUserIdAndPostId(String userId, String postId);

    long countByPostId(String postId);
}
