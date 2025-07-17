package project.social.common.annotations;

import jakarta.validation.Constraint;
import org.springframework.data.mongodb.core.mapping.Document;
import project.social.common.utils.validation.SanitizeInputValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for sanitizing input in entity fields.
 *
 * <p>This annotation is used to validate and sanitize input values in fields,
 * ensuring that values do not contain invalid or malicious characters.</p>
 *
 * <p>It is a validation annotation that uses {@link SanitizeInputValidator}
 * to apply the defined sanitization rules.</p>
 *
 * <p>Properties:</p>
 * <ul>
 *   <li><strong>message:</strong> Default error message displayed when validation fails.</li>
 *   <li><strong>allowEmpty:</strong> Indicates whether empty values are allowed. Default is <code>true</code>.</li>
 *   <li><strong>groups:</strong> Validation groups to which this annotation applies.</li>
 *   <li><strong>payload:</strong> Additional information about the validation violation.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>
 * &#64;SanitizeInput(message = "Invalid input.", allowEmpty = false)
 * private String username;
 * </pre>
 *
 * @Document Indicates that the annotation is associated with a MongoDB document.
 * @Constraint Specifies the validator class {@link SanitizeInputValidator}.
 * @Target(ElementType.FIELD) Indicates that the annotation can only be used on fields.
 * @Retention(RetentionPolicy.RUNTIME) Indicates that the annotation is available at runtime.
 */
@Document
@Constraint(validatedBy = SanitizeInputValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SanitizeInput {
    /**
     * Default error message displayed when validation fails.
     *
     * @return The error message.
     */
    String message() default "Input contains invalid characters.";

    /**
     * Indicates whether empty values are allowed.
     *
     * @return <code>true</code> if empty values are allowed, otherwise <code>false</code>.
     */
    boolean allowEmpty() default true;

    /**
     * Validation groups to which this annotation applies.
     *
     * @return The validation groups.
     */
    Class<?>[] groups() default {};

    /**
     * Additional information about the validation violation.
     *
     * @return The additional information.
     */
    Class<? extends jakarta.validation.Payload>[] payload() default {};
}
