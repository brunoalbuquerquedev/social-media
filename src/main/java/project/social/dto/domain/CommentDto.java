package project.social.dto.domain;

import project.social.domain.Comment;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class CommentDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String postId;
    private String authorId;
    private String content;
    private Date createdAt;

    public CommentDto() {
    }

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.postId = getPostId();
        this.authorId = getAuthorId();
        this.content = getContent();
        this.createdAt = getCreatedAt();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
