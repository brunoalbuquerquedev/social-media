package project.social.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.social.dto.TimelinePostDto;
import project.social.dto.TimelineResponseDto;
import project.social.services.TimelineService;
import project.social.util.SecurityUtil;

import java.util.List;

@RestController
@RequestMapping("/home")
public class TimelineResource {

    @Autowired
    private TimelineService timelineService;

    @Autowired
    private SecurityUtil securityUtil;

    @GetMapping()
    public ResponseEntity<TimelineResponseDto> getTimeline() {
        String id = securityUtil.getLoggedUserId();
        List<TimelinePostDto> posts = timelineService.getTimelineForUser(id);
        return ResponseEntity.ok(new TimelineResponseDto(posts));
    }
}
