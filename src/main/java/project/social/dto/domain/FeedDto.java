package project.social.dto.domain;

import project.social.domain.Post;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class FeedDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String authorId;
    private String content;
    private String title;
    private Date createdAt;

    private String authorUsername;
    private String authorProfilePictureUrl;

    private int likesCount;
    private int commentsCount;

    private boolean likedByCurrentUser;

    public FeedDto() {
    }

    public FeedDto(Post post) {
        this.id = post.getId();
        this.authorId = post.getAuthorId();
        this.content = post.getContent();
        this.title = post.getTitle();
        this.createdAt = post.getCreatedAt();
        this.likesCount = post.getLikes().size();
        this.commentsCount = post.getComments().size();
        this.likedByCurrentUser = post.isHasUserLiked();
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

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getAuthorProfilePictureUrl() {
        return authorProfilePictureUrl;
    }

    public void setAuthorProfilePictureUrl(String authorProfilePictureUrl) {
        this.authorProfilePictureUrl = authorProfilePictureUrl;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public boolean isLikedByCurrentUser() {
        return likedByCurrentUser;
    }

    public void setLikedByCurrentUser(boolean likedByCurrentUser) {
        this.likedByCurrentUser = likedByCurrentUser;
    }
}
