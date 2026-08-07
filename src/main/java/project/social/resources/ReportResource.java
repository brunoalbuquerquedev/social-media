package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.common.annotations.CurrentUser;
import project.social.common.dtos.domain.ReportDto;
import project.social.domain.enums.ReportContentType;
import project.social.domain.enums.ReportStatus;
import project.social.services.interfaces.IReportService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reports")
public class ReportResource {

    private final IReportService reportService;

    /**
     * Creates a new report.
     *
     * @param contentType the type of content being reported
     * @param contentId the ID of the content
     * @param reason the reason for the report
     * @param description the detailed description
     * @param currentUserId the ID of the current user
     * @return the created ReportDto
     */
    @PostMapping
    public ResponseEntity<ReportDto> createReport(
            @RequestParam ReportContentType contentType,
            @RequestParam String contentId,
            @RequestParam String reason,
            @RequestParam String description,
            @CurrentUser String currentUserId) {
        ReportDto report = reportService.createReport(currentUserId, contentType, contentId, reason, description);
        return ResponseEntity.status(HttpStatus.CREATED).body(report);
    }

    /**
     * Retrieves pending reports (admin only).
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of ReportDtos
     */
    @GetMapping("/pending")
    public ResponseEntity<Page<ReportDto>> getPendingReports(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<ReportDto> reports = reportService.getPendingReports(pageNumber, pageSize);
        return ResponseEntity.ok(reports);
    }

    /**
     * Retrieves reports by status (admin only).
     *
     * @param status the report status
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of ReportDtos
     */
    @GetMapping("/status")
    public ResponseEntity<Page<ReportDto>> getReportsByStatus(
            @RequestParam ReportStatus status,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<ReportDto> reports = reportService.getReportsByStatus(status, pageNumber, pageSize);
        return ResponseEntity.ok(reports);
    }

    /**
     * Retrieves a report by its ID.
     *
     * @param id the ID of the report
     * @return the ReportDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> getReport(@PathVariable String id) {
        ReportDto report = reportService.getReportById(id);
        return ResponseEntity.ok(report);
    }

    /**
     * Reviews a report (admin only).
     *
     * @param id the ID of the report
     * @param resolution the resolution of the report
     * @param currentUserId the ID of the current admin
     * @return the updated ReportDto
     */
    @PutMapping("/{id}/review")
    public ResponseEntity<ReportDto> reviewReport(
            @PathVariable String id,
            @RequestParam String resolution,
            @CurrentUser String currentUserId) {
        ReportDto report = reportService.reviewReport(id, currentUserId, resolution);
        return ResponseEntity.ok(report);
    }

    /**
     * Resolves a report (admin only).
     *
     * @param id the ID of the report
     * @param resolution the resolution of the report
     * @param currentUserId the ID of the current admin
     * @return the updated ReportDto
     */
    @PutMapping("/{id}/resolve")
    public ResponseEntity<ReportDto> resolveReport(
            @PathVariable String id,
            @RequestParam String resolution,
            @CurrentUser String currentUserId) {
        ReportDto report = reportService.resolveReport(id, currentUserId, resolution);
        return ResponseEntity.ok(report);
    }

    /**
     * Gets the count of pending reports (admin only).
     *
     * @return the count of pending reports
     */
    @GetMapping("/pending/count")
    public ResponseEntity<Long> getPendingReportCount() {
        long count = reportService.getPendingReportCount();
        return ResponseEntity.ok(count);
    }
}
