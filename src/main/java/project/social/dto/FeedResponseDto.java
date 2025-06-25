package project.social.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class FeedResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<FeedDto> posts;

    public FeedResponseDto() {
    }

    public FeedResponseDto(List<FeedDto> posts) {
        this.posts = posts;
    }

    public List<FeedDto> getPosts() {
        return posts;
    }

    public void setPosts(List<FeedDto> posts) {
        this.posts = posts;
    }
}
