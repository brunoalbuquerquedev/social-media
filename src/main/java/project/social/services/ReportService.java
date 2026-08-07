package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.social.common.dtos.domain.ReportDto;
import project.social.common.exceptions.base.ObjectNotFoundException;
import project.social.domain.Report;
import project.social.domain.enums.ReportContentType;
import project.social.domain.enums.ReportStatus;
import project.social.repositories.ReportRepository;
import project.social.services.interfaces.IReportService;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class ReportService implements IReportService {

    private final ReportRepository reportRepository;

    @Override
    public ReportDto createReport(String reportedBy, ReportContentType contentType, 
                                  String contentId, String reason, String description) {
        Report report = Report.builder()
                .reportedBy(reportedBy)
                .contentType(contentType)
                .contentId(contentId)
                .reason(reason)
                .description(description)
                .status(ReportStatus.PENDING)
                .createdAt(OffsetDateTime.now())
                .build();

        Report saved = reportRepository.save(report);
        return new ReportDto(saved);
    }

    @Override
    public Page<ReportDto> getPendingReports(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return reportRepository.findByStatus(ReportStatus.PENDING, pageable)
                .map(ReportDto::new);
    }

    @Override
    public Page<ReportDto> getReportsByStatus(ReportStatus status, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return reportRepository.findByStatus(status, pageable)
                .map(ReportDto::new);
    }

    @Override
    public ReportDto getReportById(String id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Report not found."));
        return new ReportDto(report);
    }

    @Override
    public ReportDto reviewReport(String id, String reviewedBy, String resolution) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Report not found."));

        report.setStatus(ReportStatus.REVIEWED);
        report.setReviewedBy(reviewedBy);
        report.setReviewedAt(OffsetDateTime.now());
        report.setResolution(resolution);

        Report updated = reportRepository.save(report);
        return new ReportDto(updated);
    }

    @Override
    public ReportDto resolveReport(String id, String reviewedBy, String resolution) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Report not found."));

        report.setStatus(ReportStatus.RESOLVED);
        report.setReviewedBy(reviewedBy);
        report.setReviewedAt(OffsetDateTime.now());
        report.setResolution(resolution);

        Report updated = reportRepository.save(report);
        return new ReportDto(updated);
    }

    @Override
    public long getPendingReportCount() {
        return reportRepository.countByStatus(ReportStatus.PENDING);
    }
}
