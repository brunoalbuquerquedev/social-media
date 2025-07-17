package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.common.dtos.domain.PostDto;

import java.util.List;

public interface IPostService {
    PostDto findById(String id);

    Page<PostDto> findAllByHasUserLiked(boolean hasUserLiked, int pageNumber, int pageSize);

    Page<PostDto> findAllById(String id, int pageNumber, int pageSize);

    void createPost(String userId, String content, String authorProfilePictureUrl, List<String> mediaUrl);

    void deletePost(String userId, String postId);
}
