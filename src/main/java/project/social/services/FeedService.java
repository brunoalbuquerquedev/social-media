package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.social.domain.Post;
import project.social.domain.User;
import project.social.domain.enums.RestrictionType;
import project.social.dto.domain.FeedDto;
import project.social.dto.domain.PostDto;
import project.social.exceptions.base.ObjectNotFoundException;
import project.social.repositories.PostRepository;
import project.social.repositories.UserRepository;
import project.social.services.interfaces.IFeedService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService implements IFeedService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public FeedDto getFeed(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));

        List<String> usersWhoBlockedMe = user.getUsersBlockedByMe();
        List<String> visibleUsersIds = user.getUsersFollowedByMe().stream()
                .filter(id -> !usersWhoBlockedMe.contains(id))
                .toList();

        List<Post> posts = postRepository.findByAuthorIdInOrderByCreatedAtDesc(visibleUsersIds);

        List<PostDto> dtoList = posts.stream()
                .map(PostDto::new)
                .toList();

        return new FeedDto(dtoList);
    }
}
