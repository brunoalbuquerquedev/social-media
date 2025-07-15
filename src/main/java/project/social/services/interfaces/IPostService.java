package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.dto.domain.PostDto;

public interface IPostService {
    PostDto findById(String id);

    Page<PostDto> findByTitle(String text, int page, int size);

    Page<PostDto> findAllByHasUserLiked(boolean hasUserLiked, int page, int size);

    Page<PostDto> findAllById(String id, int pageNumber, int pageSize);
}
