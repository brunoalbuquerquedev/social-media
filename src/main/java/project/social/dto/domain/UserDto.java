package project.social.dto.domain;

import project.social.domain.Post;
import project.social.domain.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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

    private List<Post> posts;
    private List<String> followersIds;
    private List<String> followedIds;
    private List<String> blockedUsersIds;
    private List<String> blockedByIds;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.bio = user.getBio();
        this.profilePictureUrl = user.getProfilePictureUrl();
        this.location = user.getLocation();
        this.birthDate = user.getBirthDate();
        this.createdAt = user.getCreatedAt();
        this.isActive = user.getIsActive();
        this.lastLogin = user.getLastLogin();
        this.posts = user.getPosts();
        this.followersIds = user.getFollowersIds();
        this.followedIds = user.getFollowedIds();
        this.blockedUsersIds = user.getBlockedUsersIds();
        this.blockedByIds = user.getBlockedByIds();
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

    public List<String> getFollowedIds() {
        return followedIds;
    }

    public void setFollowingIds(List<String> followedIds) {
        this.followedIds = followedIds;
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
}
