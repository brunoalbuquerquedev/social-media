package project.social.resources.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import project.social.services.exceptions.*;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "Not found";

        StandardError error = new StandardError(
                String.valueOf(System.currentTimeMillis()),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<StandardError> userAlreadyExists(UserAlreadyExistsException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        String message = "User already exists";

        StandardError error = new StandardError(
                String.valueOf(System.currentTimeMillis()),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<StandardError> incorrectPassword(IncorrectPasswordException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String message = "Incorrect password.";

        StandardError error = new StandardError(
                String.valueOf(System.currentTimeMillis()),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<StandardError> invalidToken(InvalidTokenException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String message = "Invalid token.";

        StandardError error = new StandardError(
                String.valueOf(System.currentTimeMillis()),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(IllegalFollowingArgumentException.class)
    public ResponseEntity<StandardError> invalidFollowingArgument(InvalidTokenException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String message = "Invalid argument.";

        StandardError error = new StandardError(
                String.valueOf(System.currentTimeMillis()),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }
}
