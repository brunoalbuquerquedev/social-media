package project.social.dto.domain;

import project.social.domain.Like;
import project.social.domain.Post;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PostDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String authorId;
    private String content;
    private String title;
    private Date createdAt;

    private List<Like> likes = new ArrayList<>();
    private List<CommentDto> comments = new ArrayList<>();

    private boolean hasUserLiked;

    public PostDto() {
    }

    public PostDto(Post post) {
        this.id = post.getId();
        this.authorId = post.getAuthorId();
        this.content = post.getContent();
        this.title = post.getTitle();
        this.createdAt = post.getCreatedAt();

        if (post.getLikes() != null) {
            this.likes = post.getLikes();
        }

        if (post.getComments() != null) {
            this.comments = post.getComments().stream()
                    .map((commentDto) -> new CommentDto())
                    .collect(Collectors.toList());
        }
        this.hasUserLiked = false;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }


    public boolean isHasUserLiked() {
        return hasUserLiked;
    }

    public void setHasUserLiked(boolean hasUserLiked) {
        this.hasUserLiked = hasUserLiked;
    }
}
