package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.dto.domain.ConversationDto;
import project.social.dto.domain.MessageDto;
import project.social.services.ChatService;
import project.social.util.SecurityUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatResource {

    private final ChatService chatService;
    private final SecurityUtil securityUtil;

    @GetMapping("/conversation/id/{id}")
    public ResponseEntity<List<ConversationDto>> getConversations(@PathVariable String id) {
        List<ConversationDto> list = chatService.getConversationsForUser(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/conversation/id/{id}/messages")
    public ResponseEntity<List<MessageDto>> getMessages(@PathVariable String id) {
        List<MessageDto> list = chatService.getMessages(id);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/message/send")
    public ResponseEntity<MessageDto> sendMessage(@RequestParam String conversationId,
                                                  @RequestParam String senderId,
                                                  @RequestParam String content) {
        MessageDto dto = chatService.sendMessage(conversationId, senderId, content);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/conversation/start")
    public ResponseEntity<ConversationDto> startConversation(@RequestBody List<String> participantIds,
                                                             @RequestParam(defaultValue = "false") boolean isGroup) {
        ConversationDto dto = chatService.startConversation(participantIds, isGroup);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/message/seen/id/{id}")
    public ResponseEntity<Void> markAsSeen(@PathVariable String messageId) {
        String loggedUserId = securityUtil.getLoggedUserId();
        chatService.markAsSeen(messageId, loggedUserId);
        return ResponseEntity.noContent().build();
    }
}
