package project.mongo.dto;

import project.mongo.domain.User;

import java.io.Serializable;

public class UserDto implements Serializable {

    private String id;
    private String name;
    private String email;

    public UserDto() {
    }

    public UserDto(User obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.email = obj.getEmail();
    }

    public static User fromDto(UserDto dto) {
        return new User(dto.getId(), dto.getName(), dto.getEmail());
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
}
