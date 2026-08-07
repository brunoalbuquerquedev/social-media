package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.social.common.dtos.domain.HashtagDto;
import project.social.common.dtos.domain.PostDto;
import project.social.domain.Post;
import project.social.repositories.HashtagRepository;
import project.social.repositories.PostRepository;
import project.social.services.interfaces.ITrendingService;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrendingService implements ITrendingService {

    private final PostRepository postRepository;
    private final HashtagRepository hashtagRepository;

    @Override
    public Page<PostDto> getTrendingPosts(int pageNumber, int pageSize) {
        OffsetDateTime sevenDaysAgo = OffsetDateTime.now().minusDays(7);
        List<Post> recentPosts = postRepository.findByCreatedAtGreaterThan(sevenDaysAgo);

        List<PostDto> sortedPosts = recentPosts.stream()
                .sorted((p1, p2) -> {
                    int score1 = calculatePostScore(p1);
                    int score2 = calculatePostScore(p2);
                    return Integer.compare(score2, score1);
                })
                .map(PostDto::new)
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageSize, sortedPosts.size());

        return new PageImpl<>(
                sortedPosts.subList(start, end),
                pageable,
                sortedPosts.size()
        );
    }

    @Override
    public List<PostDto> getTopTrendingPosts(int limit) {
        OffsetDateTime sevenDaysAgo = OffsetDateTime.now().minusDays(7);
        List<Post> recentPosts = postRepository.findByCreatedAtGreaterThan(sevenDaysAgo);

        return recentPosts.stream()
                .sorted((p1, p2) -> {
                    int score1 = calculatePostScore(p1);
                    int score2 = calculatePostScore(p2);
                    return Integer.compare(score2, score1);
                })
                .limit(limit)
                .map(PostDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Page<HashtagDto> getTrendingHashtags(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return hashtagRepository.findAllByOrderByUsageCountDesc(pageable)
                .map(HashtagDto::new);
    }

    @Override
    public List<HashtagDto> getTopTrendingHashtags(int limit) {
        return hashtagRepository.findTop10ByOrderByUsageCountDesc()
                .stream()
                .limit(limit)
                .map(HashtagDto::new)
                .collect(Collectors.toList());
    }

    private int calculatePostScore(Post post) {
        int score = 0;
        
        if (post.getLikes() != null) {
            score += post.getLikes().size() * 2;
        }
        
        return score;
    }
}
