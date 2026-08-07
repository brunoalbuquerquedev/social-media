package project.social.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.social.domain.Media;

import java.util.List;

@Repository
public interface MediaRepository extends MongoRepository<Media, String> {
    Page<Media> findByUploadedBy(String uploadedBy, Pageable pageable);

    List<Media> findByUploadedBy(String uploadedBy);
}
