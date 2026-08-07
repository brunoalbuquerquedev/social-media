package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.social.common.dtos.domain.RetweetDto;
import project.social.common.exceptions.base.ObjectNotFoundException;
import project.social.common.exceptions.domain.LikeAlreadyExistsException;
import project.social.domain.Retweet;
import project.social.repositories.RetweetRepository;
import project.social.services.interfaces.IRetweetService;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class RetweetService implements IRetweetService {

    private final RetweetRepository retweetRepository;

    @Override
    public RetweetDto createRetweet(String postId, String userId) {
        retweetRepository.findByUserIdAndPostId(userId, postId)
                .ifPresent(r -> {
                    throw new LikeAlreadyExistsException("Retweet already exists.");
                });

        Retweet retweet = Retweet.builder()
                .postId(postId)
                .userId(userId)
                .createdAt(OffsetDateTime.now())
                .build();

        Retweet saved = retweetRepository.save(retweet);
        return new RetweetDto(saved);
    }

    @Override
    public void deleteRetweet(String postId, String userId) {
        retweetRepository.deleteByUserIdAndPostId(userId, postId);
    }

    @Override
    public Page<RetweetDto> getRetweetsByPostId(String postId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return retweetRepository.findByPostId(postId, pageable).map(RetweetDto::new);
    }

    @Override
    public long getRetweetCount(String postId) {
        return retweetRepository.countByPostId(postId);
    }

    @Override
    public boolean hasUserRetweeted(String postId, String userId) {
        return retweetRepository.findByUserIdAndPostId(userId, postId).isPresent();
    }
}
