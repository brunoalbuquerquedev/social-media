package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.social.common.dtos.domain.HashtagDto;
import project.social.common.exceptions.base.ObjectNotFoundException;
import project.social.domain.Hashtag;
import project.social.repositories.HashtagRepository;
import project.social.services.interfaces.IHashtagService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class HashtagService implements IHashtagService {

    private final HashtagRepository hashtagRepository;
    private static final Pattern HASHTAG_PATTERN = Pattern.compile("#(\\w+)");

    @Override
    public void processHashtags(String content) {
        if (content == null || content.isEmpty()) {
            return;
        }

        Matcher matcher = HASHTAG_PATTERN.matcher(content);
        while (matcher.find()) {
            String tag = matcher.group(1);
            incrementHashtagCount(tag);
        }
    }

    @Override
    public Page<HashtagDto> searchHashtags(String tag, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return hashtagRepository.findByTagContainingIgnoreCase(tag, pageable).map(HashtagDto::new);
    }

    @Override
    public Page<HashtagDto> getTrendingHashtags(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return hashtagRepository.findAllByOrderByUsageCountDesc(pageable).map(HashtagDto::new);
    }

    @Override
    public List<HashtagDto> getTopTrendingHashtags() {
        return hashtagRepository.findTop10ByOrderByUsageCountDesc()
                .stream()
                .map(HashtagDto::new)
                .toList();
    }

    @Override
    public HashtagDto getHashtagByTag(String tag) {
        Hashtag hashtag = hashtagRepository.findByTag(tag)
                .orElseThrow(() -> new ObjectNotFoundException("Hashtag not found."));
        return new HashtagDto(hashtag);
    }

    @Override
    public void incrementHashtagCount(String tag) {
        Hashtag hashtag = hashtagRepository.findByTag(tag)
                .orElse(Hashtag.builder()
                        .tag(tag)
                        .usageCount(0)
                        .build());

        hashtag.setUsageCount((hashtag.getUsageCount() == null ? 0 : hashtag.getUsageCount()) + 1);
        hashtag.setLastUsedAt(OffsetDateTime.now());

        hashtagRepository.save(hashtag);
    }
}
