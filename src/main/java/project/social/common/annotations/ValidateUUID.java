package project.social.common.annotations;

import jakarta.validation.Constraint;
import org.springframework.data.mongodb.core.mapping.Document;
import project.social.common.utils.validation.ValidateUUIDValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for validating UUID format in fields or parameters.
 *
 * <p>This annotation ensures that the annotated field or parameter contains
 * a valid UUID string.</p>
 *
 * <p>It is a validation annotation that uses {@link ValidateUUIDValidator}
 * to enforce the UUID format validation rules.</p>
 *
 * <p>Properties:</p>
 * <ul>
 *   <li><strong>message:</strong> Default error message displayed when validation fails.</li>
 *   <li><strong>groups:</strong> Validation groups to which this annotation applies.</li>
 *   <li><strong>payload:</strong> Additional information about the validation violation.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>
 * &#64;ValidateUUID(message = "Invalid UUID format.")
 * private String id;
 * </pre>
 *
 * @Document Indicates that the annotation is associated with a MongoDB document.
 * @Constraint Specifies the validator class {@link ValidateUUIDValidator}.
 * @Target({ElementType.FIELD,ElementType.PARAMETER}) Indicates that the annotation can be used on fields and parameters.
 * @Retention(RetentionPolicy.RUNTIME) Indicates that the annotation is available at runtime.
 */
@Document
@Constraint(validatedBy = ValidateUUIDValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateUUID {
    /**
     * Default error message displayed when validation fails.
     *
     * @return The error message.
     */
    String message() default "Invalid UUID format.";

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
