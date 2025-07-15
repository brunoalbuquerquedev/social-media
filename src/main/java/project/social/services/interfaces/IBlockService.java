package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.dto.domain.BlockDto;

public interface IBlockService {
    Page<BlockDto> findAllBlockById(String blockId, int pageNumber, int pageSize);

    void blockUser(String requesterId, String targetId);

    void unblockUser(String requesterId, String targetId);

    void muteUser(String requesterId, String targetId);

    void unmuteUser(String requesterId, String targetId);
}
