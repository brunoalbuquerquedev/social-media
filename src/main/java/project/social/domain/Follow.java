package project.social.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import project.social.domain.enums.FollowStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Document(collection = "follows")
public class Follow implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String followerId;
    private String followingId;
    private Date createdAt;
    private boolean notified;
    private FollowStatus status;

    public Follow() {
    }

    private Follow(Builder builder) {
        this.id = builder.id;
        this.followerId = builder.followerId;
        this.followingId = builder.followingId;
        this.createdAt = builder.createdAt;
        this.notified = builder.notified;
        this.status = builder.status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String followerId;
        private String followingId;
        private Date createdAt = new Date();
        private boolean notified = false;
        private FollowStatus status = null;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder followerId(String followerId) {
            this.followerId = followerId;
            return this;
        }

        public Builder followingId(String followingId) {
            this.followingId = followingId;
            return this;
        }

        public Builder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder notified(boolean notified) {
            this.notified = notified;
            return this;
        }

        public Builder status(FollowStatus status) {
            this.status = status;
            return this;
        }

        public Follow build() {
            return new Follow(this);
        }
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

    public boolean getNotified() {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Follow follow = (Follow) o;
        return Objects.equals(id, follow.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
