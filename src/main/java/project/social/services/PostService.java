package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.social.domain.Post;
import project.social.dto.domain.PostDto;
import project.social.exceptions.base.ObjectNotFoundException;
import project.social.repositories.PostRepository;
import project.social.services.interfaces.IPostService;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final PostRepository postRepository;

    @Override
    public Page<PostDto> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return postRepository.findAll(pageable).map(PostDto::new);
    }

    @Override
    public PostDto findById(String id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Post not found."));
        return new PostDto(post);
    }

    @Override
    public Page<PostDto> findAllById(String id, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return postRepository.findAllById(id, pageable).map(PostDto::new);
    }

    @Override
    public Page<PostDto> findByTitle(String text, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return postRepository.findByTitleContainingIgnoreCase(text, pageable).map(PostDto::new);
    }

    @Override
    public Page<PostDto> findAllByHasUserLiked(boolean hasUserLiked, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return postRepository.findByHasUserLiked(true, pageable).map(PostDto::new);
    }
}
