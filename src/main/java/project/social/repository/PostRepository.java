package project.social.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.social.domain.Post;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByTitleContainingIgnoreCase(String text);
    List<Post> findByAuthorIdInOrderByCreatedAtDesc(List<String> list);
}
