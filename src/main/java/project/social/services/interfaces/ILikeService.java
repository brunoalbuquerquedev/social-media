package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.dto.domain.LikeDto;

public interface ILikeService {
    LikeDto findById(String id);

    Page<LikeDto> findAllByUserId(String userId, int pageNumber, int pageSize);

    Page<LikeDto> findAllByPostId(String postId, int pageNumber, int pageSize);

    void likePost(String userId, String postId);

    void unlikePost(String userId, String postId);
}
