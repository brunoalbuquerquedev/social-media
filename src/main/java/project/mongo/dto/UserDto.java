package project.mongo.dto;

import project.mongo.domain.Post;
import project.mongo.domain.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDto implements Serializable {

    private String id;
    private String name;
    private String email;

    private List<Post> posts = new ArrayList<>();

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.posts = user.getPosts();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
