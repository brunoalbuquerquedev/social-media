package project.social.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.social.domain.Hashtag;

import java.util.Optional;
import java.util.List;

@Repository
public interface HashtagRepository extends MongoRepository<Hashtag, String> {
    Optional<Hashtag> findByTag(String tag);

    Page<Hashtag> findByTagContainingIgnoreCase(String tag, Pageable pageable);

    Page<Hashtag> findAllByOrderByUsageCountDesc(Pageable pageable);

    List<Hashtag> findTop10ByOrderByUsageCountDesc();
}
