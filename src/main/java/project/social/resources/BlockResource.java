package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.common.annotations.CurrentUser;
import project.social.common.dtos.domain.BlockDto;
import project.social.services.BlockService;
import project.social.common.utils.SecurityUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/block")
public class BlockResource {

    private final BlockService blockService;
    private final SecurityUtils securityUtils;

    /**
     * Blocks a user by their ID.
     *
     * @param id the ID of the user to block
     * @return a response indicating the result of the block operation
     */
    @PostMapping("/user-id/{id}/block")
    public ResponseEntity<Void> blockUser(@CurrentUser String currentUserId, @PathVariable String id) {
        blockService.blockUser(currentUserId, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Unblocks a user by their ID.
     *
     * @param id the ID of the user to unblock
     * @return a response indicating the result of the unblock operation
     */
    @DeleteMapping("/user-id/{id}/block")
    public ResponseEntity<Void> unblockUser(@CurrentUser String currentUserId, @PathVariable String id) {
        blockService.unblockUser(currentUserId, id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Mutes a user by their ID.
     *
     * @param id the ID of the user to mute
     * @return a response indicating the result of the mute operation
     */
    @PostMapping("/user-id/{id}/mute")
    public ResponseEntity<Void> muteUser(@CurrentUser String currentUserId, @PathVariable String id) {
        blockService.muteUser(currentUserId, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Unmutes a user by their ID.
     *
     * @param id the ID of the user to unmute
     * @return a response indicating the result of the unmute operation
     */
    @DeleteMapping("/user-id/{id}/mute")
    public ResponseEntity<Void> unmuteUser(@PathVariable String id, @CurrentUser String currentUserId) {
        blockService.unmuteUser(currentUserId, id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all blocks for a user by their ID.
     *
     * @param id the ID of the user whose blocks are to be retrieved
     * @param pageNumber the page number for pagination
     * @param pageSize the size of each page for pagination
     * @return a paginated list of BlockDto objects
     */
    @GetMapping("/user-id/{id}")
    public ResponseEntity<Page<BlockDto>> getBlockByUserId(@PathVariable String id,
                                                           @RequestParam(defaultValue = "0") int pageNumber,
                                                           @RequestParam(defaultValue = "10") int pageSize) {
        Page<BlockDto> page = blockService.findAllBlockById(id, pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }
}
