package project.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.mongo.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

}
