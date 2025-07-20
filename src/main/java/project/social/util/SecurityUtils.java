package project.social.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import project.social.exceptions.auth.InvalidTokenException;

@Component
public class SecurityUtils {

    /**
     * Obtains the ID of the currently authenticated user.
     *
     * <p>Retrieves the authentication principal from the security context and
     * ensures it is a valid string representing the user ID.</p>
     *
     * @return The ID of the logged-in user as a {@link String}.
     * @throws InvalidTokenException if no user is logged in or the authentication principal is invalid.
     */
    public String getLoggedUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof String)
            return (String) principal;

        throw new InvalidTokenException("No user logged in or invalid authentication principal.");
    }
}
