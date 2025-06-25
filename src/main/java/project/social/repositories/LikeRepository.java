package project.social.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.social.domain.Like;

public interface LikeRepository extends MongoRepository<Like, String> {
}
