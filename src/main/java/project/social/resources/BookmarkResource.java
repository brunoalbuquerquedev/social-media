package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.common.annotations.CurrentUser;
import project.social.common.dtos.domain.BookmarkDto;
import project.social.services.interfaces.IBookmarkService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmarks")
public class BookmarkResource {

    private final IBookmarkService bookmarkService;

    /**
     * Creates a new bookmark.
     *
     * @param postId the ID of the post
     * @param currentUserId the ID of the current user
     * @return the created BookmarkDto
     */
    @PostMapping("/{postId}")
    public ResponseEntity<BookmarkDto> createBookmark(
            @PathVariable String postId,
            @CurrentUser String currentUserId) {
        BookmarkDto bookmark = bookmarkService.createBookmark(postId, currentUserId);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookmark);
    }

    /**
     * Deletes a bookmark.
     *
     * @param postId the ID of the post
     * @param currentUserId the ID of the current user
     * @return no content response
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteBookmark(
            @PathVariable String postId,
            @CurrentUser String currentUserId) {
        bookmarkService.deleteBookmark(postId, currentUserId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all bookmarks for the current user.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param currentUserId the ID of the current user
     * @return a paginated list of BookmarkDtos
     */
    @GetMapping("/me")
    public ResponseEntity<Page<BookmarkDto>> getMyBookmarks(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @CurrentUser String currentUserId) {
        Page<BookmarkDto> bookmarks = bookmarkService.getUserBookmarks(currentUserId, pageNumber, pageSize);
        return ResponseEntity.ok(bookmarks);
    }

    /**
     * Gets the count of bookmarks for a post.
     *
     * @param postId the ID of the post
     * @return the count of bookmarks
     */
    @GetMapping("/{postId}/count")
    public ResponseEntity<Long> getBookmarkCount(@PathVariable String postId) {
        long count = bookmarkService.getBookmarkCount(postId);
        return ResponseEntity.ok(count);
    }
}
