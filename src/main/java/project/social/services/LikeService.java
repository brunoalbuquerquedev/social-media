package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.social.domain.Like;
import project.social.dto.domain.LikeDto;
import project.social.exceptions.base.ObjectNotFoundException;
import project.social.exceptions.domain.LikeAlreadyExistsException;
import project.social.repositories.LikeRepository;
import project.social.services.interfaces.ILikeService;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class LikeService implements ILikeService {

    private final LikeRepository likeRepository;

    public Page<LikeDto> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return likeRepository.findAll(pageable).map(LikeDto::new);
    }

    @Override
    public LikeDto findById(String id) {
        Like like = likeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Like not found."));
        return new LikeDto(like);
    }

    @Override
    public Page<LikeDto> findAllByUserId(String userId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return likeRepository.findALLByUserId(userId, pageable).map(LikeDto::new);
    }

    @Override
    public Page<LikeDto> findAllByPostId(String postId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return likeRepository.findAllByPostId(postId, pageable).map(LikeDto::new);
    }

    @Override
    public void likePost(String userId, String postId) {
        likeRepository.findByUserIdAndPostId(userId, postId)
                .ifPresent(l -> {
                    throw new LikeAlreadyExistsException("Like already exists.");
                });

        Like like = Like.builder()
                .userId(userId)
                .postId(postId)
                .likedAt(OffsetDateTime.now())
                .build();

        likeRepository.save(like);
    }

    @Override
    public void unlikePost(String userId, String postId) {
        likeRepository.findByUserIdAndPostId(userId, postId)
                .ifPresent(likeRepository::delete);
    }
}
