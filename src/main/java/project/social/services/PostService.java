package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.social.domain.Post;
import project.social.dto.domain.PostDto;
import project.social.dto.domain.UserDto;
import project.social.exceptions.base.ObjectNotFoundException;
import project.social.repositories.PostRepository;
import project.social.services.interfaces.IPostService;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final UserService userService;

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

    @Override
    public void createPost(String userId, String content, String authorProfilePictureUrl, List<String> mediaUrl) {
        UserDto user = userService.findById(userId);

        Post post = Post.builder()
                .authorId(userId)
                .authorUsername(user.username())
                .content(content)
                .authorProfilePictureUrl(authorProfilePictureUrl)
                .createdAt(OffsetDateTime.now())
                .hasUserLiked(false)
                .mediaUrl(mediaUrl)
                .build();

        postRepository.save(post);
    }

    @Override
    public void deletePost(String userId, String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ObjectNotFoundException("Post not found."));

        if (post.getAuthorId().equals(userId))
            postRepository.delete(post);
    }
}
