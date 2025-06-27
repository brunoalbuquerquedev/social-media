package project.social.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import project.social.dto.domain.UserDto;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Document(collection = "users")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String username;
    private String email;
    private String password;

    private String fullName;
    private String bio;
    private String profilePictureUrl;
    private String location;
    private Date birthDate;

    private Date createdAt;
    private Boolean isActive;
    private Date lastLogin;

    @DBRef(lazy = true)
    private List<Post> posts;

    @DBRef(lazy = true)
    private List<String> followersIds;

    @DBRef(lazy = true)
    private List<String> followingIds;

    @DBRef(lazy = true)
    private List<String> blockedUsersIds;

    @DBRef(lazy = true)
    private List<String> blockedByIds;

    public User() {
    }

    private User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
        this.fullName = builder.fullName;
        this.bio = builder.bio;
        this.profilePictureUrl = builder.profilePictureUrl;
        this.location = builder.location;
        this.birthDate = builder.birthDate;
        this.createdAt = builder.createdAt;
        this.isActive = builder.isActive;
        this.lastLogin = builder.lastLogin;
        this.posts = builder.posts;
        this.followersIds = builder.followersIds;
        this.followingIds = builder.followingIds;
        this.blockedUsersIds = builder.blockedUsersIds;
        this.blockedByIds = builder.blockedByIds;
    }

    public static class Builder {
        private String id = null;
        private String username;
        private String email;
        private String password;
        private String fullName = null;
        private String bio = null;
        private String profilePictureUrl = null;
        private String location = null;
        private Date birthDate = null;
        private Date createdAt = new Date();
        private Boolean isActive = true;
        private Date lastLogin = null;
        private List<Post> posts = new ArrayList<>();
        private List<String> followersIds = new ArrayList<>();
        private List<String> followingIds = new ArrayList<>();
        private List<String> blockedUsersIds = new ArrayList<>();
        private List<String> blockedByIds = new ArrayList<>();

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder bio(String bio) {
            this.bio = bio;
            return this;
        }

        public Builder profilePictureUrl(String profilePictureUrl) {
            this.profilePictureUrl = profilePictureUrl;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder birthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder lastLogin(Date lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        public Builder posts(List<Post> posts) {
            this.posts = posts;
            return this;
        }

        public Builder followersIds(List<String> followersIds) {
            this.followersIds = followersIds;
            return this;
        }

        public Builder followingIds(List<String> followingIds) {
            this.followingIds = followingIds;
            return this;
        }

        public Builder blockedUsersIds(List<String> blockedUsersIds) {
            this.blockedUsersIds = blockedUsersIds;
            return this;
        }

        public Builder blockedByIds(List<String> blockedByIds) {
            this.blockedByIds = blockedByIds;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<String> getFollowersIds() {
        return followersIds;
    }

    public void setFollowersIds(List<String> followersIds) {
        this.followersIds = followersIds;
    }

    public List<String> getFollowingIds() {
        return followingIds;
    }

    public void setFollowingIds(List<String> followingIds) {
        this.followingIds = followingIds;
    }

    public List<String> getBlockedUsersIds() {
        return blockedUsersIds;
    }

    public void setBlockedUsersIds(List<String> blockedUsersIds) {
        this.blockedUsersIds = blockedUsersIds;
    }

    public List<String> getBlockedByIds() {
        return blockedByIds;
    }

    public void setBlockedByIds(List<String> blockedByIds) {
        this.blockedByIds = blockedByIds;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static User fromDto(UserDto dto) {
        User.Builder builder = new User.Builder();
        builder.id(dto.getId());
        builder.username(dto.getUsername());
        builder.email(dto.getEmail());
        builder.password(dto.getPassword());
        builder.fullName(dto.getFullName());
        builder.bio(dto.getBio());
        builder.profilePictureUrl(dto.getProfilePictureUrl());
        builder.location(dto.getLocation());
        builder.birthDate(dto.getBirthDate());
        builder.createdAt(dto.getCreatedAt());
        builder.isActive(dto.getIsActive());
        builder.lastLogin(dto.getLastLogin());
        builder.posts(dto.getPosts());
        builder.followersIds(dto.getFollowersIds());
        builder.followingIds(dto.getFollowingIds());
        builder.blockedUsersIds(dto.getBlockedUsersIds());
        builder.blockedByIds(dto.getBlockedByIds());
        return builder.build();
    }

    public void updateData(User user) {
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setFullName(user.getFullName());
        setBio(user.getBio());
        setProfilePictureUrl(user.getProfilePictureUrl());
        setLocation(user.getLocation());
        setBirthDate(user.getBirthDate());
        setCreatedAt(user.getCreatedAt());
        setIsActive(user.getIsActive());
        setLastLogin(user.getLastLogin());
        setPosts(user.getPosts());
        setFollowersIds(user.getFollowersIds());
        setFollowingIds(user.getFollowingIds());
        setBlockedUsersIds(user.getBlockedUsersIds());
        setBlockedByIds(user.getBlockedByIds());
    }
}
