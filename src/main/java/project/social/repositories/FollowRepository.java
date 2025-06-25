package project.social.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.social.domain.Follow;

public interface FollowRepository extends MongoRepository<Follow, String> {
}
