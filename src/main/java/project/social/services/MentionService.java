package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.social.common.utils.MentionExtractorUtil;
import project.social.repositories.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MentionService {

    private final UserRepository userRepository;

    /**
     * Extracts mentions from content and returns their user IDs.
     *
     * @param content the content to extract mentions from
     * @return a set of mentioned user IDs
     */
    public Set<String> extractMentionedUserIds(String content) {
        Set<String> mentionedUsernames = MentionExtractorUtil.extractMentions(content);
        
        return mentionedUsernames.stream()
                .map(username -> userRepository.findByUsername(username))
                .filter(java.util.Optional::isPresent)
                .map(user -> user.get().getId())
                .collect(Collectors.toSet());
    }

    /**
     * Extracts mentions from content and returns their usernames.
     *
     * @param content the content to extract mentions from
     * @return a set of mentioned usernames
     */
    public Set<String> extractMentionedUsernames(String content) {
        return MentionExtractorUtil.extractMentions(content);
    }

    /**
     * Checks if content contains any valid user mentions.
     *
     * @param content the content to check
     * @return true if content contains valid user mentions, false otherwise
     */
    public boolean hasValidMentions(String content) {
        return !extractMentionedUserIds(content).isEmpty();
    }
}
