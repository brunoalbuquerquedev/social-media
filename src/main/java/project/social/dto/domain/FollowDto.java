package project.social.dto.domain;

import project.social.domain.Follow;
import project.social.domain.enums.FollowStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class FollowDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String followerId;
    private String followingId;
    private Date createdAt;
    private boolean notified;
    private FollowStatus status;

    public FollowDto() {
    }

    public FollowDto(Follow follow) {
        this.id = follow.getId();
        this.followerId = follow.getFollowerId();
        this.followingId = follow.getFollowedId();
        this.createdAt = follow.getCreatedAt();
        this.notified = follow.getNotified();
        this.status = follow.getStatus();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }

    public String getFollowingId() {
        return followingId;
    }

    public void setFollowingId(String followingId) {
        this.followingId = followingId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }

    public FollowStatus getStatus() {
        return status;
    }

    public void setStatus(FollowStatus status) {
        this.status = status;
    }
}
