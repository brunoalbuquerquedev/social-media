package project.social.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.social.domain.Post;
import project.social.domain.User;
import project.social.dto.domain.FeedDto;
import project.social.dto.domain.PostDto;
import project.social.repositories.PostRepository;
import project.social.repositories.UserRepository;
import project.social.services.exceptions.ObjectNotFoundException;

import java.util.List;

@Service
public class FeedService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PostRepository postRepository;

    public FeedService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public FeedDto getTimelineForUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User not found"));
        List<Post> posts = postRepository.findByAuthorIdInOrderByCreatedAtDesc(user.getFollowedIds());

        List<PostDto> dtoList = posts.stream()
                .map(PostDto::new)
                .toList();

        return new FeedDto(dtoList);
    }
}
