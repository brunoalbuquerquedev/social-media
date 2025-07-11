package project.social.services.interfaces;

import project.social.dto.domain.PostDto;

import java.util.List;

public interface IPostService {
    List<PostDto> findAll();

    PostDto findById(String id);

    List<PostDto> findByTitle(String text);

    List<PostDto> findByHasUserLiked();
}
