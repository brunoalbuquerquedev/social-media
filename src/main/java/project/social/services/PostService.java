package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.social.domain.Post;
import project.social.dto.domain.PostDto;
import project.social.exceptions.base.ObjectNotFoundException;
import project.social.repositories.PostRepository;
import project.social.services.interfaces.IPostService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final PostRepository postRepository;

    @Override
    public List<PostDto> findAll() {
        return postRepository.findAll().stream()
                .map(PostDto::new)
                .toList();
    }

    @Override
    public PostDto findById(String id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Post not found."));
        return new PostDto(post);
    }

    @Override
    public List<PostDto> findByTitle(String text) {
        return postRepository.findByTitleContainingIgnoreCase(text).stream()
                .map(PostDto::new)
                .toList();
    }

    @Override
    public List<PostDto> findByHasUserLiked() {
        return postRepository.findByHasUserLiked(true).stream()
                .map(PostDto::new)
                .toList();
    }
}
