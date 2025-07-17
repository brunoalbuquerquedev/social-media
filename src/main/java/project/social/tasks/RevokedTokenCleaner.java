package project.social.tasks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.social.repositories.RevokedTokenRepository;

import java.time.OffsetDateTime;

@Slf4j
@RequiredArgsConstructor
@Component
public class RevokedTokenCleaner {

    private final RevokedTokenRepository revokedTokenRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void cleanExpiredRevokedTokens() {
        int deleted = revokedTokenRepository.deleteAllByExpiresAtBefore(OffsetDateTime.now());
        log.info("Expired revoked tokens removed: {}", deleted);
    }
}
