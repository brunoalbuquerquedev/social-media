package project.social.common.utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import project.social.common.annotations.ValidateUUID;

import java.util.UUID;

/**
 * Validator class for the {@link ValidateUUID} annotation.
 *
 * <p>This class implements the {@link ConstraintValidator} interface to provide
 * validation logic for ensuring that a string represents a valid UUID.</p>
 *
 * <p>Validation rules:</p>
 * <ul>
 *   <li>The value must not be null or empty.</li>
 *   <li>The value must conform to the UUID format.</li>
 * </ul>
 *
 * <p>If the value is invalid, the validation will fail.</p>
 */
public class ValidateUUIDValidator implements ConstraintValidator<ValidateUUID, String> {

    /**
     * Validates whether the given string is a valid UUID.
     *
     * @param value   The string to validate.
     * @param context The context in which the constraint is evaluated.
     * @return {@code true} if the string is a valid UUID, {@code false} otherwise.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty())
            return false;

        try {
            UUID.fromString(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Initializes the validator with the properties of the {@link ValidateUUID} annotation.
     *
     * @param constraintAnnotation The annotation instance containing the configuration.
     */
    @Override
    public void initialize(ValidateUUID constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}