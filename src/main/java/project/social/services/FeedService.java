package project.social.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.social.domain.Post;
import project.social.domain.User;
import project.social.dto.FeedDto;
import project.social.repositories.PostRepository;
import project.social.repositories.UserRepository;

import java.util.ArrayList;
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

    public List<FeedDto> getTimelineForUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        List<String> followingIds = user.getFollowing();

        List<Post> posts = postRepository.findByAuthorIdInOrderByCreatedAtDesc(followingIds);

        List<FeedDto> timeline = new ArrayList<>();

        for (Post post : posts) {
            timeline.add(new FeedDto(post));
        }

        return timeline;
    }
}
