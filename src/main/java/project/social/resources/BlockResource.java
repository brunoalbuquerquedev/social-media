package project.social.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.services.BlockService;
import project.social.util.SecurityUtil;

@RestController
@RequestMapping("/block")
public class BlockResource {

    @Autowired
    private BlockService blockService;

    @Autowired
    private SecurityUtil securityUtil;

    @PostMapping("/id/{id}")
    public ResponseEntity<Void> blockUser(@PathVariable String id) {
        String loggedUserId = securityUtil.getLoggedUserId();
        blockService.blockUser(loggedUserId, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> unblockUser(@PathVariable String id) {
        String loggedUserId = securityUtil.getLoggedUserId();
        blockService.unblockUser(loggedUserId, id);
        return ResponseEntity.noContent().build();
    }
}
