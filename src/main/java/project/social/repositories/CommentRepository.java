package project.social.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.social.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
