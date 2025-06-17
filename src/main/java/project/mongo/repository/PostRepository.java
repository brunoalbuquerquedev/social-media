package project.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.mongo.domain.Post;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByTitleContainingIgnoreCase(String text);
}
