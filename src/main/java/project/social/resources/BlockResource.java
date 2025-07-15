package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.services.BlockService;
import project.social.util.SecurityUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/block")
public class BlockResource {

    private final BlockService blockService;
    private final SecurityUtils securityUtils;

    @PostMapping("/id/{id}/block")
    public ResponseEntity<Void> blockUser(@PathVariable String id) {
        String loggedUserId = securityUtils.getLoggedUserId();
        blockService.blockUser(loggedUserId, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}/block")
    public ResponseEntity<Void> unblockUser(@PathVariable String id) {
        String loggedUserId = securityUtils.getLoggedUserId();
        blockService.unblockUser(loggedUserId, id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/id/{id}/mute")
    public ResponseEntity<Void> muteUser(@PathVariable String id) {
        String loggedUserId = securityUtils.getLoggedUserId();
        blockService.muteUser(loggedUserId, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}/mute")
    public ResponseEntity<Void> unmuteUser(@PathVariable String id) {
        String loggedUserId = securityUtils.getLoggedUserId();
        blockService.unmuteUser(loggedUserId, id);
        return ResponseEntity.noContent().build();
    }
}
