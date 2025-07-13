package project.social.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.social.domain.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
}
