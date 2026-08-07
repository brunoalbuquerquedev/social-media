package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import project.social.common.dtos.domain.HashtagDto;
import project.social.common.dtos.domain.PostDto;
import project.social.common.dtos.domain.SearchResultDto;
import project.social.common.dtos.domain.user.UserResponseDto;
import project.social.repositories.HashtagRepository;
import project.social.repositories.PostRepository;
import project.social.repositories.UserRepository;
import project.social.services.interfaces.ISearchService;

@Service
@RequiredArgsConstructor
public class SearchService implements ISearchService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final HashtagRepository hashtagRepository;

    @Override
    public Page<UserResponseDto> searchUsers(String query, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return userRepository.findByUsernameContainingIgnoreCaseOrFullNameContainingIgnoreCase(
                query, query, pageable).map(UserResponseDto::new);
    }

    @Override
    public Page<PostDto> searchPosts(String query, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return postRepository.findByContentContainingIgnoreCase(query, pageable).map(PostDto::new);
    }

    @Override
    public Page<HashtagDto> searchHashtags(String query, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return hashtagRepository.findByTagContainingIgnoreCase(query, pageable).map(HashtagDto::new);
    }

    @Override
    public SearchResultDto globalSearch(String query, int pageNumber, int pageSize) {
        Page<UserResponseDto> users = searchUsers(query, pageNumber, pageSize);
        Page<PostDto> posts = searchPosts(query, pageNumber, pageSize);
        Page<HashtagDto> hashtags = searchHashtags(query, pageNumber, pageSize);

        return new SearchResultDto(
                users.toList(),
                posts.toList(),
                hashtags.toList()
        );
    }
}
