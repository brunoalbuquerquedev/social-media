package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.dto.domain.FollowDto;

public interface IFollowService {
    /**
     * Finds all follow relationships by the given user ID.
     *
     * @param id the ID of the user
     * @param page the page number to retrieve
     * @param size the number of items per page
     * @return a paginated list of FollowDto objects
     */
    Page<FollowDto> findAllById(String id, int page, int size);

    /**
     * Blocks a user by their ID.
     *
     * @param requesterId the ID of the user requesting the block
     * @param targetId the ID of the user to be blocked
     */
    void followUser(String requesterId, String targetId);

    /**
     * Unblocks a user by their ID.
     *
     * @param requesterId the ID of the user requesting the unblock
     * @param targetId the ID of the user to be unblocked
     */
    void unfollowUser(String requesterId, String targetId);

    /**
     * Deletes a mutual following relationship by blocking a user.
     *
     * @param requesterId the ID of the user requesting the block
     * @param targetId the ID of the user to be blocked
     */
    void deleteMutualFollowingByBlock(String requesterId, String targetId);
}
