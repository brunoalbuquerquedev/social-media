package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.common.dtos.domain.ReportDto;
import project.social.domain.enums.ReportContentType;
import project.social.domain.enums.ReportStatus;

public interface IReportService {
    /**
     * Creates a new report.
     *
     * @param reportedBy the ID of the user reporting
     * @param contentType the type of content being reported
     * @param contentId the ID of the content
     * @param reason the reason for the report
     * @param description the detailed description of the report
     * @return the created ReportDto
     */
    ReportDto createReport(String reportedBy, ReportContentType contentType, 
                           String contentId, String reason, String description);

    /**
     * Retrieves pending reports.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of ReportDtos
     */
    Page<ReportDto> getPendingReports(int pageNumber, int pageSize);

    /**
     * Retrieves all reports with a given status.
     *
     * @param status the report status
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of ReportDtos
     */
    Page<ReportDto> getReportsByStatus(ReportStatus status, int pageNumber, int pageSize);

    /**
     * Retrieves a report by its ID.
     *
     * @param id the ID of the report
     * @return the ReportDto if found
     */
    ReportDto getReportById(String id);

    /**
     * Reviews a report.
     *
     * @param id the ID of the report
     * @param reviewedBy the ID of the reviewer
     * @param resolution the resolution of the report
     * @return the updated ReportDto
     */
    ReportDto reviewReport(String id, String reviewedBy, String resolution);

    /**
     * Resolves a report.
     *
     * @param id the ID of the report
     * @param reviewedBy the ID of the reviewer
     * @param resolution the resolution of the report
     * @return the updated ReportDto
     */
    ReportDto resolveReport(String id, String reviewedBy, String resolution);

    /**
     * Gets the count of pending reports.
     *
     * @return the count of pending reports
     */
    long getPendingReportCount();
}
