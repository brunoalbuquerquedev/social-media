package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.social.common.dtos.domain.StoryDto;
import project.social.common.exceptions.base.ObjectNotFoundException;
import project.social.domain.Story;
import project.social.repositories.StoryRepository;
import project.social.services.interfaces.IStoryService;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoryService implements IStoryService {

    private final StoryRepository storyRepository;

    @Override
    public StoryDto createStory(String authorId, String content, String mediaUrl) {
        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime expiresAt = now.plus(24, ChronoUnit.HOURS);

        Story story = Story.builder()
                .authorId(authorId)
                .content(content)
                .mediaUrl(mediaUrl)
                .createdAt(now)
                .expiresAt(expiresAt)
                .build();

        Story saved = storyRepository.save(story);
        return new StoryDto(saved);
    }

    @Override
    public Page<StoryDto> getStoriesByAuthorId(String authorId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return storyRepository.findByAuthorId(authorId, pageable).map(StoryDto::new);
    }

    @Override
    public List<StoryDto> getActiveStoriesByAuthorId(String authorId) {
        OffsetDateTime now = OffsetDateTime.now();
        return storyRepository.findByAuthorIdAndExpiresAtGreaterThan(authorId, now)
                .stream()
                .map(StoryDto::new)
                .toList();
    }

    @Override
    public List<StoryDto> getAllActiveStories() {
        OffsetDateTime now = OffsetDateTime.now();
        return storyRepository.findByExpiresAtGreaterThan(now)
                .stream()
                .map(StoryDto::new)
                .toList();
    }

    @Override
    public StoryDto getStoryById(String id) {
        Story story = storyRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Story not found."));
        return new StoryDto(story);
    }

    @Override
    public void deleteStory(String id) {
        if (!storyRepository.existsById(id)) {
            throw new ObjectNotFoundException("Story not found.");
        }
        storyRepository.deleteById(id);
    }

    @Override
    public void deleteExpiredStories() {
        OffsetDateTime now = OffsetDateTime.now();
        storyRepository.deleteByExpiresAtLessThanEqual(now);
    }
}
