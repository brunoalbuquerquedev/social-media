package project.social.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import project.social.dto.domain.CommentDto;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Document(collection = "posts")
public class Post implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String authorUsername;
    private String authorId;
    private String content;
    private String title;
    private Date createdAt;

    @DBRef(lazy = true)
    private List<Like> likes = new ArrayList<>();

    @DBRef(lazy = true)
    private List<CommentDto> comments = new ArrayList<>();

    private boolean hasUserLiked;

    public Post() {
    }

    private Post(Builder builder) {
        this.id = builder.id;
        this.authorUsername = builder.authorUsername;
        this.authorId = builder.authorId;
        this.content = builder.content;
        this.title = builder.title;
        this.createdAt = builder.createdAt;
        this.likes = builder.likes;
        this.comments = builder.comments;
        this.hasUserLiked = builder.hasUserLiked;
    }

    public static class Builder {
        private String id;
        private String authorUsername;
        private String authorId;
        private String content;
        private String title;
        private Date createdAt;
        private List<Like> likes = new ArrayList<>();
        private List<CommentDto> comments = new ArrayList<>();
        private boolean hasUserLiked;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder authorUsername(String authorUsername) {
            this.authorUsername = authorUsername;
            return this;
        }

        public Builder authorId(String authorId) {
            this.authorId = authorId;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder likes(List<Like> likes) {
            this.likes = likes;
            return this;
        }

        public Builder comments(List<CommentDto> comments) {
            this.comments = comments;
            return this;
        }

        public Builder hasUserLiked(boolean hasUserLiked) {
            this.hasUserLiked = hasUserLiked;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
