package project.social.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.social.domain.Post;
import project.social.domain.User;
import project.social.dto.TimelinePostDto;
import project.social.repository.PostRepository;
import project.social.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimelineService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PostRepository postRepository;

    public TimelineService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<TimelinePostDto> getTimelineForUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        List<String> followingIds = user.getFollowing();

        List<Post> posts = postRepository.findByAuthorIdInOrderByCreatedAtDesc(followingIds);

        List<TimelinePostDto> timeline = new ArrayList<>();

        for (Post post : posts) {
            timeline.add(new TimelinePostDto(post));
        }

        return timeline;
    }
}
