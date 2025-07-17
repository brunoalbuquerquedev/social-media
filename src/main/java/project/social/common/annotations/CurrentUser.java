package project.social.common.annotations;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Custom annotation to access the authenticated user in the Spring Security context.
 *
 * <p>This annotation is used on controller method parameters to inject the currently
 * authenticated user, leveraging Spring Security functionality.</p>
 *
 * <p>It is a meta-annotation that combines {@link AuthenticationPrincipal}, simplifying
 * access to the authenticated principal.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * &#64;GetMapping("/me")
 * public ResponseEntity&lt;User&gt; getCurrentUser(@CurrentUser User user) {
 *     return ResponseEntity.ok(user);
 * }
 * </pre>
 *
 * @Target({ElementType.PARAMETER}) Indicates that the annotation can only be used on method parameters.
 * @Retention(RetentionPolicy.RUNTIME) Indicates that the annotation is available at runtime.
 * @AuthenticationPrincipal Indicates that the injected value will be the authenticated principal.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@AuthenticationPrincipal
public @interface CurrentUser {
}