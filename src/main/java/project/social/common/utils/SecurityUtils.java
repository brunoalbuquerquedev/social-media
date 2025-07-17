package project.social.common.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import project.social.common.exceptions.auth.InvalidTokenException;

@Component
public class SecurityUtils {

    public String getLoggedUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof String)
            return (String) principal;

        throw new InvalidTokenException("No user logged in or invalid authentication principal.");
    }
}
