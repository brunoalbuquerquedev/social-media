package project.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.mongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {
}
