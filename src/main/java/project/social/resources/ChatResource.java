package project.social.resources;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.dto.domain.ConversationDto;
import project.social.dto.domain.MessageDto;
import project.social.services.ChatService;
import project.social.util.SecurityUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatResource {

    private final ChatService chatService;
    private final SecurityUtils securityUtils;

    @GetMapping("/conversation/id/{id}")
    public ResponseEntity<Page<ConversationDto>> getConversations(@PathVariable String id,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        Page<ConversationDto> list = chatService.getConversationsForUser(id, page, size);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/conversation/id/{id}/messages")
    public ResponseEntity<Page<MessageDto>> getMessages(@PathVariable String id,
                                                        @RequestParam int pageNumber,
                                                        @RequestParam int pageSize) {
        Page<MessageDto> page = chatService.getMessages(id, pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }

    @PostMapping("/message/send")
    public ResponseEntity<MessageDto> sendMessage(@RequestParam String conversationId,
                                                  @RequestParam String senderId,
                                                  @RequestParam String content) {
        MessageDto dto = chatService.sendMessage(conversationId, senderId, content);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/conversation/start")
    public ResponseEntity<ConversationDto> startConversation(@Valid  @RequestBody List<String> participantIds) {
        ConversationDto dto = chatService.startConversation(participantIds);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/message/seen/id/{id}")
    public ResponseEntity<Void> markAsSeen(@PathVariable String messageId) {
        String loggedUserId = securityUtils.getLoggedUserId();
        chatService.markAsSeen(messageId, loggedUserId);
        return ResponseEntity.noContent().build();
    }
}
