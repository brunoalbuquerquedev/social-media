package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.social.common.dtos.domain.BookmarkDto;
import project.social.common.exceptions.base.ObjectNotFoundException;
import project.social.common.exceptions.domain.LikeAlreadyExistsException;
import project.social.domain.Bookmark;
import project.social.repositories.BookmarkRepository;
import project.social.services.interfaces.IBookmarkService;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class BookmarkService implements IBookmarkService {

    private final BookmarkRepository bookmarkRepository;

    @Override
    public BookmarkDto createBookmark(String postId, String userId) {
        bookmarkRepository.findByUserIdAndPostId(userId, postId)
                .ifPresent(b -> {
                    throw new LikeAlreadyExistsException("Bookmark already exists.");
                });

        Bookmark bookmark = Bookmark.builder()
                .postId(postId)
                .userId(userId)
                .createdAt(OffsetDateTime.now())
                .build();

        Bookmark saved = bookmarkRepository.save(bookmark);
        return new BookmarkDto(saved);
    }

    @Override
    public void deleteBookmark(String postId, String userId) {
        bookmarkRepository.deleteByUserIdAndPostId(userId, postId);
    }

    @Override
    public Page<BookmarkDto> getUserBookmarks(String userId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return bookmarkRepository.findByUserId(userId, pageable).map(BookmarkDto::new);
    }

    @Override
    public long getBookmarkCount(String postId) {
        return bookmarkRepository.countByPostId(postId);
    }

    @Override
    public boolean hasUserBookmarked(String postId, String userId) {
        return bookmarkRepository.findByUserIdAndPostId(userId, postId).isPresent();
    }
}
