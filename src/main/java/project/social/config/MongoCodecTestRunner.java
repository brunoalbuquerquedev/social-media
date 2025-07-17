package project.social.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import project.social.domain.User;

import java.time.OffsetDateTime;
import java.util.Optional;

@Component
public class MongoCodecTestRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void testCodec() {
        try {
            User user = new User();
            user.setUsername("test_user");
            user.setCreatedAt(OffsetDateTime.now());

            mongoTemplate.insert(user);
            Optional<User> fetched = Optional.ofNullable(mongoTemplate.findById(user.getId(), User.class));

            if (fetched.isEmpty()) {
                System.out.println("User not found in the database.");
                return;
            }
            System.out.println("Fetched createdAt: " + fetched.get().getCreatedAt());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
