package project.social.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.social.domain.Story;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface StoryRepository extends MongoRepository<Story, String> {
    Page<Story> findByAuthorId(String authorId, Pageable pageable);

    List<Story> findByAuthorIdAndExpiresAtGreaterThan(String authorId, OffsetDateTime now);

    List<Story> findByExpiresAtGreaterThan(OffsetDateTime now);

    void deleteByExpiresAtLessThanEqual(OffsetDateTime now);
}
