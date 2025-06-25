package project.social.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import project.social.dto.UserDto;

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
    private List<Post> posts = new ArrayList<>();
    private List<String> followers = new ArrayList<>();
    private List<String> following = new ArrayList<>();

    public User() {
    }

    public User(String id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public User(String id, String username, String email, String password, String fullName,
                String bio, String profilePictureUrl, String location, Date birthDate,
                Date createdAt, Boolean isActive, Date lastLogin,
                List<Post> posts, List<String> followers, List<String> following) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.bio = bio;
        this.profilePictureUrl = profilePictureUrl;
        this.location = location;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.isActive = isActive;
        this.lastLogin = lastLogin;
        this.posts = posts;
        this.followers = followers;
        this.following = following;
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

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
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
        return new User(dto.getId(), dto.getUsername(), dto.getEmail());
    }

    public void updateData(User user) {
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setFullName(user.getFullName());
        setBio(user.getBio());
        setLocation(user.getLocation());
        setBirthDate(user.getBirthDate());
        setLastLogin(user.getLastLogin());
    }
}
