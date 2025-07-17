package project.social.common.utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import project.social.common.annotations.SanitizeInput;

/**
 * Validator class for the {@link SanitizeInput} annotation.
 *
 * <p>This class implements the {@link ConstraintValidator} interface to provide
 * validation logic for sanitizing input strings. It ensures that the input:
 * <ul>
 *   <li>Is not empty if empty values are not allowed.</li>
 *   <li>Does not exceed the maximum allowed length of 255 characters.</li>
 *   <li>Does not contain unsafe or malicious content.</li>
 * </ul>
 * </p>
 *
 * <p>The validation uses the Jsoup library to sanitize the input and compare
 * it with the original value to ensure it is safe.</p>
 */
public class    SanitizeInputValidator implements ConstraintValidator<SanitizeInput, String> {

    /**
     * Indicates whether empty values are allowed.
     */
    private boolean allowEmpty;

    /**
     * Validates the input string based on the sanitization rules.
     *
     * @param string  The input string to validate.
     * @param context The context in which the constraint is evaluated.
     * @return {@code true} if the input is valid, {@code false} otherwise.
     */
    @Override
    public boolean isValid(String string, ConstraintValidatorContext context) {
        final int MAX_LENGTH = 255;

        if (string == null)
            return true;

        String trimmed = string.trim();
        if (!allowEmpty && trimmed.isEmpty())
            return false;

        if (trimmed.length() > MAX_LENGTH)
            return false;

        String sanitized = Jsoup.clean(trimmed, Safelist.none());
        return sanitized.equals(trimmed);
    }

    /**
     * Initializes the validator with the properties of the {@link SanitizeInput} annotation.
     *
     * @param constraintAnnotation The annotation instance containing the configuration.
     */
    @Override
    public void initialize(SanitizeInput constraintAnnotation) {
        this.allowEmpty = constraintAnnotation.allowEmpty();
    }
}
