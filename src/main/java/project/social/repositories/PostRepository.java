package project.social.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.social.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByTitleContainingIgnoreCase(String text);
    List<Post> findByAuthorIdInOrderByCreatedAtDesc(List<String> list);
    Optional<Post> findByAuthorUsername(String username);
}
