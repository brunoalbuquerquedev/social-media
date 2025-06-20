package project.social.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class TimelineResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<TimelinePostDto> posts;

    public TimelineResponseDto() {
    }

    public TimelineResponseDto(List<TimelinePostDto> posts) {
        this.posts = posts;
    }

    public List<TimelinePostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<TimelinePostDto> posts) {
        this.posts = posts;
    }
}
