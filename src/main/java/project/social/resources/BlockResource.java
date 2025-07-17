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

    @PostMapping("/id/{id}/block")
    public ResponseEntity<Void> blockUser(@PathVariable String id, @CurrentUser String currentUserId) {
        blockService.blockUser(currentUserId, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}/block")
    public ResponseEntity<Void> unblockUser(@PathVariable String id, @CurrentUser String currentUserId) {
        blockService.unblockUser(currentUserId, id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/id/{id}/mute")
    public ResponseEntity<Void> muteUser(@PathVariable String id, @CurrentUser String currentUserId) {
        blockService.muteUser(currentUserId, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}/mute")
    public ResponseEntity<Void> unmuteUser(@PathVariable String id, @CurrentUser String currentUserId) {
        blockService.unmuteUser(currentUserId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Page<BlockDto>> getBlockByUserId(@PathVariable String id,
                                                           @RequestParam(defaultValue = "0") int pageNumber,
                                                           @RequestParam(defaultValue = "10") int pageSize) {
        Page<BlockDto> page = blockService.findAllBlockById(id, pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }
}
