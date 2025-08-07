package project.social.resources;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.common.annotations.CurrentUser;
import project.social.common.dtos.domain.ConversationDto;
import project.social.common.dtos.domain.MessageDto;
import project.social.services.ChatService;
import project.social.common.utils.SecurityUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
public class ChatResource {

    private final ChatService chatService;
    private final SecurityUtils securityUtils;

    /**
     * Retrieves conversations for a user by their ID.
     *
     * @param id   the ID of the user
     * @param page the page number to retrieve
     * @param size the number of items per page
     * @return a paginated list of conversations
     */
    @GetMapping("/conversation-id/{id}")
    public ResponseEntity<Page<ConversationDto>> getConversations(@PathVariable String id,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        Page<ConversationDto> list = chatService.getConversationsForUser(id, page, size);
        return ResponseEntity.ok(list);
    }

    /**
     * Retrieves messages for a conversation by its ID.
     *
     * @param id        the ID of the conversation
     * @param pageNumber the page number to retrieve
     * @param pageSize   the number of items per page
     * @return a paginated list of messages in the conversation
     */
    @GetMapping("/conversation-id/{id}/messages")
    public ResponseEntity<Page<MessageDto>> getMessages(@PathVariable String id,
                                                        @RequestParam int pageNumber,
                                                        @RequestParam int pageSize) {
        Page<MessageDto> page = chatService.getMessages(id, pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }

    /**
     * Sends a message in a conversation.
     *
     * @param conversationId the ID of the conversation
     * @param senderId       the ID of the sender
     * @param content        the content of the message
     * @return the sent message as a DTO
     */
    @PostMapping("/message/send")
    public ResponseEntity<MessageDto> sendMessage(@RequestParam String conversationId,
                                                  @RequestParam String senderId,
                                                  @RequestParam String content) {
        MessageDto dto = chatService.sendMessage(conversationId, senderId, content);
        return ResponseEntity.ok(dto);
    }

    /**
     * Starts a new conversation with the specified participants.
     *
     * @param participantIds the list of participant IDs
     * @return the created conversation as a DTO
     */
    @PostMapping("/conversation/start")
    public ResponseEntity<ConversationDto> startConversation(@Valid @RequestBody List<String> participantIds) {
        ConversationDto dto = chatService.startConversation(participantIds);
        return ResponseEntity.ok(dto);
    }

    /**
     * Marks a message as seen by the user.
     *
     * @param messageId the ID of the message to mark as seen
     * @return a response indicating the operation was successful
     */
    @PostMapping("/message-id/{id}/mark-as-seen")
    public ResponseEntity<Void> markAsSeen(@PathVariable String messageId, @CurrentUser String currentUserId) {
        chatService.markAsSeen(messageId, currentUserId);
        return ResponseEntity.noContent().build();
    }
}
