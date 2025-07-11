package project.social.services.interfaces;

public interface IBlockService {
    void blockUser(String requesterId, String targetId);

    void unblockUser(String requesterId, String targetId);

    void muteUser(String requesterId, String targetId);

    void unmuteUser(String requesterId, String targetId);
}
