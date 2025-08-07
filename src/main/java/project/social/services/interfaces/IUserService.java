package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.common.dtos.domain.user.UserDto;
import project.social.common.dtos.domain.user.UserResponseDto;
import project.social.common.dtos.domain.user.UserUpdateDto;

import java.util.List;

public interface IUserService {
    /**
     * Finds all users with pagination.
     *
     * @param page the page number to retrieve
     * @param size the number of users per page
     * @return a page of UserDto objects
     */
    Page<UserResponseDto> findAllById(List<String> id, int page, int size);

    /**
     * Finds a user by their ID.
     *
     * @param id the ID of the user
     * @return the UserDto object if found, otherwise null
     */
    UserResponseDto findById(String id);

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user
     * @return the UserDto object if found, otherwise null
     */
    UserResponseDto findByUsername(String username);

    /**
     * Updates a user's details.
     *
     * @param dto the UserDto object containing user details
     */
    void updateUser(String userId, UserUpdateDto dto);
}
