package project.social.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.social.domain.Report;
import project.social.domain.enums.ReportStatus;

import java.util.List;

@Repository
public interface ReportRepository extends MongoRepository<Report, String> {
    Page<Report> findByStatus(ReportStatus status, Pageable pageable);

    Page<Report> findByReportedBy(String reportedBy, Pageable pageable);

    Page<Report> findByContentId(String contentId, Pageable pageable);

    List<Report> findByStatus(ReportStatus status);

    long countByStatus(ReportStatus status);
}
