package project.social.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.social.domain.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    Page<Comment> findByPostId(String postId, Pageable pageable);
    
    List<Comment> findByPostId(String postId);
    
    List<Comment> findByAuthorId(String authorId);
    
    Page<Comment> findByAuthorId(String authorId, Pageable pageable);
}
