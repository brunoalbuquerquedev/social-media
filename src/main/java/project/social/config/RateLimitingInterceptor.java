package project.social.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitingInterceptor implements HandlerInterceptor {

    private static final int MAX_REQUESTS_PER_MINUTE = 100;
    private static final long MINUTE_IN_MILLIS = 60 * 1000;

    private final Map<String, RateLimitData> requestCounts = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
            throws Exception {

        String userId = extractUserId(request);
        if (userId == null) {
            return true;
        }

        RateLimitData data = requestCounts.computeIfAbsent(userId, k -> new RateLimitData());

        long now = Instant.now().toEpochMilli();

        if (now - data.windowStart >= MINUTE_IN_MILLIS) {
            data.windowStart = now;
            data.requestCount = 1;
            return true;
        }

        if (data.requestCount >= MAX_REQUESTS_PER_MINUTE) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Rate limit exceeded: " + MAX_REQUESTS_PER_MINUTE + " requests per minute");
            return false;
        }

        data.requestCount++;
        return true;
    }

    private String extractUserId(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        String userIdHeader = request.getHeader("X-User-Id");
        if (userIdHeader != null) {
            return userIdHeader;
        }
        return null;
    }

    public static class RateLimitData {
        public long windowStart;
        public int requestCount;

        public RateLimitData() {
            this.windowStart = Instant.now().toEpochMilli();
            this.requestCount = 0;
        }
    }
}
