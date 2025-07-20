package project.social.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.social.domain.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    Page<Post> findByAuthorIdInOrderByCreatedAtDesc(List<String> list, Pageable pageable);

    Optional<Post> findByAuthorUsername(String username);

    Page<Post> findByUserIdAndHasUserLiked(String userId, boolean hasUserLiked, Pageable pageable);

    Page<Post> findAllById(String id, Pageable pageable);
}
