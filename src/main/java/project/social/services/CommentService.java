package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.social.common.dtos.domain.CommentDto;
import project.social.common.exceptions.base.ObjectNotFoundException;
import project.social.domain.Comment;
import project.social.repositories.CommentRepository;
import project.social.services.interfaces.ICommentService;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;

    @Override
    public CommentDto createComment(String postId, String authorId, String content) {
        Comment comment = Comment.builder()
                .postId(postId)
                .authorId(authorId)
                .content(content)
                .createdAt(OffsetDateTime.now())
                .likesCount(0)
                .build();

        Comment saved = commentRepository.save(comment);
        return new CommentDto(saved);
    }

    @Override
    public CommentDto getCommentById(String id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Comment not found."));
        return new CommentDto(comment);
    }

    @Override
    public Page<CommentDto> getCommentsByPostId(String postId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return commentRepository.findByPostId(postId, pageable).map(CommentDto::new);
    }

    @Override
    public CommentDto updateComment(String id, String content) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Comment not found."));
        
        comment.setContent(content);
        comment.setEditedAt(OffsetDateTime.now());
        comment.setUpdatedAt(OffsetDateTime.now());
        
        Comment updated = commentRepository.save(comment);
        return new CommentDto(updated);
    }

    @Override
    public void deleteComment(String id) {
        if (!commentRepository.existsById(id)) {
            throw new ObjectNotFoundException("Comment not found.");
        }
        commentRepository.deleteById(id);
    }

    @Override
    public void incrementLikesCount(String id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Comment not found."));
        
        if (comment.getLikesCount() == null) {
            comment.setLikesCount(1);
        } else {
            comment.setLikesCount(comment.getLikesCount() + 1);
        }
        commentRepository.save(comment);
    }

    @Override
    public void decrementLikesCount(String id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Comment not found."));
        
        if (comment.getLikesCount() != null && comment.getLikesCount() > 0) {
            comment.setLikesCount(comment.getLikesCount() - 1);
            commentRepository.save(comment);
        }
    }
}
